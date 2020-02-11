package nio.book1.chapter01.buffer;

import java.nio.CharBuffer;

/**
 * 限制缓冲区的大小，代表第一个不应该读取或者写入的元素index
 */
public class TestClear {
    public static void main(String[] args) {
        char[] charArray = new char[]{'a','b','c','d','e','f'};
        CharBuffer buffer = CharBuffer.wrap(charArray);
        buffer.position(2);
        buffer.limit(3);
        buffer.mark();

        buffer.clear();

        System.out.println("buffer.position=" + buffer.position());
        System.out.println();

        System.out.println("buffer.limit=" + buffer.limit());
        buffer.reset();
    }
}
