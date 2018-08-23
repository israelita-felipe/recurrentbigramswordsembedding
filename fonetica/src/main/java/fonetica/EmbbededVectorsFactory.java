package fonetica;

import java.util.ArrayList;
import java.util.List;

public class EmbbededVectorsFactory {

	public static Double[] embbed(List<Double[]> values) {

		while (values.size() > 1) {
			List<Double[]> newValues = new ArrayList<>();

			VecToBigramProb bigramProb = new VecToBigramProb();
			bigramProb.addSentence(values);

			for (int i = 0; i < values.size() - 1; i++) {

				// get vector in sequence
				// captura os vetores na sequência

				Double[] v1 = values.get(i);
				Double[] v2 = values.get(i + 1);

				// create a new vector
				// cria um novo vetor

				/*
				 * [ p(wi|wi-1)/p(wi-1), (I(wi)-I(wi-1))/I(wi,wi-1)]
				 */
				double bigramIndex = bigramProb.indexOfBigram(new Double[][] { v1, v2 });
				double probV1V2 = bigramProb.getProbByBigram(new Double[][] { v1, v2 });

				// System.out.println("probv1v2 -> "+probV1V2);

				Double[] newVector = null;

				if (v1[0] > 0) {

					Double logProb = probV1V2 / v1[0];

					logProb = logProb.isInfinite() ? 1 : logProb;

					if (bigramIndex != 0) {
						newVector = new Double[] { logProb, (v2[1] - v1[1]) / bigramIndex };
					} else {
						double length = Math.max(Math.abs(v2[1]), Math.abs(v1[1]));
						length = length > 0 ? length : 1;

						newVector = new Double[] { logProb, (v2[1] - v1[1]) / length };
					}
				} else {

					Double logProb = probV1V2;
					logProb = logProb.isInfinite() ? 1 : logProb;

					if (bigramIndex != 0) {
						newVector = new Double[] { logProb, (v2[1] - v1[1]) / bigramIndex };
					} else {
						double length = Math.max(Math.abs(v2[1]), Math.abs(v1[1]));
						length = length > 0 ? length : 1;

						newVector = new Double[] { logProb, (v2[1] - v1[1]) / length };
					}
				}
				// System.out.println("new -> " + Arrays.toString(newVector));
				// add the new value to new vector with n-1 dimension
				// adiciona o novo valor aos vetores para formação de dimensão n-1
				newValues.add(newVector);
			}
			// the current values is now n-1 new vector dimensional
			// o valor corrente é agora o novo vetor de dimensão n-1
			values.clear();
			values.addAll(newValues);
		}
		// return when the values has only one element
		// retorn quanto a lista tem apenas um vetor
		return values.get(0);
	}

	public static Double euclidianDistance(Double[][] m1, Double[][] m2) {
		Double value = 0.0;
		for (int line = 0; line < m1.length; line++) {
			for (int col = 0; col < m1[0].length; col++) {
				value += (Math.pow(m2[line][col] - m1[line][col], 2));
			}
		}
		return Math.sqrt(value);
	}

	/**
	 * based on
	 * https://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
	 * 
	 * @param wordA
	 * @param wordB
	 * @return
	 */
	public static int editDistance(String wordA, String wordB) {
		int lenA = wordA.length();
		int lenB = wordB.length();
		int buffer[][] = new int[lenA + 1][lenB + 1];

		for (int i = 0; i <= lenA; i++) {
			for (int j = 0; j <= lenB; j++) {

				// if i == 0 (wordA is empty for iteration) then store j (wordB)
				if (i == 0) {
					buffer[i][j] = j;
				}
				// if j == 0 (wordB is empty for iteration) then store i (wordA)
				else if (j == 0) {
					buffer[i][j] = i;
				}
				// if the chars at i-1 and j-1 are equals just update the i,j position with the
				// previous distance
				// because this distance is 0!
				else if (wordA.charAt(i - 1) == wordB.charAt(j - 1)) {
					buffer[i][j] = buffer[i - 1][j - 1];
				}
				// if not the before conditions...
				// so add 1 and the min using inserts, removes and replaces...
				else {
					buffer[i][j] = 1 + min(buffer[i][j - 1], buffer[i - 1][j], buffer[i - 1][j - 1]);
				}
			}
		}

		return buffer[lenA][lenB];
	}

	/**
	 * convenient method to get the min from three operators
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @return the min for a, b and c
	 */
	public static int min(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}

	public static Double cos(Double[] v, Double[] u) {
		return prod(v, u) / ((norma(v)) * (norma(u)));
	}

	public static Double prod(Double[] v, Double[] u) {
		Double value = 0.0;
		for (int i = 0; i < v.length; i++) {
			value = value + (v[i] * u[i]);
		}
		return value;
	}

	public static Double norma(Double[] v) {
		Double value = 0.0;
		for (int i = 0; i < v.length; i++) {
			value = value + (v[i] * v[i]);
		}
		return Math.sqrt(value);
	}
}
