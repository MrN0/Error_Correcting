package correcter;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import correcter.handler.ByteErrorGenerator;
import correcter.handler.HammingDecoder;
import correcter.handler.HammingEncoder;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Write a mode: ");
		String mode = scanner.nextLine();
		scanner.close();

		Corrector corrector = new Corrector();

		try {
			switch (mode) {
			case "encode":
				corrector.set(new HammingEncoder());
				corrector.handle("send.txt", "encoded.txt");
				break;
			case "send":
				corrector.set(new ByteErrorGenerator());
				corrector.handle("encoded.txt", "received.txt");
				break;
			case "decode":
				corrector.set(new HammingDecoder());
				corrector.handle("received.txt", "decoded.txt");
				break;
			default:
				throw new InputMismatchException(String.format("There is no %s mode%n", mode));
			}
		} catch (InputMismatchException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error : Something went wrong with the file " + e.getMessage());
			e.printStackTrace();
		}
	}

}
