package nio.book1.chapter01.buffer;

import java.nio.ByteBuffer;

/**
 * mark() 缓冲区的位置设置标记
 */
public class Test5 {
    public static void main(String[] args) {
        byte[] byteArray = new byte[]{1,2,3};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);

        System.out.println("bytebuffer.capacity="+byteBuffer.capacity());
        System.out.println();

        byteBuffer.position(1);
        byteBuffer.mark();

        System.out.println("bytebuffer.position=" + byteBuffer.position());
        byteBuffer.position(2);
        byteBuffer.reset();
        System.out.println();
        System.out.println("bytebuffer.position=" + byteBuffer.position());

    }
}
