package com.ant.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * <p>
 * 选择器示例
 * </p>
 *
 * @author Ant
 * @since 2021/12/8 10:25 上午
 */
public class SelectorExample {
    public static void main(String[] args) throws IOException {
        // Get the selector
        Selector selector = Selector.open();
        System.out.println("Selector is open for making connection: " + selector.isOpen());

        // Get the server socket channel and register using selector
        ServerSocketChannel SS = ServerSocketChannel.open();
        InetSocketAddress hostAddress = InetSocketAddress.createUnresolved("localhost", 8080);
        SS.bind(hostAddress);
        SS.configureBlocking(false);
        final int ops = SS.validOps();

        SelectionKey selectKy = SS.register(selector, ops, null);



    }
}
