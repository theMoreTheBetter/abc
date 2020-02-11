package nio.book1.chapter01.buffer;

import java.nio.ByteBuffer;

/**
 * limit不能大于其capcity
 */
public class Test2_2 {

    public static void main(String[] args) {
        byte[] byteArray = new byte[]{1,2,3};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        byteBuffer.limit(10);
    }
}
