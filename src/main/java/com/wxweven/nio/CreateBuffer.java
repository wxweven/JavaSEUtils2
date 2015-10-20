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
public class CreateBuffer {
	static public void main(String args[]) throws Exception {
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		buffer.put((byte) 'a');
		buffer.put((byte) 'b');
		buffer.put((byte) 'c');

		buffer.flip();

		System.out.println((char) buffer.get());
		System.out.println((char) buffer.get());
		System.out.println((char) buffer.get());
	}
}
