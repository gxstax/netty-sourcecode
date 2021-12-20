package com.ant.io.nio.tcp.handlers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * <p>
 * 服务端接收新的链接
 * </p>
 *
 * @author Ant
 * @since 2021/12/17 5:11 下午
 */
public class AcceptableHandler implements ServerHandler {
    private final static int BUF_SIZE = 1024;

    @Override
    public void doHandler(SelectionKey key) {
        final ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
        try {
            final SocketChannel sc = ssChannel.accept();
            sc.configureBlocking(false);
            sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUF_SIZE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
