package com.jarvis.operations;

import java.util.Random;

public class CreateFormula {// 生成算式
    private static char[] operate = { '+', '-', '*', '÷' };

    public String Creating(int range){
        String subject = "";
        Random r = new Random();
        int  operator = r.nextInt(3)+1;//操作符个数
        int kuohao=0;
        boolean flag=true;
        for(int i=1;i<=operator;i++)
        {

            Random rand=new Random();
            int temp=rand.nextInt(3)+1;
            if(temp==1)//决定是否加入左括号
            {
                subject+="(";
                kuohao++;
                subject+=this.getNum(range);
                subject += this.getOperate();
            } else if (temp == 2 && flag) {//决定是否加入左括号
                flag=false;
                subject="("+subject;
                kuohao++;
                subject+=this.getNum(range);
                subject += this.getOperate();
            }else if(temp==3&& kuohao!=0){//决定是否加入右括号
                subject+=this.getNum(range);
                subject+=")";
                kuohao--;
                subject += this.getOperate();
            }else{
                subject+=this.getNum(range);
                subject += this.getOperate();
            }

        }
        subject+=this.getNum(range);
        for(int i=kuohao;i>0;i--)
        {
            subject=subject+")";
        }
        return subject;
    }
    private String getNum(int num) {// 随机获取操作数,1.自然数，2.真分数，3.带分数
        String str = "";
        int fz=0;//分子
        int fm=1;//分母
        int zs=0;//带分数左边的整数
        Random rand = new Random();
        int type=rand.nextInt(4);
        if(type==0){	//带分数
            fm= (rand.nextInt(num)) + 1 ;
            fz=(rand.nextInt(fm));
            if(fz==0)
            {
                fz=fz+1;
            }
            zs=(rand.nextInt(fm))+1;
            str=zs+"'"+fz+"/"+fm+"";
        }else if(type==1){//真分数
            fm= (rand.nextInt(num)) + 1 ;
            fz=(rand.nextInt(fm));
            if(fz==0)
            {
                fz=fz+1;
            }
            str=fz+"/"+fm+"";
        }else{
            //自然数
            str = ((int) (rand.nextInt(num)) + 1) + "";
        }

        return str;
    }

    private  String getOperate() {//随机生成操作符
        Random rand = new Random();
        int temp = rand.nextInt(4);
        String str=operate[temp]+"";
        return str;
    }
}