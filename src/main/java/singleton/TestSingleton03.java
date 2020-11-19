package singleton;
//虽然实现了按需创建，但是带了线程不安全的问题
public class TestSingleton03 {
    //需要一个对象的时候才new
    private static TestSingleton03 INSTANCE;
    private TestSingleton03(){}
    //要解决线程安全问题就上锁synchronized
    public static  /*synchronized*/ TestSingleton03 getInstance(){
        //业务逻辑 没必要用到锁
        // xxxx
        //xxx
        // 又出现一个问题 锁粒度太粗了 进行修正
        if (INSTANCE==null) {
            //妄图通过减少同步代码块的方式提高效率，然而不可行
            synchronized (TestSingleton03.class) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new TestSingleton03();
            }
        }
        return INSTANCE;
    }

    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->System.out.println(TestSingleton03.getInstance().hashCode())).start();
        }
    }
}
