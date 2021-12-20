package com.ant.io.nio.tcp.handlers;

import java.nio.channels.SelectionKey;

/**
 * <p>
 * 服务端请求路由工厂
 * </p>
 *
 * @author Ant
 * @since 2021/12/17 5:15 下午
 */
public class ServerHandlerFactory {

    public static ServerHandler getHandler(SelectionKey key) {
        if (key.isAcceptable()) {
            return new AcceptableHandler();
        }

        if (key.isReadable()) {
            return new ReadHandler();
        }

        if (key.isWritable()) {
            return new WriteHandler();
        }

        return new DefaultHandler();
    }

}
