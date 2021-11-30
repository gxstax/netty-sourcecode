package com.ant.io.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <p>
 * 使用 BufferedReader 从文件中读取行的简单示例
 * </p>
 *
 * @author Ant
 * @since 2021/11/30 4:52 下午
 */
public class BufferedReaderDemo {

    private static final String target_file_path = "/Users/mayi/work/Ant/demo/target.txt";

    public static void main(String[] args) {
        Path file = null;
        BufferedReader bufferedReader = null;

        try {
            file = Paths.get(target_file_path);
            InputStream inputStream = Files.newInputStream(file);

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            System.out.println("Reading the Line of target.txt file: \n" + bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
