package com.ant.io.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * <p>
 * JavaNio demo
 * </p>
 *
 * @author Ant
 * @since 2021/11/30 3:54 下午
 */
public class JavaNioDemo {

    private static final String source_file_path = "/Users/mayi/work/Ant/demo/source.txt";
    private static final String target_file_path = "/Users/mayi/work/Ant/demo/target.txt";

    public static void main(String[] args) throws IOException {
        String relativelyPath = System.getProperty("user.dir");
        FileInputStream input = new FileInputStream(source_file_path);
        // 数据读取通道
        ReadableByteChannel sourceChannel = input.getChannel();
        // 数据写入通道
        FileOutputStream outputStream = new FileOutputStream(target_file_path);
        WritableByteChannel destinationChannel = outputStream.getChannel();

        copyData(sourceChannel, destinationChannel);

        sourceChannel.close();
        destinationChannel.close();
    }

    private static void copyData(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(20 * 1024);
        while (src.read(buffer) != -1) {
            //
            buffer.flip();

            while (buffer.hasRemaining()) {
                dest.write(buffer);
            }
            buffer.clear();
        }
    }

}

