package fonetica;

import java.util.Map;
import java.util.Set;

public class FoneticTransformerFactory {

	private final Map<String, Set<String>> foneticMap;
	private boolean useFonetic = false;

	public FoneticTransformerFactory(Map<String, Set<String>> foneticMap) {
		this.foneticMap = foneticMap;
	}

	public String transform(String document) {
		if(useFonetic) {
			return document;
		}
		for (String key : foneticMap.keySet()) {
			int index = 0;
			while ((index = document.indexOf(key, index)) != -1) {
				document = document.substring(0, index) + foneticMap.get(key).iterator().next()
						+ document.substring(index + key.length(), document.length());

				index = index + key.length();

				System.out.println(foneticMap.get(key).iterator().next());
				System.out.println(document);
				System.out.println(document.indexOf(key, index));
			}
		}
		return document;
	}

}
