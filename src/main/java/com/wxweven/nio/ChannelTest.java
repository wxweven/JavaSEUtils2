package com.wxweven.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*Channel的实现

 这些是Java NIO中最重要的通道的实现：

 FileChannel
 SocketChannel
 ServerSocketChannel
 
 FileChannel 从文件中读写数据。

 DatagramChannel 能通过UDP读写网络中的数据。

 SocketChannel 能通过TCP读写网络中的数据。

 ServerSocketChannel可以监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel。*/
public class ChannelTest {

	public static void main(String[] args) throws IOException {
		RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
		FileChannel inChannel = aFile.getChannel();

		ByteBuffer buf = ByteBuffer.allocate(1024);

//		int bytesRead = inChannel.read(buf);
//		while (bytesRead != -1) {
//
//			System.out.println("Read " + bytesRead);
//			buf.flip();
//
//			while (buf.hasRemaining()) {
//				System.out.print((char) buf.get());
//			}
//
//			buf.clear();
//			bytesRead = inChannel.read(buf);
//		}
		
		while(true){
			buf.clear();
			int bytesRead = inChannel.read(buf);
			System.out.println("Read " + bytesRead);
			
			if(bytesRead == -1){
				break;
			}
			
			buf.flip();
			while(buf.hasRemaining()){
				System.out.println((char)buf.get());
			}
		}

		aFile.close();

	}
}
