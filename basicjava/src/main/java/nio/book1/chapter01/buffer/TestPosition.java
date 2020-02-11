package nio.book1.chapter01.buffer;

import java.nio.CharBuffer;

/**
 * 返回此缓冲区的位置
 */
public class TestPosition {

    public static void main(String[] args) {
        char[] charArray = new char[]{'a','b','c','d'};
        CharBuffer charBuffer = CharBuffer.wrap(charArray);
        System.out.println("A capacity()=" + charBuffer.capacity() + " limit()=" + charBuffer.limit() + " position()=" + charBuffer.position());
        charBuffer.position(2);
        System.out.println("B capacity()=" + charBuffer.capacity() + " limit()=" + charBuffer.limit() + " position()=" + charBuffer.position());
        charBuffer.put("z");
        for (int i = 0 ; i < charArray.length;i++)
            System.out.print(charArray[i] + " ");

    }
}
