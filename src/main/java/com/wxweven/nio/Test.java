package com.wxweven.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author wxweven
 * @date 2014年9月14日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:
 */
public class Test {

	public static void main(String[] args) throws Exception {
		transferTo();
	}

	private static void transferTo() throws Exception {
		RandomAccessFile fromFile = new RandomAccessFile("data/fromFile.txt", "rw");
		FileChannel fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("data/toFile.txt", "rw");
		FileChannel toChannel = toFile.getChannel();

		long position = 0;
		long count = fromChannel.size();

		fromChannel.transferTo(position, count, toChannel);
		
		fromFile.close();
		toFile.close();
	}

}
