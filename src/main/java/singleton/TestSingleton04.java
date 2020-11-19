package singleton;

//虽然实现了按需创建，但是带了线程不安全的问题
//于是有了DCL的写法 Double Check Lock
//
public class TestSingleton04 {
        //于是 又有一个问题 要不要volatile?
    private static volatile TestSingleton04 INSTANCE;

    private TestSingleton04() {
    }

    public static  /*synchronized*/ TestSingleton04 getInstance() {
        if (INSTANCE == null) {
            //双重检查 Double Check Lock
            synchronized (TestSingleton04.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new TestSingleton04();
                }
            }
        }
        return INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> System.out.println(TestSingleton04.getInstance().hashCode())).start();
        }
    }
}
