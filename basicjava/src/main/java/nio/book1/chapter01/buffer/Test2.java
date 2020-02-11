package nio.book1.chapter01.buffer;

import java.nio.CharBuffer;

/**
 * limit()
 */
public class Test2 {
    public static void main(String[] args) {
        char[] charArray = new char[] {'a','b','c','d','e'};
        CharBuffer buffer = CharBuffer.wrap(charArray);
        System.out.println("A capacity()=" + buffer.capacity() + "  limit()=" + buffer.limit());
        buffer.limit(3);
        System.out.println("使用limit后");
        System.out.println("B capacity()=" + buffer.capacity() + "  limit()=" + buffer.limit());
        buffer.put(0,'o');
        buffer.put(1,'p');
        buffer.put(2,'q');
        buffer.put(3,'o');
        System.out.println(buffer);
    }
}
