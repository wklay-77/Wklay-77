package org.example;

import java.util.HashMap;
import java.util.Map;

public class SimilarityMain {
    // 创建一个存储字符向量的Map，键为字符，值为包含两个元素的int数组
    private final Map<Character, int[]> vectorMap = new HashMap<>();

    // 构造函数，计算两个字符串的字符向量
    public SimilarityMain(String string1, String string2) {
        calculateVector(string1, 0); // 计算字符串1的字符向量，index为0
        calculateVector(string2, 1); // 计算字符串2的字符向量，index为1
    }

    // 计算相似度
    public double similarity() {
        return cosineSimilarity(); // 返回余弦相似度
    }

    // 计算字符向量的方法
    private void calculateVector(String str, int index) {
        for (char c : str.toCharArray()) {
            // 如果字符c不在Map中，则将其加入Map并初始化对应的int数组
            // 然后根据index增加对应位置的计数
            vectorMap.computeIfAbsent(c, k -> new int[2])[index]++;
        }
    }

    // 计算余弦相似度的方法
    private double cosineSimilarity() {
        double dotProduct = 0.0; // 点积
        double normA = 0.0; // 向量A的模长
        double normB = 0.0; // 向量B的模长

        // 遍历字符向量计算点积和各自的模长
        for (int[] vector : vectorMap.values()) {
            dotProduct += vector[0] * vector[1]; // 点积累加
            normA += Math.pow(vector[0], 2); // 向量A模长的平方累加
            normB += Math.pow(vector[1], 2); // 向量B模长的平方累加
        }

        // 计算余弦相似度并返回
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}