import java.util.ArrayList;

public class HeapTest {
    byte[] a=new byte[2048*100*10];

    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeapTest> arrayList = new ArrayList<>();
        while (true){
            arrayList.add(new HeapTest());
            Thread.sleep(10);
        }
    }
}
