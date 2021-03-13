package correcter.handler;

import java.io.IOException;
import java.util.Random;

/**
 * This class is an error generator.
 */
public class ByteErrorGenerator extends AbstractDataHandler {

    @Override
    public void handle(String inputFile, String outputFile) throws IOException {
        readFileAsByteArray(inputFile);
        printDataViews(inputFile,
                "hex view: " + hexView(),
                "bin view: " + binView());

        generateErrors();

        writeToFile(outputFile);
        printDataViews(outputFile,
                "bin view: " + binView(),
                "hex view: " + hexView());
    }

    /**
     * This method generates one error per byte
     */
    private void generateErrors() {
        Random random = new Random();
        for (int i = 0; i < this.data.length; i++) {
            int shift = random.nextInt(7);
            this.data[i] = (byte) (this.data[i] ^ (1 << shift));
        }
    }

}
