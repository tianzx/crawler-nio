package org.crawler.nio.think;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelCopy {

	public static  int BSIZE = 1024;
	
	public static void main(String[] args) {
		File file = null;
		File file2 = null;
		try {
			file = CommonUtils.getFile("data.txt");
			file2 = CommonUtils.getFile("data2.txt");
		} catch (ArgumentsException e1) {
			e1.printStackTrace();
		}
		try {
			FileChannel in = new FileInputStream(file).getChannel();
			FileChannel out = new FileOutputStream(file2).getChannel();
			ByteBuffer bb = ByteBuffer.allocate(BSIZE);
			while(in.read(bb)!=-1){
				bb.flip();
				out.write(bb);
				bb.clear();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
