public class Lab6 {

    public static void main(String[] args) {

        int firstInput = 0xd4;
        int secondInput = 0xbf;

        int firstResult = mul02( firstInput);
        int secondResult = mul03( secondInput);

        System.out.println(Integer.toHexString(firstInput) + " * 02 = " + Integer.toHexString(firstResult));

        System.out.println(Integer.toHexString(secondInput) + " * 03 = " + Integer.toHexString(secondResult));
    }

    public static int mul02(int a) {
        int tmp = (a << 1) & 0xff;
        if (a < 80) {
            return tmp;
        } else {
            return tmp ^ 0x1b;
        }
    }

    public static int mul03(int a) {
        return mul02(a) ^ a;
    }

}
