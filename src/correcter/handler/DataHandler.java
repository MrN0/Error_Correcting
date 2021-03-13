package correcter.handler;

import java.io.IOException;

/**
 * Interface for file processing classes
 */
public interface DataHandler {

	/**
	 * This method reads the file, then handles it and writes the result to the
	 * source file
	 *
	 * @param inputFile		the {@link String} input file name
	 * @param outputFile	the {@link String} output file name
	 * @throws IOException	if an I/O error occurs
	 */
	void handle(String inputFile, String outputFile) throws IOException;

}
