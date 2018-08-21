package fonetica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class DocToBigramProb {

	private int nBigramsInDoc = 0;
	private Map<String, Integer> mapProb = new HashMap<>();
	private SortedSet<String> indexSet = new TreeSet<>();

	public DocToBigramProb() {
	}
	
	public List<String> getBigrams(){
		return new ArrayList<>(indexSet);
	}

	public void addSentence(String sentence) {
		String[] words = sentence.split("\\s+");
		for (int i = 0; i < words.length - 1; i++) {
			String key = words[i] + " " + words[i + 1];
			if (mapProb.containsKey(key)) {
				mapProb.put(key, mapProb.get(key) + 1);
			} else {
				mapProb.put(key, 1);
			}
			nBigramsInDoc++;
			indexSet.add(key);
		}
	}

	public double getProbByBigram(String[] bigram) {
		String key = new String(bigram[0] + " " + bigram[1]);
		if (!mapProb.containsKey(key)) {
			return 0;
		}
		return new Double(mapProb.get(key)) / new Double(nBigramsInDoc);
	}

	public String getBigramFromIndex(Double indexNorm) {
		int index = new Double(Math.round(indexNorm * (indexSet.size() + 1))).intValue();
		if (index >= indexSet.size() || index < 0) {
			return null;
		}
		return new ArrayList<>(indexSet).get(index);
	}

	public Double indexOfBigram(String[] bigram) {
		String key = new String(bigram[0] + " " + bigram[1]);

		List<String> list = new ArrayList<>(indexSet);
		for (int i = 0; i < indexSet.size(); i++) {
			if (list.get(i).equals(key)) {
				return new Double(i) / new Double(indexSet.size() + 1);
			}

		}
		return new Double(0);
	}
}
