package fonetica;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class BigramMapping {

	private Map<String, Set<String>> map = new TreeMap<>();

	public void fillMap(String source) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader(source));
		sc.nextLine();
		while (sc.hasNextLine()) {
			String[] line = sc.nextLine().split("\t");
			String[] word = line[0].split("-");
			String[] fonetica = line[2].split("-");
			if (word.length != fonetica.length) {
				continue;
			}
			for (int i = 0; i < word.length; i++) {
				if (map.containsKey(word[i])) {
					map.get(word[i]).add(fonetica[i]);
				} else {
					Set<String> set = new TreeSet<>();
					set.add(fonetica[i]);
					map.put(word[i], set);
				}
			}
		}
		sc.close();
	}

	public void writeMapToFile(String fileName) throws IOException {
		FileWriter fw = new FileWriter(fileName);
		for (String key : map.keySet()) {
			fw.write(key);
			fw.write(" = ");
			fw.write(map.get(key).toString());
			fw.write("\n");
		}
		fw.close();
	}

	public Map<String, Set<String>> getMap() {
		return map;
	}
	
	
}
