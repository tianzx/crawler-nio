package org.crawler.nio.think;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GetChannel {

	private static final int BSIZE = 1024;
	
	private String name;
	
	public GetChannel(String name){
		this.name = name;
	}
	public File getFile() {
		String filePath = this.getClass().getResource("/").getPath() + this.name;
//		System.err.println(filePath);
		File file = new File(filePath);
		return file;
	}
	public static void main(String[] args) throws IOException {
		GetChannel channel = new GetChannel("data.txt");
		FileChannel fc = new FileOutputStream(channel.getFile()).getChannel();
		fc.write(ByteBuffer.wrap("tianzx v5".getBytes()));
		fc.close();
		fc = new RandomAccessFile(channel.getFile(), "rw").getChannel();
		fc.write(ByteBuffer.wrap("this is a big news ".getBytes()));
		fc.close();
		fc = new FileInputStream(channel.getFile()).getChannel();
		ByteBuffer bf = ByteBuffer.allocate(BSIZE);
		fc.read(bf);
		bf.flip();
		while(bf.hasRemaining()){
			System.err.println(bf.getChar());
		}
	}
}
