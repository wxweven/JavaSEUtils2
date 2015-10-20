package com.wxweven.nio;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

/**
 * @author wxweven
 * @date 2014年9月14日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:
 */
public class FastCopyFile {
	static public void main(String args[]) throws Exception {
//		if (args.length < 2) {
//			System.err.println("Usage: java FastCopyFile infile outfile");
//			System.exit(1);
//		}

		String infile = "infile";//输入文件
		String outfile = "outfile";//输出文件

		FileInputStream fin = new FileInputStream(infile);//文件输入流
		FileOutputStream fout = new FileOutputStream(outfile);//文件输出流

		FileChannel fcin = fin.getChannel();//输入通道
		FileChannel fcout = fout.getChannel();//输出通道

		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);//直接缓冲区

		while (true) {
			buffer.clear();//每次读入缓冲区的时候，先清空缓冲区

			//Reads a sequence of bytes from this channel into the given buffer
			int r = fcin.read(buffer);

			if (r == -1) {
				break;
			}

			buffer.flip();//反转缓冲区，开始从缓冲区写入到输出通道

			fcout.write(buffer);
		}
	}
}
