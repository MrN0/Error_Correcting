package correcter;

import java.io.IOException;

import correcter.handler.DataHandler;

/**
 * This class is the context of the strategy pattern. It delegates execution to
 * an instance of a concrete strategy through its interface.
 */
public class Corrector {

	private DataHandler handler;

	public void set(DataHandler handler) {
		this.handler = handler;
	}

	public void handle(String inputFile, String outputFile) throws IOException {
		this.handler.handle(inputFile, outputFile);
	}

}
