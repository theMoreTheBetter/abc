package nio.book1.chapter01.buffer;

import java.nio.CharBuffer;

/**
 * remaining()返回当前位置与limit之间的元素数量
 */
public class Test4 {

    public static void main(String[] args) {
        char[] charArray = new char[]{'a','b','c','d','e'};
        CharBuffer charBuffer = CharBuffer.wrap(charArray);
        System.out.println("A capacity()="+charBuffer.capacity() + " limit()=" + charBuffer.limit() + " position()=" + charBuffer.position());
        charBuffer.position(2);
        System.out.println("B capacity()="+charBuffer.capacity() + " limit()=" + charBuffer.limit() + " position()=" + charBuffer.position());
        System.out.println("C remaining()=" + charBuffer.remaining());

    }
}
