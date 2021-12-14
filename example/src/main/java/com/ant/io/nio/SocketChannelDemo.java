package com.ant.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * <p>
 * SocketChannel 示例
 * </p>
 *
 * @author Ant
 * @since 2021/12/9 11:46 上午
 */
public class SocketChannelDemo {

    public static void main(String[] args) throws IOException {
        demo();
    }

    public static void demo() throws IOException{
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress("localhost", 8080));

        String newData = "The new String is writing in a file ..." + System.currentTimeMillis();
        ByteBuffer bb= ByteBuffer.allocate(48);
        bb.clear();
        bb.put(newData.getBytes());
        bb.flip();
        while(bb.hasRemaining()) {
            channel.write(bb);
        }
    }

}
