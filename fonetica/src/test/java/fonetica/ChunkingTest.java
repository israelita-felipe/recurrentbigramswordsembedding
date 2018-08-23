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

		Scanner sc = new Scanner(new FileInputStream(new File("chunkingDataset.txt")));
		OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(new File("LawDomainChunking.txt")));
		while (sc.hasNextLine()) {
			String[] sentences = d2bp.splitPossibleSentences(sc.nextLine());
			for (String sentence : sentences) {
				System.out.println(sentence);
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
