package com.jarvis.operations;


import java.util.ArrayList;
import java.util.Stack;

public class Calculate {

    public static String getResult(String s)	{

        String result=Calculate.PRNCal(s);
        String[] ary = result.split("/");
        int a = Integer.parseInt(ary[0].trim());
        int b = Integer.parseInt(ary[1].trim());
        if (Math.abs(a) < Math.abs(b)) {
            result = a + "/" + b;
        } else if (a == b) {
            result = 1 + "";
        } else if (a == -b) {
            result = -1 + "";
        } else {
            int yu;
            int da;
            if (a == 0 || b == 0) {
                yu = 0;
                da = 0;
            } else {
                yu = a % b;
                da = a / b;
            }
            if (yu == 0)
                result = da + "";
            else
                result = da + "'" + Math.abs(yu) + "/" + Math.abs(b);

        }
        return result;

    }

    // 计算算式的结果（后缀表达式）
    public static String PRNCal(String s) {
        String PRN=Calculate.generate(s);
        Stack<String> stack1 = new Stack<String>();
        String[] ss = PRN.split(" ");
        for (int i = 0; i < ss.length; i++) {
            if (ss[i].equals("+") || ss[i].equals("-") || ss[i].equals("*")
                    || ss[i].equals("÷")) {
                String b = stack1.pop();
                String a = stack1.pop();
                int fenmu;
                int fenzi;
                // 转换
                fenshu f1 = new fenshu();
                f1 = OperandChange(a);
                fenshu f2 = new fenshu();
                f2 = OperandChange(b);
                int fenmu1 = f1.getFenmu();
                int fenzi1 = f1.getFenzi();
                int fenmu2 = f2.getFenmu();
                int fenzi2 = f2.getFenzi();

                if (ss[i].equals("+")) {
                    int gcm = getMCM(fenmu1, fenmu2);
                    fenzi1 = gcm / fenmu1 * fenzi1;
                    fenzi2 = gcm / fenmu2 * fenzi2;
                    fenzi = fenzi1 + fenzi2;
                    fenmu = gcm;
                    if (fenzi != 0 && fenmu != 0) {
                        int lcd = getLCD(fenzi, fenmu);
                        fenmu = fenmu / lcd;
                        fenzi = fenzi / lcd;
                    }

                    stack1.push(fenzi + "/" + fenmu);
                }

                if (ss[i].equals("-")) {
                    int gcm = getMCM(fenmu1, fenmu2);
                    fenzi1 = gcm * fenzi1 / fenmu1;
                    fenzi2 = gcm * fenzi2 / fenmu2;
                    fenzi = fenzi1 - fenzi2;
                    fenmu = gcm;
                    if (fenzi != 0 && fenmu != 0) {
                        int lcd = getLCD(fenzi, fenmu);
                        fenmu = fenmu / lcd;
                        fenzi = fenzi / lcd;
                    }

                    stack1.push(fenzi + "/" + fenmu);
                }

                if (ss[i].equals("*")) {
                    fenmu = fenmu1 * fenmu2;
                    fenzi = fenzi1 * fenzi2;
                    if (fenzi != 0 && fenmu != 0) {
                        int lcd = getLCD(fenzi, fenmu);
                        fenmu = fenmu / lcd;
                        fenzi = fenzi / lcd;
                    }

                    stack1.push(fenzi + "/" + fenmu);
                }

                if (ss[i].equals("÷")) {
                    fenmu = fenmu1 * fenzi2;
                    fenzi = fenmu2 * fenzi1;
                    if (fenzi != 0 && fenmu != 0) {
                        int lcd = getLCD(fenzi, fenmu);
                        fenmu = fenmu / lcd;
                        fenzi = fenzi / lcd;
                    }

                    stack1.push(fenzi + "/" + fenmu);
                }

            } else {
                stack1.push(ss[i]);
            }
        }
        return stack1.pop();
    }


    //中缀表达式转后缀表达式
    public static String generate(String s) {
        String[] arr = convert(s);
        StringBuffer buffer = new StringBuffer();
        Stack<String> op = new Stack<String>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("(")) {
                buffer.append(generate(bracketDeal(s, i)));
                i = i + bracketDeal1(s, i) + 1;
            } else if (arr[i].equals("+") || arr[i].equals("-")
                    || arr[i].equals("*") || arr[i].equals("÷")) {
                while (!op.isEmpty() && opCompare(op.lastElement(), arr[i])) {
                    buffer.append(op.pop() + " ");
                }
                op.push(arr[i]);
            } else
                buffer.append(arr[i] + " ");
        }

        while (!op.isEmpty()) {
            buffer.append(op.pop() + " ");

        }

        return buffer.toString();

    }

    // 括号处理1
    public static String bracketDeal(String s, int k) {
        int m = 0;
        int i;
        String[] arr = convert(s);
        for (i = k; i < arr.length; i++) {
            if (arr[i].equals("(")) {
                m++;
                continue;
            }
            if (arr[i].equals(")")) {
                m--;
                if (m == 0)
                    break;
                else
                    continue;
            }

        }

        StringBuilder ss = new StringBuilder();
        for (int j = k + 1; j < i; j++) {
            ss.append(arr[j]);
        }
        return ss.toString();
    }

    // 括号处理2
    public static int bracketDeal1(String s, int k) {
        int m = 0;
        int i;
        String[] arr = convert(s);
        for (i = k; i < arr.length; i++) {
            if (arr[i].equals("(")) {
                m++;
                continue;
            }
            if (arr[i].equals(")")) {
                m--;
                if (m == 0)
                    break;
                else
                    continue;
            }

        }

        int position = i - k - 1;
        return position;
    }

    // 将表达式分离成一个个字符串
    public static String[] convert(String s) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < s.length(); i++) {
            if (!isOpOrKh(s.charAt(i))) {
                int j = i;
                while ((i < s.length()) && !isOpOrKh(s.charAt(i)))
                    i++;
                arrayList.add(s.substring(j, i));
                i--;
            } else
                arrayList.add(String.valueOf(s.charAt(i)));
        }

        return arrayList.toArray(new String[arrayList.size()]);

    }

    // 判断是否是括号还是操作符
    public static boolean isOpOrKh(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '÷' || c == '('
                || c == ')')
            return true;
        else
            return false;
    }

    //给操作符赋值用于比较优先级
    public static int opValue(String s) {
        if (s.equals("+")) {
            return 1;
        } else if (s.equals("-")) {
            return 1;
        } else if (s.equals("*")) {
            return 3;
        } else if (s.equals("÷")) {
            return 3;
        } else {
            return -1;
        }
    }

    //操作符比较优先级
    public static boolean opCompare(String s1, String s2) {
        if (opValue(s1) >= opValue(s2))
            return true;
        else {
            return false;
        }
    }

    // 求最大公约数
    public static int getLCD(int num1, int num2) {
        while (num2 != 0) {
            int temp = num1 % num2;
            num1 = num2;
            num2 = temp;
        }
        return num1;
    }

    // 求最小公倍数
    public static int getMCM(int num1, int num2) {
        return num1 * num2 / getLCD(num1, num2);
    }

    // 所有操作数转换分子分母形式
    public static fenshu OperandChange(String b) {
        fenshu f = new fenshu();
        if (b.indexOf("'") != -1) {
            String[] ary = b.split("'");
            f.setFenshu(Integer.parseInt(ary[0].trim()));
            b = ary[1].trim();
            String[] ary1 = b.split("/");
            int c = Integer.parseInt(ary1[0].trim())
                    + Integer.parseInt(ary1[1].trim())
                    * Integer.parseInt(ary[0].trim());
            f.setFenzi(c);
            f.setFenmu(Integer.parseInt(ary1[1].trim()));

        } else if (b.indexOf("/") != -1) {
            f.setFenshu(0);
            String[] ary1 = b.split("/");
            f.setFenzi(Integer.parseInt(ary1[0].trim()));
            f.setFenmu(Integer.parseInt(ary1[1].trim()));
        } else {
            f.setFenshu(0);
            f.setFenzi(Integer.parseInt(b.trim()));
            f.setFenmu(1);
        }

        return f;

    }
}