package nio.book1.chapter01.buffer;

import java.nio.ByteBuffer;

/**
 * position不能大于其limit
 */
public class Test2_1 {
    public static void main(String[] args) {
        byte[] byteArray = new byte[]{1,2,3};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        byteBuffer.limit(2);
        byteBuffer.position(3);

    }
}
