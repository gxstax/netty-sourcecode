package com.ant.io.nio.tcp.handlers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * <p>
 * 服务端接收写请求
 * </p>
 *
 * @author Ant
 * @since 2021/12/17 5:13 下午
 */
public class WriteHandler implements ServerHandler {

    @Override
    public void doHandler(SelectionKey key) throws IOException {
        final SocketChannel sChannel = (SocketChannel) key.channel();
        final ByteBuffer buffer = (ByteBuffer) key.attachment();

        buffer.flip();
        while (buffer.hasRemaining()) {
            sChannel.write(buffer);
        }
        // 压缩缓冲区，也就是平移到缓冲区的开始位置
        buffer.compact();
    }

}
