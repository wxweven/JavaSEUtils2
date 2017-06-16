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

/*
 * 一、使用 NIO 完成网络通信的三个核心：
 *
 * 1. 通道（Channel）：负责连接
 *
 * java.nio.channels.Channel 接口：
 *             |--SelectableChannel
 *                 |--SocketChannel
 *                 |--ServerSocketChannel
 *                 |--DatagramChannel
 *
 *                 |--Pipe.SinkChannel
 *                 |--Pipe.SourceChannel
 *
 * 2. 缓冲区（Buffer）：负责数据的存取
 *
 * 3. 选择器（Selector）：是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况
 *
 */
public class TestBlockingNIO {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestBlockingNIO.class);

    private static final String INPUT_PIC_NAME = "1.jpg";
    private static final String OUTPUT_PIC_NAME = "2.jpg";
    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 9898;
    private static final int BUFFER_SIZE = 1024 << 2;


    // 客户端
    @Test
    public void client() throws IOException {
        // 1. 获取文件通道
        String path = TestBlockingNIO.class.getClassLoader().getResource(INPUT_PIC_NAME).getPath();
        LOGGER.debug("input file path:{}", path);
        FileChannel fileChannel = FileChannel.open(Paths.get(path), StandardOpenOption.READ);

        // 2. 获取socket通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(SERVER_HOST, SERVER_PORT));

        // 3. 分配缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

        // 4. 利用缓冲区循环读写
        while (fileChannel.read(buffer) != -1) {
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }

        LOGGER.debug("send finish...");

        // 5. 关闭资源
        fileChannel.close();
        socketChannel.close();
    }

    // 服务端
    @Test
    public void server() throws IOException {
        // 1. 获取文件通道
        String path = TestBlockingNIO.class.getClassLoader().getResource(".").getPath();

        LOGGER.debug("output file path:{}", path);
        FileChannel fileChannel = FileChannel.open(Paths.get(path, OUTPUT_PIC_NAME), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        // 2. 获取服务端socket通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();

        // 3. 绑定端口
        serverChannel.bind(new InetSocketAddress(SERVER_PORT));

        // 4. 接收客户端连接
        SocketChannel socketChannel = serverChannel.accept();

        // 5. 分配缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

        // 6. 利用缓冲区读写文件
        while (socketChannel.read(buffer) != -1) {
            buffer.flip();
            fileChannel.write(buffer);
            buffer.clear();
        }

        // 7. 关闭资源
        socketChannel.close();
        serverChannel.close();
        fileChannel.close();
    }
}
