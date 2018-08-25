package fonetica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.Test;

public class POSTaggerTest {

//	@Test
	public void posTaggerTest() throws IOException {
		Scanner sc = new Scanner(new FileInputStream("dataset/corpus.txt"));

		DocToBigramProb d2bp = new DocToBigramProb();

		Scanner database = new Scanner(new FileInputStream(new File("Constituicao de 1988.txt")), "UTF-8");
		while (database.hasNextLine()) {
			d2bp.addSentence((database.nextLine()));
		}
		database.close();

		DocToVec d2v = new DocToVec(d2bp);

		FileWriter fw = new FileWriter("posTagger.txt");
		fw.write("prob_word\tindex_word\ttag\n");
		while (sc.hasNextLine()) {
			String[] sentence = sc.nextLine().trim().split("\\s+");
			for (int i = 0; i < sentence.length - 1; i++) {

				String[] word1 = sentence[i].split("_");
				String[] word2 = sentence[i + 1].split("_");

				System.out.println("--------------------");
				System.out.println(Arrays.toString(word1));
				System.out.println(Arrays.toString(word2));
				System.out.println("--------------------");

				String token = word1[0] + " " + word2[0];
				String tag = word1[1];

				Double[] vector = d2v.getBigramVectorsFrom(token).get(0);
				if (vector[0] != 0) {
					fw.write(String.format("%.20f\t%.20f\t%s\n", vector[0], vector[1], tag));
				}

			}
		}

		fw.close();
		sc.close();
	}
	
	@Test
	public void posTaggerClassifierTest() throws Exception {
		DocToBigramProb d2bp = new DocToBigramProb();

		Scanner database = new Scanner(new FileInputStream(new File("Constituicao de 1988.txt")), "UTF-8");
		while (database.hasNextLine()) {
			d2bp.addSentence((database.nextLine()));
		}
		database.close();

		DocToVec d2v = new DocToVec(d2bp);
		System.out.println(new POSTagger(d2v).getPOSTagger("Recente destaque na imprensa nacional e internacional foi o ataque de moradores do município de Pacaraima/RR a imigrantes venezuelanos que se aglomeravam em espaços públicos."));
	}
}
