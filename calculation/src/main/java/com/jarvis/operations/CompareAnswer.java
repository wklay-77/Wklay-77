package com.jarvis.operations;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class CompareAnswer {
    private static String filePath2 = "answer.txt";
    private static String filePath3 = "your-Answer.txt";

    public static void compare() throws Exception {
        String right = "";
        String error = "";
        int correct=0;
        int wrong=0;
        InputStreamReader reader = new InputStreamReader(new FileInputStream(
                filePath2), "gbk");
        BufferedReader br = new BufferedReader(reader);
        String s = null;
        InputStreamReader reader1 = new InputStreamReader(new FileInputStream(
                filePath3), "gbk");
        BufferedReader br1 = new BufferedReader(reader1);
        String s1 = null;
        int i = 0;
        right = "";
        error = "";
        while (((s = br.readLine()) != null) && (s1 = br1.readLine()) != null) {
            i++;
            if (s.trim().equals(s1.trim())) {
                correct++;
                right = right + i + ",";
            } else {
                wrong++;
                error = error + i + ",";
            }

        }
        String str1 = "Correct:" + correct;
        if (right.length() <= 1)
            str1 = str1 + "(" + ")";
        else
            str1 = str1 + "(" + right.substring(0, right.length() - 1)+ ")";

        String str2 = "Wrong:" + wrong;
        if (error.length() <= 1)
            str2 = str2 + "(" + ")";
        else
            str2 = str2 + "(" + error.substring(0, error.length() - 1)+ ")";
        FileWriting.fileClear();
        FileWriting.writter3(str1);
        FileWriting.writter3(str2);

    }
}