package org.crawler.nio.example;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * three function to compare read file
 * 
 * @author tianzx
 * 
 */
public class FileReadCompare {

	public static void readFileIo(String path) throws IOException {
		// start time;
		long start = System.currentTimeMillis();
		File file = new File(path);
		BufferedReader bf = null;
		FileReader fr = null;
		if (null == file) {
			throw new FileNotFoundException("文件木有找到。。。");
		} else if (false == file.isFile()) {
			throw new FileNotFoundException("不是文件。。。");
		} else {
			fr = new FileReader(file);
			bf = new BufferedReader(fr);
			System.out
					.println("========================== 传统IO读取数据，使用虚拟机堆内存 ==========================");
			String line = bf.readLine();
			try {
				while (bf.readLine() != null) {
					System.err.println(line);
					line = bf.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				fr.close();
				bf.close();
				long end = System.currentTimeMillis();// 结束时间
				System.out.println("传统IO读取数据，不指定缓冲区大小，总共耗时：" + (end - start)
						+ "ms");
			}
		}
	}
	
	public static void readFileNIo(String path) {
		long start = System.currentTimeMillis();
		int bufferSize = 1024 * 1024 * 5;
		File file = new File(path);
		try {
			FileChannel fc= new RandomAccessFile(file, "r").getChannel();
			ByteBuffer bf = ByteBuffer.allocate(bufferSize);
			String enterStr = "\n";
			long len = 0L;
			
			byte[] bs = new byte[bufferSize];
			String tempString = null;
			while(fc.read(bf)!=-1){
				 int rSize = bf.position();
				 bf.rewind();
				 bf.get(bs);
				 bf.clear();
				 tempString = new String(bs,0,rSize);
				 int fromIndex = 0;
				 int endIndex = 0;
				 while((endIndex = tempString.indexOf(enterStr,fromIndex))!=-1){
					 String line = tempString.substring(fromIndex,endIndex);
					 System.err.println(line);
					 fromIndex = endIndex +1;
				 }
			}
			long end = System.currentTimeMillis();
			System.err.println("传统IO读取数据，制定缓冲区大小，总共耗时："+(end-start)+"ms");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void readFileMMF(String path) {
		long start = System.currentTimeMillis();
		long fileLength = 0;
		final int BUFFER_SIZE = 0x500000;
		File file = new File(path);
		fileLength = file.length();
		
		try {
			MappedByteBuffer inputBuffer = new RandomAccessFile(file, "r").getChannel().map(FileChannel.MapMode.READ_ONLY, 0, fileLength);
			byte[] bs = new byte[BUFFER_SIZE];
			for(int offset=0;offset <fileLength;offset+=BUFFER_SIZE){
				if (fileLength-offset>=BUFFER_SIZE){
					for(int i=0;i<BUFFER_SIZE;i++){
						bs[i] = inputBuffer.get(offset+i);
					}
				}else{
					for(int i=0;i<fileLength-offset;i++){
						bs[i] = inputBuffer.get(offset+i);
					}
				}
				Scanner scan = new Scanner(new ByteArrayInputStream(bs)).useDelimiter(" ");
				while(scan.hasNext()){
					System.err.println(scan.next()+"");
				}
				scan.close();
			}
			System.err.println();
			long end = System.currentTimeMillis();
			System.err.println("NIO 内存映射读大文件，总共耗时：" +(end-start)+"ms");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) throws IOException {
			FileReadCompare.readFileIo("D://Program Files (x86)//2015 学习书籍//Nosql//七周七语言 理解多种编程范型_13010643.pdf");
	}
}
