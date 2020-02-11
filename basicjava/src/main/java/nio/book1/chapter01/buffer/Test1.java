package nio.book1.chapter01.buffer;

import java.nio.*;

/**
 * wrap() 和 capacity()
 */
public class Test1 {
    public static void main(String[] args) {
        byte[] byteArray = new byte[]{1,2,3};
        short[] shortArray = new short[]{1,2,3,4};
        int[] intArray = new int[]{1,2,3,4,5};
        long[] longArray = new long[]{1,2,3,4,5,6};
        float[] floatArray = new float[]{1,2,3,4,5,6,7};
        double[] doubleArray = new double[]{1,2,3,4,5,6};
        char[] charArray = new char[]{'a','b','c','d','e','f'};

        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        ShortBuffer shortBuffer = ShortBuffer.wrap(shortArray);
        IntBuffer intBuffer = IntBuffer.wrap(intArray);
        LongBuffer longBuffer = LongBuffer.wrap(longArray);
        FloatBuffer floatBuffer = FloatBuffer.wrap(floatArray);
        DoubleBuffer doubleBuffer = DoubleBuffer.wrap(doubleArray);
        CharBuffer charBuffer = CharBuffer.wrap(charArray);

        System.out.println("bytebuffer= " + byteBuffer.getClass().getName());
        System.out.println("shortBuffer= "+shortBuffer.getClass().getName());
        System.out.println("intbuffer= "+intBuffer.getClass().getName());
        System.out.println("longbuffer= "+longBuffer.getClass().getName());
        System.out.println("floatbuffer= "+floatBuffer.getClass().getName());
        System.out.println("doublebuffer= "+doubleBuffer.getClass().getName());
        System.out.println("charbuffer= "+charBuffer.getClass().getName());

        System.out.println("=====================================");

        System.out.println("bytebuffer.capacity= " + byteBuffer.capacity());
        System.out.println("shortBuffer.capacity= "+shortBuffer.capacity());
        System.out.println("intbuffer.capacity= "+intBuffer.capacity());
        System.out.println("longbuffer.capacity= "+longBuffer.capacity());
        System.out.println("floatbuffer.capacity= "+floatBuffer.capacity());
        System.out.println("doublebuffer.capacity= "+doubleBuffer.capacity());
        System.out.println("charbuffer.capacity= "+charBuffer.capacity());
    }
}
