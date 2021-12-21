package com.ant.io.nio.tcp;

import com.ant.io.nio.tcp.handlers.ServerHandler;
import com.ant.io.nio.tcp.handlers.ServerHandlerFactory;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * TCP 服务端
 * </p>
 *
 * @author Ant
 * @since 2021/12/17 4:51 下午
 */
public class ServerConnect {

    private final static int BUF_SIZE = 1024;
    private final static long TIME_OUT = 1000L;
    public final static int SERVER_PORT = 8099;

    public static void main(String[] args) throws IOException {
        connect();
    }

    public static void connect() throws IOException {
        // 选择器
        Selector selector;
        // TCP 通道
        ServerSocketChannel serverSocketChannel;

        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.socket().bind(new InetSocketAddress("localhost", SERVER_PORT));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                TimeUnit.SECONDS.sleep(1);
                // =0 说明没有准备就绪的客户端
                if ((selector.select(TIME_OUT) == 0)) {
                    System.out.println("未准备就绪...");
                    continue;
                }

                final Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    final SelectionKey key = iterator.next();
                    ServerHandler handler = ServerHandlerFactory.getHandler(key);

                    // 通道处理
                    handler.doHandler(key);

                    // 注意处理完就绪集合的通道后，要移除出已就绪集合
                    iterator.remove();
                }

            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
