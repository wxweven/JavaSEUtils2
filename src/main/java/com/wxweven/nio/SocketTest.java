package com.wxweven.nio;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by wxweven
 * on 2017/5/19.
 */
public class SocketTest {

    private static final Logger LOGGER  = LoggerFactory.getLogger(SocketTest.class);

    @Test
    public void client() throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8888));

        FileChannel fileChannel = FileChannel.open(Paths.get("/Users/wxweven/gitwork/JavaSEUtils/target/classes", "/1.png"), StandardOpenOption.READ);

        ByteBuffer buffer = ByteBuffer.allocate(1024 * 4);

        LOGGER.debug("ready to send socket...");
        while (fileChannel.read(buffer) != -1) {
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }

        LOGGER.debug("finish sending socket...");

        socketChannel.close();
        fileChannel.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8888));

        LOGGER.debug("ready to receive socket...");
        SocketChannel socketChannel = serverSocketChannel.accept();
        ByteBuffer buffer = ByteBuffer.allocate(1024*4);

        FileChannel fileChannel = FileChannel.open(Paths.get("2.png"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        while (socketChannel.read(buffer) != -1) {
            buffer.flip();
            fileChannel.write(buffer);
            buffer.clear();
        }

        LOGGER.debug("finish receiving socket...");

        fileChannel.close();
        serverSocketChannel.close();
        socketChannel.close();
    }
}
