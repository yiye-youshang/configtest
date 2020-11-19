package singleton;

import sun.security.provider.MD2;

/*
 * 饿汉式
 * 类加载到内存后，就实例化一个单例，JVM保证线程安全
 * 简单实用 推荐使用！
 * 唯一缺点：不管用到与否，类装载时就完成了实例化
 * Class.forName("")
 * */
public class TestSingleton {
    private static final TestSingleton INSTANCE = new TestSingleton();
    //不让别人用
    private TestSingleton() {
    };

    public static TestSingleton getInstance() {
        return INSTANCE;
    }
    public void m(){System.out.println("m");}

    public static void main(String[] args) {
        TestSingleton m1 = TestSingleton.getInstance();
        TestSingleton m2 = TestSingleton.getInstance();
        System.out.println(m1 == m2);
    }
}
