package correcter.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the "Hamming Code (7,4)" encoder.
 */
public class HammingEncoder extends AbstractDataHandler {

    @Override
    public void handle(String inputFile, String outputFile) throws IOException {
        readFileAsByteArray(inputFile);
        printDataViews(inputFile,
                "text view: " + textView(),
                "hex view: " + hexView(),
                "bin view: " + binView());

        expand();
        setParityBits();
        writeToFile(outputFile);
        printDataViews(outputFile,
                "expand: " + expandView(),
                "parity: " + binView(),
                "hex view: " + hexView());
    }

    /**
     * This method expands the byte array
     */
    private void expand() {
        List<Byte> expandedBytes = new ArrayList<>();
        for (byte aByte : this.data) {
            expandedBytes.add(createExpandedByteOfFourBits((byte) (aByte >>> 4)));
            expandedBytes.add(createExpandedByteOfFourBits(aByte));
        }
        this.data = toByteArray(expandedBytes);
    }

    /**
     * This method expands the byte using its first four bits
     * expanded byte = p1 p2 d3 p4 d5 d6 d7 p8,
     * where pX = parity bit an dX are normal bits
     *
     * @param aByte the byte to expand
     * @return      the expanded byte
     */
    private byte createExpandedByteOfFourBits(byte aByte) {
        int bitsInByte = 8;
        int parityIndex = 1;

        // start from the second half of the byte
        int bitPointer = 3;

        byte expandedByte = 0;
        for (int i = 1; i <= bitsInByte; i++) {
            // skip the parity bit
            if (parityIndex == i) {
                parityIndex *= 2;
            } else {
                expandedByte += (aByte >> bitPointer & 1) << bitsInByte - i;
                bitPointer--;
            }
        }
        return expandedByte;
    }

    /**
     * This method calculates and sets parity bits for each byte
     */
    private void setParityBits() {
        for (int i = 0; i < this.data.length; i++) {
            int bitsInByte = 8;
            int parityIndex = 1;
            while (parityIndex < bitsInByte) {
                this.data[i] += calculateParity(this.data[i], parityIndex) << bitsInByte - parityIndex;
                parityIndex *= 2;
            }
        }
    }

    /**
     * This method calculates a given parity in a given byte
     *
     * @param aByte         the byte to calculate
     * @param parityIndex   the parity index
     * @return              the calculated parity
     */
    private byte calculateParity(byte aByte, int parityIndex) {
        int bitsInByte = 8;
        int nextSegment = parityIndex * 2;
        boolean parityBit = true;

        byte calculatedParity = 0;
        // segments of parity bits
        for (int i = parityIndex; i < bitsInByte; i += nextSegment) {
            // sequence of bits for calculation
            for (int j = 0; j < parityIndex; j++) {
                // skip the first bit which is the parity bit
                if (parityBit) {
                    parityBit = false;
                } else {
                    int position = bitsInByte - i - j;
                    calculatedParity ^= aByte >> position & 1;
                }
            }
        }
        return  calculatedParity;
    }

}
