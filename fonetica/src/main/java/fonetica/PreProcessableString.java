package fonetica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PreProcessableString extends PreProcessable<String> {

	@Override
	String preProcess(String value) {		
		return value
				.replaceAll("”", " \" ")
				.replaceAll("“", " \" ")
				.replaceAll("‘", " \' ")
				.replaceAll("’", " \' ")
				.replaceAll("\\.", " . ")
				.replaceAll("(…|\\.\\.\\.)", " … ")
				.replaceAll(",", " , ")
				.replaceAll(":", " : ")
				.replaceAll("!", " ! ")
				.replaceAll("\\?", " ? ")
				.replaceAll(";", " ; ")
				.replaceAll("\"", " \" ")
				.replaceAll("\'", " \' ")
				.replaceAll("\\(", " ( ")
				.replaceAll("\\)", " ) ")
				.replaceAll("\\{", " { ")
				.replaceAll("\\}", " } ")
				.replaceAll("\\[", " [ ")
				.replaceAll("\\]", " ] ")
				.replaceAll("\\s+", " ")
				.trim();
	}
	
	@Override
	String[] splitPossibleSentences(String value) {
		Set<String> list = new HashSet<>();
		Pattern p = Pattern.compile("(.+?)(\\.|;|!|\\?)");
		value = value.trim();		
		List<String> indexes = new ArrayList<>();
		indexes.add(0+"");

		Matcher m = p.matcher(value);
		while(m.find()) {
			indexes.add(m.end() + "");
		}
		while (!indexes.isEmpty()) {
			
			int index = Integer.valueOf(indexes.remove(0));
			
			if (value.length() > index) {

				value = value.substring(index, value.length()).trim();
				m = p.matcher(value);

				while (m.find()) {
					list.add(value.substring(0, m.end()).trim());
				}
				for (int i = 0; i < indexes.size(); i++) {
					indexes.set(i, (Integer.parseInt(indexes.get(i)) - index) + "");
					if (Integer.parseInt(indexes.get(i)) < 0) {
						indexes.remove(i);
					}
				}
			}

		}
		return list.toArray(new String[list.size()]);
	}

}
