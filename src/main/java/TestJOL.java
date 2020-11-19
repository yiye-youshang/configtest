import org.openjdk.jol.info.ClassLayout;

//假如锁处于偏向状态，这时候来了竞争者,那他的状态是什么
public class TestJOL {
    public static void main(String[] args) {
        Object o = new Object();
        /**解析一个对象的布局把他转成打印的类型
        Instance size: 16 bytes 刚new一个对象无任何变量 占16个字节
        前8个字节 叫markword
         后4个字节  new出来的对象属于哪个类 叫klasspoint
         最后4个字节是补出来的 为了能让8整除 提高效率
         **/
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        //
        /* markword的值 未加锁状态
           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
           00 00 00 00 (00000000 00000000 00000000 00000000) (0)

           28 f8 bd 02(00101000 11111000 10111101 00000010) (46004264)
           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
            加锁状态 简单来说 就是修改markword的值

        * */

        synchronized (o){
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
