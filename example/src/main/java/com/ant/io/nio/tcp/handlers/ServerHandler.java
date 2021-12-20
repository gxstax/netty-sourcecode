package com.ant.io.nio.tcp.handlers;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * <p>
 * 服务端接收请求处理接口
 * </p>
 *
 * @author Ant
 * @since 2021/12/17 5:14 下午
 */
public interface ServerHandler {

    void doHandler(SelectionKey key) throws IOException;

}
