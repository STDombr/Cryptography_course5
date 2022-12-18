import java.math.BigInteger;
import java.util.List;
import java.util.Random;

public class DiffieHellman {

    public static Random random = new Random();

    private static long power(long a, long b, long p) {
        if (b == 1) {
            return a;
        } else {
            return (((long) Math.pow(a, b)) % p);
        }
    }

    public static void main(String[] args) {

        long p = 0, g, x, a, y, b, ka, kb;

        while (p == 0) {
            int tmp = random.nextInt(1000) + 2;
            if (isPrime(new BigInteger(tmp, random), 3)) {
                p = tmp;
            }
        }
        
        System.out.println("The value of P:" + p);

        g = getG(p);
        System.out.println("The value of G:" + g);

        a = 10;
        System.out.println("The private key a for Alice:" + a);

        x = power(g, a, p);

        b = 20;
        System.out.println("The private key b for Bob:" + b);

        y = power(g, b, p);

        ka = power(y, a, p);
        kb = power(x, b, p);

        System.out.println("Secret key for the Alice is:" + ka);
        System.out.println("Secret key for the Bob is:" + kb);
    }

    private static final BigInteger THREE = new BigInteger("3");

    public static boolean isPrime(BigInteger n, int maxIterations) {

        if (n.compareTo(THREE) < 0) {
            return true;
        }

        int s = 0;
        // n - 1 = 2^s * d, where d % 2 = 1
        BigInteger d = n.subtract(BigInteger.ONE);

        while (d.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            s++;
            d = d.divide(BigInteger.TWO);
        }

        for (int i = 0; i < maxIterations; i++) {
            BigInteger a = uniformRandom(BigInteger.TWO, n.subtract(BigInteger.ONE));
            // x = a^d mod n
            BigInteger x = a.modPow(d, n);

            if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) {
                continue;
            }

            int r = 0;
            for (; r < s; r++) {
                // x = x^2 mod n
                x = x.modPow(BigInteger.TWO, n);

                if (x.equals(BigInteger.ONE)) {
                    return false;
                }

                if (x.equals(n.subtract(BigInteger.ONE))) {
                    break;
                }
            }

            if (r == s) {
                return false;
            }
        }

        return true;
    }

    private static BigInteger uniformRandom(BigInteger bottom, BigInteger top) {

        BigInteger res;
        do {
            res = new BigInteger(top.bitLength(), random);
        } while (res.compareTo(bottom) < 0 || res.compareTo(top) > 0);

        return res;
    }

    public static long getG(long p) {
        while (true) {
            long g = 2 + random.nextInt((int) (p - 2));
            if (isPrimitiveRoot(g, p)) {
                return g;
            }
        }
    }

    public static boolean isPrimitiveRoot(long g, long p) {
        for (long i = 1; i < p - 1; i++) {
            if (power(g, i, p) == 1) {
                return false;
            }
        }
        return true;
    }
}
