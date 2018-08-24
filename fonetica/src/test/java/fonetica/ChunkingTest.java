package fonetica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import org.junit.Test;

public class ChunkingTest {

	@Test
	public void generateChunkingDataset() throws IOException {
		DocToBigramProb d2bp = new DocToBigramProb();

		Scanner sc = new Scanner(new FileInputStream(new File("Constituicao de 1988.txt")), "UTF-8");
		while (sc.hasNextLine()) {
			d2bp.addSentence((sc.nextLine()));
		}
		sc.close();

		DocToVec d2v = new DocToVec(d2bp);

		sc = new Scanner(new FileInputStream(new File("chunkingDataset.txt")));
		OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(new File("LawDomainChunking.txt")));
		fw.write("doc_prob\tdoc_index\tsent_prob\tsent_index\tdocument\tsentence\tclass\n");
		while (sc.hasNextLine()) {
			String document = sc.nextLine();
			String[] sentences = d2bp.splitPossibleSentences(document);
			Double[] docVector = EmbbededVectorsFactory.embbed(d2v.getBigramVectorsFrom(document));
			for (String sentence : sentences) {
				System.out.println(sentence);
				Double[] sentenceVector = EmbbededVectorsFactory.embbed(d2v.getBigramVectorsFrom(sentence));
				fw.write(String.format("%.20f\t%.20f\t%.20f\t%.20f\t", docVector[0], docVector[1], sentenceVector[0],
						sentenceVector[1]));
				fw.write(document);
				fw.write("\t");
				fw.write(sentence);
				fw.write("\t?\n");
			}
		}
		sc.close();
		fw.close();

	}

	// @Test
	public void chunkingTest() {
		DocToBigramProb d2bp = new DocToBigramProb();

		String[] sentences = d2bp
				.splitPossibleSentences("Dr. Jr. é um médico. o gato é preto. o pombo de Dr. Jr. não tem pena.");
		for (String sentence : sentences) {
			System.out.println(sentence);
		}

	}
}
