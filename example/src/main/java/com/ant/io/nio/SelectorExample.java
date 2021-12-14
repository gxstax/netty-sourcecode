package com.ant.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * <p>
 * 选择器示例
 * </p>
 *
 * @author Ant
 * @since 2021/12/8 10:25 上午
 */
public class SelectorExample {

    public static void main(String[] args) throws IOException, InterruptedException {

        // Get the selector
        Selector selector = Selector.open();
        System.out.println("Selector is open for making connection: " + selector.isOpen());

        // Get the server socket channel and register using selector
        ServerSocketChannel SS = ServerSocketChannel.open();
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 8080);
        SS.bind(hostAddress);
        SS.configureBlocking(false);
        final int ops = SS.validOps();

        SelectionKey selectKy = SS.register(selector, ops, null);
        System.out.println("Waiting for the select operation...");

        for (;;) {
            int noOfKeys = selector.select();
            System.out.println("The Number of selected keys are: " + noOfKeys);

            final Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator itr = selectionKeys.iterator();

            while (itr.hasNext()) {
                final SelectionKey key = (SelectionKey) itr.next();
                if (key.isAcceptable()) {
                    // The new client connection is accepted
                    SocketChannel client = SS.accept();
                    client.configureBlocking(false);

                    // The new connection is added to a selector
                    client.register(selector, SelectionKey.OP_READ);
                    System.out.println("The new connection is accepted from the client: " + client);
                } else if (key.isReadable()) {
                    // Data is read from the client
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(256);
                    client.read(buffer);
                    String output = new String(buffer.array()).trim();
                    System.out.println("Message read from client: " + output);
                    if (output.equals("Bye Bye")) {
                        client.close();
                        System.out.println("The Client messages are complete; close the session.");
                    }
                }
                itr.remove();
            }
        }
    }

}
