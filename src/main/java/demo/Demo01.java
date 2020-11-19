package demo;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author 听一夜忧伤
 * @create 2020/11/17
 */
public class Demo01 {
    public static void main(String[] args) {
        System.out.println(test());
    }
    private static int test(){
        int temp =1;
        try {
            System.out.println(temp);
            return ++temp;
        }catch (Exception e){
            System.out.println(temp);
            return ++temp;
        }finally {
            ++temp;
            System.out.println(temp);
        }
    }
}
