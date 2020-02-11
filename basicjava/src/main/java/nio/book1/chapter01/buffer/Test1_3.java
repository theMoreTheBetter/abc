package nio.book1.chapter01.buffer;

import java.nio.ByteBuffer;

public class Test1_3 {

    public static void main(String[] args) {
        byte[] byteArray = new byte[]{1,2,3};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        byteBuffer = (ByteBuffer) byteBuffer.position(-1);//position不能为负
    }
}
