package com.ant.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

/**
 * <p>
 * 分散/聚集输入输出示例
 * </p>
 *
 * @author Ant
 * @since 2021/11/30 6:33 下午
 */
public class ScatterGatherIO {

    private static final String target_file_path = "/Users/mayi/work/Ant/demo/target.txt";

    public static void main(String[] args) {
        String data = "ScatterGatherIO demo By Ant";
        gatherBytes(data);
        scatterBytes();
    }

    /**
     * <p>
     * 字节聚集
     * </p>
     *
     * @param data
     * @return void
     */
    public static void gatherBytes(String data) {
        // The First Buffer is used for holding a random number.
        ByteBuffer buffer1 = ByteBuffer.allocate(8);

        // The Second Buffer is used for holding a data that we want to write.
        ByteBuffer buffer2 = ByteBuffer.allocate(400);

        buffer1.asIntBuffer().put(420);
        buffer2.asCharBuffer().put(data);

        GatheringByteChannel gatherer = createChannelInstance(target_file_path, true);

        // Write the data into file
        try {
            gatherer.write(new ByteBuffer[] {buffer1, buffer2});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     * 字节分散
     * </p>
     *
     * @param
     * @return void
     */
    public static void scatterBytes() {
        // The First Buffer is used for holding a random number
        ByteBuffer buffer1 = ByteBuffer.allocate(8);
        // The Second Buffer is used for holding a data that we want to write
        ByteBuffer buffer2 = ByteBuffer.allocate(400);
        ScatteringByteChannel scatter = createChannelInstance(target_file_path, false);
        // Reading a data from the channel
        try {
            scatter.read(new ByteBuffer[] { buffer1, buffer2 });
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Read the two buffers seperately
        buffer1.rewind();
        buffer2.rewind();

        int bufferOne = buffer1.asIntBuffer().get();
        String bufferTwo = buffer2.asCharBuffer().toString();
        // Verification of content
        System.out.println(bufferOne);
        System.out.println(bufferTwo);


    }

    public static FileChannel createChannelInstance(String file, boolean isOutput) {
        FileChannel FChannel = null;
        try {
            if (isOutput) {
                FChannel = new FileOutputStream(file).getChannel();
            } else {
                FChannel = new FileInputStream(file).getChannel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FChannel;
    }

}
