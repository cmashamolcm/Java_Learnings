public class Strings {
    public static void main(String[] args) {
        String s1 = "test";
        String s2 = "test";
        System.out.println(s1 == s2);// true. test will be one entry in string constant pool while creating s1. s2 will find it from pool,
        // and hence will not creating again.
        //But;
        String s3 = new String("test");
        System.out.println(s1 == s3);

        StringBuffer sb = new StringBuffer("hai");// thread safe
        sb.append(" world");
        System.out.println(sb.toString());

        StringBuilder builder = new StringBuilder("hello");// not thread safe as methods are not synchronized
        builder.append(" world");
        System.out.println(builder.toString());

    }
}
