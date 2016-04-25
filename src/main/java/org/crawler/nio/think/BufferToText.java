package org.crawler.nio.think;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferToText {

	private static final int BSIZE = 1024;
	
	public static void main(String[] args) throws ArgumentsException, FileNotFoundException {
		File file = CommonUtils.getFile("data.txt");
		try {
			FileChannel fc = new FileOutputStream(file).getChannel();
			fc.write(ByteBuffer.wrap("tianzx".getBytes()));
			fc.close();
			
			fc = new FileInputStream(file).getChannel();
			ByteBuffer bf = ByteBuffer.allocateDirect(BSIZE);
			fc.read(bf);
			bf.flip();
			System.out.println(bf.asCharBuffer());
			bf.rewind();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
