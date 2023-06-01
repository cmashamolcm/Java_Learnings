public class DataTypes {
    public static void main(String[] args) {
        int a = 10;//4 byte. default
        long b = 10L;// 8byte
        float c = 10.2f;// 4byte
        double d = 10.2;//8 byte. default is double
        char e = 'c';// 2byte - unicode. not ascii
        short f = 10;// 2byte
        byte g = 1;//1 byte. If we give big values, warns that precision may lost.
        byte i = (byte) 1000;
        System.out.println(i);// prints -24 isntead of 1000 as byte cannot hold this big value
        boolean h = true; // less than a byte
        int oct = 010;
        System.out.println(oct);//8
        int binary = 0B10;// ob10 also fine.
        System.out.println(binary);//2

        //conversion
        int x = 10;
        long y = x;//conversion

        int z = (int)y;// explicit type casting

        int num1 = 257;
        byte num2 = (byte)num1;
        System.out.println(num2);//num1%range for byte = 257%(2^(8-1)) = 257%128=1

        byte aa = 10;
        byte bb = 127;
        int cc = aa * bb;// allowed to save in cc with type int. If we try to assign it to int, it asks for explicit casting.

    }
}
