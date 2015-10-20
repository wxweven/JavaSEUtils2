package com.wxweven.nio;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
/**
 * 
 * @author wxweven
 * @date 2014年9月14日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:
 */
public class CopyFile {
	static public void main(String args[]) throws Exception {
//		if (args.length < 2) {
//			System.err.println("Usage: java CopyFile infile outfile");
//			System.exit(1);
//		}

		String infile = "infile";
		String outfile = "outfile";

		FileInputStream fin = new FileInputStream(infile);
		FileOutputStream fout = new FileOutputStream(outfile);

		FileChannel fcin = fin.getChannel();
		FileChannel fcout = fout.getChannel();

		ByteBuffer buffer = ByteBuffer.allocate(1024);

		while (true) {
			buffer.clear();

			int r = fcin.read(buffer);

			if (r == -1) {
				break;
			}

			buffer.flip();

			fcout.write(buffer);
		}
	}
}
