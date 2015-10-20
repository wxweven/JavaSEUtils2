package com.wxweven.nio;

import java.nio.*;

public class UseFloatBuffer {
	static public void main(String args[]) throws Exception {
		FloatBuffer buffer = FloatBuffer.allocate(10);

		for (int i = 0; i < buffer.capacity(); ++i) {
			float f = (float) Math.sin((((float) i) / 10) * (2 * Math.PI));
			buffer.put(f);
		}

		/**
		 * flip()源码：
		 */
//		public final Buffer flip() {
//        	limit = position;
//        	position = 0;
//        	mark = -1;
//        	return this;
//    	}
		/**
		 * 反转缓冲区。首先将限制设置为当前位置，然后将位置设置为 0。
		 * 如果已定义了标记，则丢弃该标记。 常与compact方法一起使用。
		 * 通常情况下，在准备从缓冲区中读取数据时调用flip方法。
		 */
		buffer.flip();

		while (buffer.hasRemaining()) {
			float f = buffer.get();
			System.out.println(f);
		}
	}
}
