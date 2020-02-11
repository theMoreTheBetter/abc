package nio.book1.chapter01.buffer;

import java.nio.ByteBuffer;

/**
 * 转换存储和读取
 */
public class TestFlip {

    public static void main(String[] args) {
        byte[] byteArray = new byte[]{1,2,3,4,5,6,7,8};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);

        byteBuffer.position(2);
        byteBuffer.mark();

        byteBuffer.flip();

        System.out.println("byteBuffer.position=" + byteBuffer.position());

        System.out.println("byteBuffer.limit=" + byteBuffer.limit());

        byteBuffer.reset();
    }

}
