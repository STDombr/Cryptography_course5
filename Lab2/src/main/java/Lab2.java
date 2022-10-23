import java.util.Arrays;

public class Lab2 {

    public static char[][] mapping = new char[][]{
            {'a', 'b', 'c', 'd', 'e'},
            {'f', 'g', 'h', 'i', 'j'},
            {'k', 'l', 'm', 'n', 'o'},
            {'p', 'q', 'r', 's', 't'},
            {'u', 'v', 'w', 'x', 'y'}
    };

    public static void main(String[] args) {

        char[] input = new char[]{'t', 'e', 's', 't', 'm', 'e', 's', 's', 'a', 'g', 'e'};
        System.out.println("Input text ='" + new String(input) + "'");

        char[] encrypted = encrypt(input);
        System.out.println("Encrypted text ='" + new String(encrypted) + "'");

        char[] decrypted = decrypt(encrypted);
        System.out.println("Decrypted text ='" + new String(decrypted) + "'");
    }

    public static char[] encrypt(char[] word) {

        int[] numbers = new int[word.length * 2];
        char[] result = new char[word.length];

        for (int i = 0; i < word.length; i++) {
            int row = (word[i] - 97) / 5 + 1;
            int column = (word[i] - 97) % 5 + 1;

            numbers[i] = row;
            numbers[word.length + i] = column;
        }

        for (int i = 0; i < word.length; i++) {
            result[i] = (char) (96 + (numbers[i * 2] - 1) * 5 + numbers[i * 2 + 1]);
        }

        return result;
    }

    public static char[] decrypt(char[] word) {

        int[] numbers = new int[word.length * 2];
        char[] result = new char[word.length];

        for (int i = 0; i < word.length; i++) {
            int row = (word[i] - 97) / 5 + 1;
            int column = (word[i] - 97) % 5 + 1;

            numbers[i * 2] = row;
            numbers[i * 2 + 1] = column;
        }

        for (int i = 0; i < word.length; i++) {
            result[i] = (char) (96 + (numbers[i] - 1) * 5 + numbers[word.length + i]);
        }

        return result;
    }
}
