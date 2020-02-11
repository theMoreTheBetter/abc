package nio.book1.chapter01.buffer;

import java.nio.ByteBuffer;

/**
 * 向缓冲区中存储数据，然后再从缓冲区中读取这些数据时，就是使用flip()方法的最佳时机
 * 通过改变limit和position的值
 */
public class Test11 {

    public static void main(String[] args) {
        byte[] byteArray = new byte[]{1,2,3};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);

        byteBuffer.position(2);
        byteBuffer.mark();

        byteBuffer.flip();

        System.out.println("byteBuffer.position=" + byteBuffer.position());
        System.out.println();

        System.out.println("byteBuffer.limit=" + byteBuffer.limit());
        System.out.println();

        byteBuffer.reset();
    }
}
