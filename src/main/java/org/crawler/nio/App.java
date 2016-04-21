package org.crawler.nio;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		File file = new File("../");
		String[] lists = file.list(new DirFilter("src"));
		for(String str : lists) {
			System.err.println(str);
		}
		
		Arrays.sort(lists,String.CASE_INSENSITIVE_ORDER);
		for(String str : lists) {
			System.err.println(str);
		}
	}
}

class DirFilter implements FilenameFilter{

	private Pattern pattern;
	public DirFilter(String regex){
		pattern = Pattern.compile(regex);
	}
	@Override
	public boolean accept(File paramFile, String paramString) {
		return pattern.matcher(paramString).matches();
	}
	
}