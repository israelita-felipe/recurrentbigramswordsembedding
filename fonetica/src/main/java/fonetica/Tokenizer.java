package fonetica;

public class Tokenizer {

	public static String[] tokenize(String text) {
		return text.split("\\s+");
	}
}
