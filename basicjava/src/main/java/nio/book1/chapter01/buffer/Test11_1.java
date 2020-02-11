package nio.book1.chapter01.buffer;

import java.nio.CharBuffer;

public class Test11_1 {
    public static void main(String[] args) {
        CharBuffer charBuffer = CharBuffer.allocate(20);
        System.out.println("A position=" + charBuffer.position() +" limit=" + charBuffer.limit());
    }
}
