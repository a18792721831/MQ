public class MyJni {

    public static native String encode(String data);

    public static void main(String[] args) {

        System.out.println(encode("startimes"));

    }

}
