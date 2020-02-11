package nio.book1.chapter01.buffer;

import java.nio.*;

public class Test6 {

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

        System.out.println("bytebuffer.isReadOnly= " + byteBuffer.isReadOnly());
        System.out.println("shortBuffer.isReadOnly= "+shortBuffer.isReadOnly());
        System.out.println("intbuffer.isReadOnly= "+intBuffer.isReadOnly());
        System.out.println("longbuffer.isReadOnly= "+longBuffer.isReadOnly());
        System.out.println("floatbuffer.isReadOnly= "+floatBuffer.isReadOnly());
        System.out.println("doublebuffer.isReadOnly= "+doubleBuffer.isReadOnly());
        System.out.println("charbuffer.isReadOnly= "+charBuffer.isReadOnly());

        System.out.println("=====================================");
    }
}
