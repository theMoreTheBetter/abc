package nio.book1.chapter01.buffer;

import java.nio.ByteBuffer;

/**
 * 缓冲区的capacity不能为负数
 * 需要使用allocate()方法开辟出指定空间大小的缓冲区
 */
public class Test1_1 {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(-1);//不能为负数

    }
}
