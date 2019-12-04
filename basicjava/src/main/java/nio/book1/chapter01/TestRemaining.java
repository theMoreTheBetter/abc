package nio.book1.chapter01;

import java.nio.CharBuffer;

/**
 * 返还“当前位置”与limit之间的元素数
 */
public class TestRemaining {
    public static void main(String[] args) {
        char[] charArray = new char[]{'a','b','d','t','p'};
        CharBuffer charBuffer = CharBuffer.wrap(charArray);
        System.out.println("A capacity()=" + charBuffer.capacity() + " limit()=" + charBuffer.limit() + " position()=" + charBuffer.position());
        charBuffer.position(2);
        System.out.println("B capacity()=" + charBuffer.capacity() + " limit()=" + charBuffer.limit() + " position()=" + charBuffer.position());
        System.out.println("C remaining()=" + charBuffer.remaining());

    }
}
