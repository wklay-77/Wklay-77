package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class
Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // 提示用户输入原始论文的文件绝对路径
        System.out.print("提示用户输入原始论文的文件绝对路径");
        String str1 = scan.nextLine();

        // 提示用户输入抄袭版论文的文件绝对路径
        System.out.print("提示用户输入抄袭版论文的文件绝对路径");
        String str2 = scan.nextLine();

        // 提示用户输入要保存相似度结果的文件绝对路径
        System.out.print("提示用户输入要保存相似度结果的文件绝对路径");
        String outputFile = scan.nextLine();

        // 检查输入的文件路径是否为空
        if (str1.length() == 0 || str2.length() == 0 || outputFile.length() == 0) {
            System.out.println("输入文件路径不能为空！");
            return;
        }

        // 创建一个DecimalFormat实例以格式化相似度值
        DecimalFormat df = new DecimalFormat("0.00");

        // 使用输入的文件路径创建SimilarityMain实例
        SimilarityMain similar = new SimilarityMain(str1, str2);

        // 计算两篇论文的相似度
        double similarity = similar.similarity();

        // 将相似度结果打印到控制台
        System.out.println("文章相似度为：" + df.format(similarity));

        try (FileWriter writer = new FileWriter(outputFile)) {
            // 将相似度结果写入指定的输出文件
            writer.write("文章相似度为：" + df.format(similarity));
            System.out.println("相似度结果已保存至文件：" + outputFile);
        } catch (IOException e) {
            System.out.println("无法写入文件：" + e.getMessage());
        }
    }
}
