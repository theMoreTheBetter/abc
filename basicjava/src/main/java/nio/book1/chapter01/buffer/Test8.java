package nio.book1.chapter01.buffer;

import java.nio.ByteBuffer;

/**
 * clear()方法的主要使用场景是在对缓冲区存储数据之前调用此方法
 */
public class Test8 {

    public static void main(String[] args) {
        byte[] byteArray = new byte[]{1,2,3};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);

        byteBuffer.position(2);
        byteBuffer.limit(3);
        byteBuffer.mark();

        byteBuffer.clear();

        System.out.println("byteBuffer.position= " + byteBuffer.position());
        System.out.println();
        System.out.println("bytebuffer.limit=" + byteBuffer.limit());
        System.out.println();
        byteBuffer.reset();
    }
}
