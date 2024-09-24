package com.jarvis.operations;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomePage
{
    public static void main(String[] args)
            throws Exception
    {
        System.out.print("请输入生成的题目数量:");
        String input1 = input();
        while (!isInt(input1)) {
            System.out.println("输入错误，请重新输入！");
            input1 = input();
        }
        System.out.print("请输入题目的数值范围:");
        String input2 = input();
        while (!isInt(input2)) {
            System.out.println("输入错误，请重新输入！");
            input2 = input();
        }
        question exam = new question();
        exam.Exam(Integer.parseInt(input1), Integer.parseInt(input2));
        System.out.println("出题完毕，请到your-answer.txt作答！");
        System.out.println("如需批改请按‘1’，任意键退出");
        String input3 = input();
        if (input3.equals("1")){
            CompareAnswer.compare();
            System.out.println("批改已完成，请到Grade.txt查看");
        }
        else
            System.exit(0);
    }

    public static boolean isInt(String string)
    {
        if (string == null) {
            return false;
        }
        String regEx1 = "[\\-|\\+]?\\d+";

        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(string);

        return m.matches();
    }

    public static String input()
    {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        return input;
    }
}