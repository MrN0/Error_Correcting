package correcter.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * This abstract class contains general methods and fields for classes that
 * extend it
 */
abstract class AbstractDataHandler implements DataHandler {
	// data for processing
	protected byte[] data;

	/**
	 * This method reads the file and stores the data in a byte array
	 *
	 * @param fileName		the {@link String} file name
	 * @throws IOException	if an I/O error occurs
	 */
	protected void readFileAsByteArray(String fileName) throws IOException {
		data = Files.readAllBytes(Paths.get(fileName));
	}

	/**
	 * This method writes data from a byte array to a file
	 *
	 * @param fileName		the {@link String} file name
	 * @throws IOException	if an I/O error occurs
	 */
	protected void writeToFile(String fileName) throws IOException {
		File file = new File(fileName);
		try (OutputStream outputStream = new FileOutputStream(file, false)) {
			for (byte aByte : data) {
				outputStream.write(aByte);
			}
		}
	}

	/**
	 * This method represents a byte array in the form of text
	 *
	 * @return the {@link String} converted data
	 */
	protected String textView() {
		return new String(this.data, StandardCharsets.UTF_8);
	}

	/**
	 * This method represents a byte array in the form of hexadecimal numbers
	 *
	 * @return the {@link String} converted data
	 */
	protected String hexView() {
		StringBuilder sb = new StringBuilder();
		for (byte aByte : this.data) {
			sb.append(String.format("%02X", aByte)).append(" ");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	/**
	 * This method represents a byte array in the form of binary numbers
	 *
	 * @return the {@link String} converted data
	 */
	protected String binView() {
		StringBuilder sb = new StringBuilder();
		for (byte aByte : this.data) {
			String toBinary = toBinary(aByte);
			sb.append(toBinary).append(" ");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	/**
	 * This method represents a byte array as binary numbers, showing only extended
	 * bits without parity bits
	 *
	 * @return the {@link String} converted data
	 */
	protected String expandView() {
		StringBuilder sb = new StringBuilder();
		for (byte aByte : this.data) {
			char[] toBinary = toBinary(aByte).toCharArray();

			int bitsInByte = 8;
			int parityIndex = 1;
			for (int i = 0; i < bitsInByte; i++) {
				if (parityIndex == i + 1) {
					toBinary[i] = '.';
					parityIndex *= 2;
				}
			}
			sb.append(toBinary).append(" ");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	/**
	 * This method prints the specified data representations to the console
	 *
	 * @param fileName	the {@link String} file name
	 * @param views		the list of {@link String} data views
	 */
	protected void printDataViews(String fileName, String... views) {
		System.out.println(fileName + ":");
		for (String view : views) {
			System.out.println(view);
		}
		System.out.println();
	}

	/**
	 * This method converts the byte list to an array
	 *
	 * @param list	the {@link List} of bytes
	 * @return		the {@code byte} array
	 */
	protected byte[] toByteArray(List<Byte> list) {
		byte[] array = new byte[list.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = list.get(i);
		}
		return array;
	}

	/**
	 * This method represents a byte as an 8-bit binary number
	 *
	 * @param aByte	the byte for representation
	 * @return		the {@link String} representation of the byte 
	 *         		as an 8-bit binary number
	 */
	private String toBinary(byte aByte) {
		return String.format("%8s", Integer.toBinaryString(aByte & 0xff)).replace(" ", "0");
	}

}
