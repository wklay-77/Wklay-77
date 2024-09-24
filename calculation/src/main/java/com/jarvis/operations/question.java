package com.jarvis.operations;

public class question {
    static String str="";
    public void Exam(int count,int range) throws Exception
    {
        int re=0;
        String[] strr = new String[count+1];
        String[] strr2 = new String[count+1];
        String[] repeat =new String[count+1];
        FileWriting.fileClear();
        String subject;
        for(int i=0;i<count;i++)
        {
            CreateFormula create=new CreateFormula();
            subject=create.Creating(range);
            //去掉多余括号
            subject=deleteKuoHao(subject);
            str=Check.chaChong(subject);
            strr[i]=str;
            strr2[i]=subject;
            int xy=1;
            for(int h=1;h<i;h++)
            {
                if(strr[h].equals(str))
                {
                    xy=0;
                    repeat[re]=h+","+strr2[h]+"  Repeat  "+i+","+subject;
                    re++;
                    break;
                }
            }
            if(xy==0)
            {
                i--;
                continue;
            }
            else
            {
                String result=Calculate.getResult(subject);
                FileWriting.writter((i+1)+"："+subject);
                FileWriting.writter1((i+1)+":"+result);
                FileWriting.writter2((i+1)+":");
                System.out.print("第"+(i+1)+"题,"+subject+"\n");

            }
        }
	/*System.out.println("Repeat:"+(re));
	System.out.println("RepeatDetail:");
	for(int j=0;j<re;j++)
	{
		System.out.print(j+1);
		System.out.print(" ");
		System.out.println(repeat[j]);
	}*/

    }
    //去除部分题目开头和结尾为括号的表达式
    static String deleteKuoHao(String str) {
        int i=0,j=0;
        String temp;
        if (str.substring(0, 1).equals("(") && str.substring(str.length() - 1, str.length()).equals(")")) {
            temp = str.substring(1, str.length() - 1);
            i=temp.indexOf(")");
            j=temp.indexOf("(");
            if(i>=j){
                str=temp;
            }
        }
        return str;
    }

}