package fonetica;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class VecToBigramProb {
	private int nBigramsInDoc = 0;
	private Map<BigramVector, Integer> mapProb = new HashMap<>();
	private SortedSet<BigramVector> indexSet = null;

	public VecToBigramProb() {
		this.indexSet = new TreeSet<>(new Comparator<BigramVector>() {
			@Override
			public int compare(BigramVector bv1, BigramVector bv2) {
				return bv1.getDet().compareTo(bv2.getDet());
			}
		});
	}

	public void addSentence(List<Double[]> values) {

		for (int i = 0; i < values.size() - 1; i++) {
			BigramVector key = new BigramVector(values.get(i), values.get(i + 1));
			if (mapProb.containsKey(key)) {
				mapProb.put(key, mapProb.get(key) + 1);
			} else {
				mapProb.put(key, 1);
			}
			nBigramsInDoc++;
			indexSet.add(key);
		}
	}

	public double getProbByBigram(Double[][] bigram) {
		BigramVector key = new BigramVector(bigram);
		if (!mapProb.containsKey(key)) {
			return 0;
		}
		return new Double(mapProb.get(key)) / new Double(nBigramsInDoc);
	}

	public Double[][] getBigramFromIndex(Double indexNorm) {

		int index = new Double(Math.round(indexNorm * (indexSet.size() + 1))).intValue()-1;
		if (index >= indexSet.size()) {
			return null;
		}
		return new ArrayList<>(indexSet).get(index).getBigram();
	}

	public Double indexOfBigram(Double[][] bigram) {
		List<BigramVector> list = new ArrayList<>(indexSet);
		BigramVector key = new BigramVector(bigram);
		for (int i = 1; i == indexSet.size(); i++) {
			if (list.get(i-1).equals(key)) {
				return new Double(i) / new Double(indexSet.size() + 1);
			}

		}

		return new Double(0);
	}
	
	public int getIndexSize() {
		return indexSet.size();
	}
}
