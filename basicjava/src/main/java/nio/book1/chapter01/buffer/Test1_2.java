package nio.book1.chapter01.buffer;

import java.nio.ByteBuffer;

public class Test1_2 {

    public static void main(String[] args) {
        byte[] byteArray = new byte[]{1,2,3};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        byteBuffer = (ByteBuffer) byteBuffer.limit(-1);//limit不能为负
    }
}
