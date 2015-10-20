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
public class SliceBuffer {
	static public void main(String args[]) throws Exception {
		ByteBuffer buffer = ByteBuffer.allocate(10);

		for (int i = 0; i < buffer.capacity(); ++i) {
			buffer.put((byte) i);
		}

		buffer.position(3);
		buffer.limit(7);

		ByteBuffer slice = buffer.slice();

		for (int i = 0; i < slice.capacity(); ++i) {
			byte b = slice.get(i);
			b *= 11;
			slice.put(i, b);
		}

		buffer.position(0);
		buffer.limit(buffer.capacity());

		while (buffer.hasRemaining()) {
			System.out.println(buffer.get());
		}
	}
}
