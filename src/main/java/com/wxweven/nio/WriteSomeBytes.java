package com.wxweven.nio;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

/**
 * @author wxweven
 * @version 1.0
 * @date 2014年9月14日
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:
 */
public class WriteSomeBytes {
    static private final byte message[] = {83, 111, 109, 101, 32, 98, 121, 116, 101, 115, 46};

    static public void main(String args[]) throws Exception {
        String curDir = WriteSomeBytes.class.getClassLoader().getResource("com/wxweven/nio/").getPath();
        String file = curDir + "writesomebytes.txt";
        FileOutputStream fout = new FileOutputStream(file);

        FileChannel fc = fout.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        for (int i = 0; i < message.length; ++i) {
            buffer.put(message[i]);
        }

        buffer.flip();

        fc.write(buffer);

        fout.close();
    }
}
