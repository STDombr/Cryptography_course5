import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

public class RSA {

    public static void main(String[] args) {

        Random random = new Random();

        int input = 998;
        System.out.println("Input message: " + input);

        int n, f, d = 0, e, i;

        int p;
        do {
            p = random.nextInt(100) + 3;
        } while (!MillerRabin.isPrime(new BigInteger(Integer.toString(p)), 3));

        System.out.println("p = " + p);

        int q;
        do {
            q = random.nextInt(100) + 3;
        } while (!MillerRabin.isPrime(new BigInteger(Integer.toString(q)), 3));

        System.out.println("q = " + q);


        n = p * q;
        f = (p - 1) * (q - 1);
        System.out.println("f(n) = " + f);

        for (e = 2; e < f; e++) {
            if (gcdExtended(e, f) == 1) {
                break;
            }
        }
        System.out.println("e = " + e);

        for (i = 0; i <= 9; i++) {
            int x = i * f + 1;
            if (x % e == 0) {
                d = x / e;
                break;
            }
        }
        System.out.println("d = " + d);

        double enc = Math.pow(input, e) % n;
        System.out.println("Encrypted message: " + enc);

        BigInteger N = BigInteger.valueOf(n);

        BigInteger C = BigDecimal.valueOf(enc).toBigInteger();
        BigInteger dec = (C.pow(d)).mod(N);
        System.out.println("Decrypted message: " + dec);
    }

    public static int gcdExtended(int a, int b) {
        return gcdExtended(a, b, 1, 1);
    }

    private static int gcdExtended(int a, int b, int x, int y) {
        if (a == 0) {
            x = 0;
            y = 1;
            return b;
        }

        int x1 = 1, y1 = 1;

        return gcdExtended(b % a, a, x1, y1);
    }
}
