package jmc.converter.util;

public class Util {

    public static String byte2HexString(byte value) {
        StringBuilder strBuilder = new StringBuilder(2);
        strBuilder.append(String.format("%02X", value & 0xFF));

        return strBuilder.toString().toUpperCase();
    }

    public static String byteArray2HexString(byte array[]) {
        String str = "";

        for (int ii = 0; ii < array.length; ii++) {
            str += Util.byte2HexString(array[ii]);
        }

        return str;
    }

    public static short getShort(byte highByte, byte lowByte) {
        return (short) (((highByte & 0xFF) << 8) | (lowByte & 0xFF));
    }

    public static short revert(short value) {
        short tmp = value;

        value  = (short) ((tmp & 0x00FF) << 8);
        value += (short) ((tmp & 0xFF00) >> 8);

        return value;
    }

    public static int revert(int value) {
        int tmp = value;

        value  = (int) ((tmp & 0x000000FF) << 24);
        value += (int) ((tmp & 0x0000FF00) <<  8);
        value += (int) ((tmp & 0x00FF0000) >>  8);
        value += (int) ((tmp & 0xFF000000) >> 24);

        return value;
    }

}
