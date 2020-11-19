package testvolatile;

import java.security.PublicKey;

public class Disorder {
    //证明CPU指令执行乱序  反证法
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i=0;
        //让他死循环 然后起2个线程
        for (;;){
            i++;
            x = 0; y = 0;
            a = 0; b = 0;
            Thread one =new Thread(new Runnable() {
                @Override
                public void run() {
                    //由于线程one先启动,下面这句话让他等一等线程two
                    //shortWait(10000);
                    //下俩语句没有关系 可能存在乱序
                    /*假设不存在乱序 a=1一定在前面先执行 b=1也是
                    可能结果1：a=1           可能结果2: b=1      可能结果3  a=1 被打断 b=1
                              x=b                     y=a               x=b   y=a
                              b=1                     a=1               x=1,y=1
                              y=b                     x=b
                              x=0,y=1                 x=1,y=0

                    不可能会出现x=0,y=0
                                                  * */
                    a=1;
                    x=b;
                }
            });
            Thread other = new Thread(new Runnable() {
                @Override
                public void run() {
                    b=1;
                    y=a;
                }
            });
            one.start();other.start();
            one.join();other.join();
            String result= "第"+i+"次("+x+"."+y+")";
            if (x==0&&y==0){
                System.err.println(result);
                break;
            }else {
                //System.out.println(result);
            }
        }
    }

}
