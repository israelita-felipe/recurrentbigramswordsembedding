package fonetica;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ArffLoader;
import weka.core.converters.CSVLoader;

public class POSTagger {

	private final DocToVec d2v;
	private final Classifier classifier;

	public POSTagger(DocToVec docToVec) throws FileNotFoundException, Exception {
		this.d2v = docToVec;
		this.classifier = (Classifier) SerializationHelper
				.read(new FileInputStream("models/pos/pos.random_forest.model"));
	}

	public List<String> getPOSTagger(String text) throws Exception {

		String[] tokens = Tokenizer.tokenize(d2v.preProcess(text));
		List<String> taggedText = new ArrayList<>();

		for (int i = 0; i < tokens.length - 1; i++) {

			Double[] bigram = d2v.getBigramVectorsFrom(tokens[i] + " " + tokens[i + 1]).get(0);

			taggedText.add(tokens[i] + "/" + classify(bigram));

			// adicionando a ultima palavra
			// adding the last word
			if (i == tokens.length - 2) {

				bigram = d2v.getBigramVectorsFrom(tokens[i + 1]).get(0);

				taggedText.add(tokens[i + 1] + "/" + classify(bigram));
			}
		}
		return taggedText;
	}

	private String classify(Double[] bigram) throws Exception {
		StringBuilder sb = new StringBuilder("prob_word\tindex_word\ttag\n");
		sb.append(String.format("%.20f\t%.20f\t?\n", bigram[0], bigram[1]));

		CSVLoader loader = new CSVLoader();
		loader.setSource(new ByteArrayInputStream(sb.toString().replaceAll("0,", "0.").getBytes()));

		ArffLoader arffLoader = new ArffLoader();
		arffLoader.setSource(new File("posTagger.arff"));

		loader.setFieldSeparator("\t");
		loader.setNumericAttributes("1-2");

		Instances instances = loader.getDataSet();
		Instances arffIntances = arffLoader.getStructure();

		arffIntances.add(instances.firstInstance());

		arffIntances.setClassIndex(instances.numAttributes() - 1);

		Instance instance = arffIntances.firstInstance();

		return arffIntances.classAttribute().value((int) classifier.classifyInstance(instance));
	}
}
