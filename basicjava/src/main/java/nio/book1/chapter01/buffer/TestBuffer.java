package nio.book1.chapter01.buffer;

import java.nio.*;

public class TestBuffer {

    public static void main(String[] args) {
        byte[] byteArrray = new byte[]{1,2,3,4};
        short[] shortArray = new short[]{1,2,3,4};
        int[] intArray = new int[]{1,2,3,4};
        long[] longArray = new long[]{1,2,3,4};
        float[] floatArray = new float[]{1.1f,2.1f,3.1f,4.1f,5.1f,6.1f};
        double[] doubleArray = new double[]{1,2,3,4,5,6,7};
        char[] charArray = new char[]{'a','b','c','d'};

        //wrap()相当于创建缓冲区的工厂方法
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArrray);
        ShortBuffer shortBuffer = ShortBuffer.wrap(shortArray);
        IntBuffer intBuffer = IntBuffer.wrap(intArray);
        LongBuffer longBuffer = LongBuffer.wrap(longArray);
        FloatBuffer floatBuffer = FloatBuffer.wrap(floatArray);
        DoubleBuffer doubleBuffer = DoubleBuffer.wrap(doubleArray);
        CharBuffer charBuffer = CharBuffer.wrap(charArray);

        System.out.println("bytebuffer=" + byteBuffer.getClass().getName());
        System.out.println("shortbuffer=" + shortBuffer.getClass().getName());
        System.out.println("intbuffer=" + intBuffer.getClass().getName());
        System.out.println("longbuffer=" + longBuffer.getClass().getName());
        System.out.println("floatbuffer=" + floatBuffer.getClass().getName());
        System.out.println("doublebuffer=" + doubleBuffer.getClass().getName());
        System.out.println("charbuffer=" + charBuffer.getClass().getName());

        System.out.println("---------------------");

        System.out.println("bytebuffer.capacity=" + byteBuffer.capacity());
        System.out.println("shortbuffer.capacity=" + shortBuffer.capacity());
        System.out.println("intbuffer.capacity=" + intBuffer.capacity());
        System.out.println("longbuffer.capacity=" + longBuffer.capacity());
        System.out.println("floatbuffer.capacity=" + floatBuffer.capacity());
        System.out.println("doublebuffer.capacity=" + doubleBuffer.capacity());
        System.out.println("charbuffer.capacity=" + charBuffer.capacity());

    }
}
