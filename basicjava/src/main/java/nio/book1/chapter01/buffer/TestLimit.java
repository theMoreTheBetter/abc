package nio.book1.chapter01.buffer;

import java.nio.CharBuffer;

/**
 * 限制缓冲区的大小，代表第一个不应该读取或者写入的元素index
 */
public class TestLimit {
    public static void main(String[] args) {
        char[] charArray = new char[]{'a','b','c','d','e','f'};
        CharBuffer buffer = CharBuffer.wrap(charArray);
        System.out.println("A capacity()=" + buffer.capacity() + " limit()=" + buffer.limit());
        buffer.limit(4);
        System.out.println();
        System.out.println("B capacity()=" + buffer.capacity() + " limit()=" + buffer.limit());
        buffer.put(0,'o');
        buffer.put(1,'p');
        buffer.put(2,'q');
        buffer.put(3,'r');
       // buffer.put(4,'s');
    }
}
