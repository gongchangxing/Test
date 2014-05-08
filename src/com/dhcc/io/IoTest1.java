package com.dhcc.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

// ~ File Information
// ====================================================================================================================

public class IoTest1 {

	public static void main(String[] args) throws Exception {
		String fileName = "D:"+File.separator+"test.txt";
		File file = new File(fileName);
		OutputStream out = new FileOutputStream(file);
		String str = "hello world";
		byte[] bytes = str.getBytes();
		out.write(bytes);
		out.close();
	}

}
