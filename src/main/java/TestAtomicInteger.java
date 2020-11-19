import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicInteger {
    //轻量级锁 无锁 自旋锁
    private static AtomicInteger m = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        Thread[] thread = new Thread[100];
        final CountDownLatch countDownLatch = new CountDownLatch(thread.length);
        for (int i = 0; i < thread.length; i++) {
            thread[i]=new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        
                        m.incrementAndGet();
                    }
                    countDownLatch.countDown();
                }
            });
        }
        Arrays.stream(thread).forEach((t)->t.start());
        countDownLatch.await();
        System.out.println(m);
    }

}
