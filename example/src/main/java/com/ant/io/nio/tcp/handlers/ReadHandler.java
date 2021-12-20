package com.ant.io.nio.tcp.handlers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * <p>
 * 服务端接收读操作
 * </p>
 *
 * @author Ant
 * @since 2021/12/17 5:13 下午
 */
public class ReadHandler implements ServerHandler {

    @Override
    public void doHandler(SelectionKey key) throws IOException {
        // TCP 通道
        final SocketChannel sChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        int bytesRead = sChannel.read(buffer);
        System.out.println("读取内容如下:");
        while (bytesRead > 0) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            System.out.println();
            buffer.clear();
            bytesRead = sChannel.read(buffer);
        }
        if (bytesRead == -1) {
            sChannel.close();
        }

    }
}
