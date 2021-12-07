package com.ant.io.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * <p>
 * 通道之间的数据传输
 * </p>
 *
 * @author Ant
 * @since 2021/12/3 11:44 上午
 */
public class TransferDemo {

    private static final String input_file1_path = "/Users/mayi/work/Ant/demo/input1.txt";
    private static final String input_file2_path = "/Users/mayi/work/Ant/demo/input2.txt";
    private static final String input_file3_path = "/Users/mayi/work/Ant/demo/input3.txt";
    private static final String input_file4_path = "/Users/mayi/work/Ant/demo/input4.txt";

    private static final String output_file_path = "/Users/mayi/work/Ant/demo/combine_output.txt";

    public static void main(String[] args) throws IOException {
        // Path of input files
        String[] iF = new String[] {input_file1_path, input_file2_path, input_file3_path, input_file4_path};

        FileOutputStream output = new FileOutputStream(new File(output_file_path));
        WritableByteChannel targetChannel = output.getChannel();

        for (int j = 0; j < iF.length; j++) {
            // Get the channel for input files
            FileInputStream input = new FileInputStream(iF[j]);
            FileChannel inputChannel = input.getChannel();

            // The data is transfer from input channel to output channel
            inputChannel.transferTo(0, inputChannel.size(), targetChannel);

            // close an input channel
            inputChannel.close();
            input.close();
        }

        // close the target channel
        targetChannel.close();
        output.close();
        System.out.println("All jobs done!!!");
    }

}
