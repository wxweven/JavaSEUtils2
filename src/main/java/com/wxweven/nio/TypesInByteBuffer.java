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
public class TypesInByteBuffer {
	static public void main(String args[]) throws Exception {
		ByteBuffer buffer = ByteBuffer.allocate(64);

		buffer.putInt(30);
		buffer.putLong(7000000000000L);
		buffer.putDouble(Math.PI);

		buffer.flip();

		System.out.println(buffer.getInt());
		System.out.println(buffer.getLong());
		System.out.println(buffer.getDouble());
	}
}
