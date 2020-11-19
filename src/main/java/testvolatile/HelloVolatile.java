package testvolatile;

import java.util.concurrent.TimeUnit;
/*程序永远不会结束 死循环
 加上Volatile  作用一个线程修改内存一个值 其他线程马上可见
* */
public class HelloVolatile {

   /* boolean*/boolean running = true;

    void m() {
        System.out.println("m start");
        while (running) {
            //会结束 因为在语句执行的内部 会和本地缓存做同步 很可能会结束 如果不是打印语句 结果不一定结束
            System.out.println("hello");
        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        HelloVolatile t = new HelloVolatile();
        new Thread(t::m, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;
    }
}
