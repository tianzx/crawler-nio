package org.crawler.nio.think;

import java.io.IOException;
import java.io.RandomAccessFile;

public class UsingRandomAccessFile {

	private static String file = "./data.txt";

	private static void display() throws IOException {
		RandomAccessFile rf = new RandomAccessFile(file, "r");
		for (int i = 0; i < 7; i++) {
			System.err.println("Value" + i + "" + rf.readDouble());
		}
		System.err.println(rf.readUTF());
		rf.close();
	}

	public static void main(String[] args) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		for (int i = 0; i < 7; i++) {
			raf.writeDouble(i * 1.44);
		}
		raf.writeUTF("The End Of File");
		raf.close();
		display();
		raf = new RandomAccessFile(file, "rw");
		raf.seek(5 * 8);
		raf.writeDouble(47.0001);
		raf.close();
		display();
	}
}
