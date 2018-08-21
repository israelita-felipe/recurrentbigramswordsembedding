package fonetica;

import java.util.ArrayList;
import java.util.List;

public class DocToVec {

	private final DocToBigramProb mapProb;

	public DocToVec(DocToBigramProb mapProb) {
		this.mapProb = mapProb;
	}

	public List<Double[]> getBigramVectorsFrom(String document) {
		List<Double[]> values = new ArrayList<>();
		if (document.length() < 2) {
			document = document.concat(" ");
		}

		// mapProb.addSentence(document);

		String[] words = document.split("\\s+");
		for (int i = 0; i < words.length - 1; i++) {
			String key = words[i] + " " + words[i + 1];
			Double[] value = new Double[] { mapProb.getProbByBigram(key.split("\\s+")),
					new Double(mapProb.indexOfBigram(key.split("\\s+"))) };
			values.add(value);
		}

		return values;
	}
}
