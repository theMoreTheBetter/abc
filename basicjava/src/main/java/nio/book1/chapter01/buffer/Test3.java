package nio.book1.chapter01.buffer;

import java.nio.CharBuffer;

/**
 * position() 代表下一个要读取或写入元素的index，position<limit,如果mark
 * 已定义且大于新的position则丢弃mark
 */
public class Test3 {
    public static void main(String[] args) {
        char[] charArray = new char[]{'a','b','c','d','e'};
        CharBuffer charBuffer = CharBuffer.wrap(charArray);
        System.out.println("A capacity()= " + charBuffer.capacity() +
                " limit()=" + charBuffer.limit() + " position()=" + charBuffer.position());
        charBuffer.position(2);
        System.out.println("B capacity()= " + charBuffer.capacity() +
                " limit()=" + charBuffer.limit() + " position()=" + charBuffer.position());
        charBuffer.put("z");
        for(int i = 0; i < charArray.length;i++){
            System.out.print(charArray[i] + " ");
        }
        System.out.println();
    }
}
