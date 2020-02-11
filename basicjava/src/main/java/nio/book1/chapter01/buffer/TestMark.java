package nio.book1.chapter01.buffer;

import java.nio.ByteBuffer;

public class TestMark {
    public static void main(String[] args) {
        byte[] byteArray = new byte[]{1,2,4,5,6,7};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);

        System.out.println("bytebuffer.capacity=" + byteBuffer.capacity());
        System.out.println();

        byteBuffer.position(1);
        byteBuffer.mark();

        System.out.println("bytebuffer.position=" + byteBuffer.position());
        byteBuffer.position(2);
        //重置到mark处
        byteBuffer.reset();
        System.out.println();
        System.out.println("bytebuffer.positon=" + byteBuffer.position());
    }
}
