package nio.book1.chapter01.buffer;

import java.nio.ByteBuffer;

public class Test7_1 {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100);
        System.out.println(byteBuffer.isDirect());
    }
}
