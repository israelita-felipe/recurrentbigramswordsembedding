package fonetica;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class BigramMappingTest {

	@Test
	public void writeFoneticMapping() {
		BigramMapping mf = new BigramMapping();
		try {
			mf.fillMap("DICIONARIO_FONETICO.txt");
			mf.writeMapToFile("mapaFonetico.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
