package fonetica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class DocToVecTest {

	// @Test
	public void getBigramVectors() throws FileNotFoundException {
		DocToBigramProb d2bp = new DocToBigramProb();

		Scanner sc = new Scanner(new FileReader("dataset-lei.txt"));
		while (sc.hasNextLine()) {
			d2bp.addSentence(sc.nextLine());
		}
		sc.close();
		DocToVec d2v = new DocToVec(d2bp);

		for (Double[] value : d2v.getBigramVectorsFrom("abescorpus")) {
			System.out.println(Arrays.toString(value));
		}
	}

	// @Test
	public void embbedVectors() throws FileNotFoundException {
		DocToBigramProb d2bp = new DocToBigramProb();

		Scanner sc = new Scanner(new FileReader("dataset-lei.txt"));
		while (sc.hasNextLine()) {
			d2bp.addSentence(sc.nextLine());
		}
		sc.close();
		DocToVec d2v = new DocToVec(d2bp);

		List<Double[]> values = d2v.getBigramVectorsFrom("o menino não gosta de leite");
		for (Double[] value : values) {
			System.out.println(Arrays.toString(value));
		}

		System.out.println("gato\n");
		Double[] gato = EmbbededVectorsFactory.embbed(values);
		System.out.println(Arrays.toString(gato) + "\n----------");

		values = d2v.getBigramVectorsFrom("a menina gosta de chupar chiclete");
		for (Double[] value : values) {
			System.out.println(Arrays.toString(value));
		}

		System.out.println("pato");
		Double[] pato = EmbbededVectorsFactory.embbed(values);
		System.out.println(Arrays.toString(pato) + "\n----------");

		values = d2v.getBigramVectorsFrom("tato sente algo. o menino tem tato. a menina tem tato");
		for (Double[] value : values) {
			System.out.println(Arrays.toString(value));
		}

		System.out.println("tato");
		Double[] tato = EmbbededVectorsFactory.embbed(values);
		System.out.println(Arrays.toString(tato) + "\n----------");

		System.out.println("distancias");

		System.out.println("gato-pato " + EmbbededVectorsFactory.cos(gato, pato));
		System.out.println("gato-tato " + EmbbededVectorsFactory.cos(gato, tato));
		System.out.println("tato-pato " + EmbbededVectorsFactory.cos(tato, pato));
	}

	@Test
	public void embbedFoneticVectors() throws FileNotFoundException {

		DocToBigramProb d2bp = new DocToBigramProb();

		Scanner sc = new Scanner(new FileInputStream(new File("Constituicao de 1988.txt")), "UTF-8");
		while (sc.hasNextLine()) {
			d2bp.addSentence((sc.nextLine()));
		}
		sc.close();

		d2bp.getBigrams().forEach(b -> {
			System.out.println(b);
		});

		DocToVec d2v = new DocToVec(d2bp);

		List<Double[]> values = d2v.getBigramVectorsFrom(
				("Numa acepção amplíssima, lei é toda a regra jurídica, escrita ou não; aqui ela abrange os costumes e todas as normas formalmente produzidas pelo Estado, representadas, por exemplo, pela Constituição federal, medida provisória, decreto, lei ordinária, lei complementar, etc."));

		System.out.println("gato\n");
		Double[] gato = EmbbededVectorsFactory.embbed(values);
		System.out.println(Arrays.toString(gato) + " -> " + d2bp.getBigramFromIndex(gato[1]) + "\n----------");

		values = d2v.getBigramVectorsFrom(
				("Já num sentido amplo, lei é somente a regra jurídica escrita, excluindo-se dessa aceção, portanto, o costume jurídico."));

		System.out.println("pato");
		Double[] pato = EmbbededVectorsFactory.embbed(values);
		System.out.println(Arrays.toString(pato) + " -> " + d2bp.getBigramFromIndex(pato[1]) + "\n----------");

		values = d2v.getBigramVectorsFrom(
				("Por fim, numa acepção técnica ou estrita, a palavra lei designa uma modalidade de regra escrita que apresenta determinadas características; no direito brasileiro, são leis em sentido estrito apenas a lei complementar e a lei ordinária."));

		System.out.println("tato");
		Double[] tato = EmbbededVectorsFactory.embbed(values);
		System.out.println(Arrays.toString(tato) + " -> " + d2bp.getBigramFromIndex(tato[1]) + "\n----------");

		System.out.println("distancias");

		System.out.println("gato-pato " + (1 - Math.abs(EmbbededVectorsFactory.cos(gato, pato))));
		System.out.println("gato-tato " + (1 - Math.abs(EmbbededVectorsFactory.cos(gato, tato))));
		System.out.println("tato-pato " + (1 - Math.abs(EmbbededVectorsFactory.cos(tato, pato))));
	}
}
