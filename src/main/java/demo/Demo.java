package demo;

/**
 * @author 听一夜忧伤
 * @create 2020/11/16
 */
public class Demo{
    private int count;
    public static void main(String[] args) {
        Demo test=new Demo(88);
        System.out.println(test.count);
    }
    Demo(int a) {
        count=a;
    }
}