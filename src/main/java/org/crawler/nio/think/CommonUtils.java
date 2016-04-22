package org.crawler.nio.think;

import java.io.File;

public class CommonUtils {

	public static File getFile(String filePath) throws ArgumentsException {
		if (null ==filePath ||"" ==filePath ){
			throw new ArgumentsException("参数传递有误。。。");
		}
		return new File(filePath);
	}
}
