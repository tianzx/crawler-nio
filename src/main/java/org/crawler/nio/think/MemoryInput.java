package org.crawler.nio.think;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MemoryInput {

	public static void main(String[] args) throws MyException {
		try {
			DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("D:/WorkSpace/crawler-web/crawler-nio/src/main/java/org/crawler/nio/think/data.txt")));
			out.writeDouble(3.14159);
			out.writeUTF("That was pi");
			out.writeDouble(1.41413);
			out.writeUTF("Square root of 2");
			out.close();
			DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("D:/WorkSpace/crawler-web/crawler-nio/src/main/java/org/crawler/nio/think/data.txt")));
			System.err.println(in.readDouble());
			System.err.println(in.readUTF());
			System.err.println(in.readDouble());
			System.err.println(in.readUTF());
		} catch (FileNotFoundException e) {
			throw new MyException("沒找到啊。。。。。");
		} catch (IOException e) {
		}
		
	}
}
