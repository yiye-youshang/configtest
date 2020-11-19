package singleton;
//虽然实现了按需创建，但是带了线程不安全的问题
public class TestSingleton02 {
    //需要一个对象的时候才new
    private static TestSingleton02 INSTANCE;
    private TestSingleton02(){}
    //要解决线程安全问题就上锁synchronized
    public static  /*synchronized*/ TestSingleton02 getInstance(){
        //业务逻辑 没必要用到锁 又出现一个问题 锁粒度太粗了
        if (INSTANCE==null){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE=new TestSingleton02();
        }
        return INSTANCE;
    }

    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->System.out.println(TestSingleton02.getInstance().hashCode())).start();
        }
    }
}
