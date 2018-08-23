package fonetica;

public abstract class PreProcessable<T> {

	abstract String preProcess(T value);

	abstract String[] splitPossibleSentences(String value);
}
