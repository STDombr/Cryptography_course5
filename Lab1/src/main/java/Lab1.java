import java.util.*;

public class Lab1 {

    public static void main(String[] args) {

        char[] input = new char[]{'t', 'e', 's', 't', ' ', 'm', 'e', 's', 's', 'a', 'g', 'e'};
        String key1 = "key1";
        String key2 = "key2";
        System.out.println("Input text ='" + new String(input) + "', key1 = '" + key1 + "', key2 = '" + key2 + "'");

        char[] encrypted = encrypt(input, key1, key2);
        System.out.println("Encrypted text ='" + new String(encrypted) + "'");

        char[] decrypted = decrypt(encrypted, key1, key2);
        System.out.println("Decrypted text ='" + new String(decrypted) + "'");
    }

    public static char[] encrypt(char[] word, String key1, String key2) {

        int columns = key1.length() + 1;
        int rows = key2.length() + 1;

        char[][] arr = new char[rows][columns];

        for (int i = 1; i < columns; i++) {
            arr[0][i] = key1.charAt(i - 1);
        }

        for (int i = 1; i < rows; i++) {
            arr[i][0] = key2.charAt(i - 1);
        }

        for (int i = 0; i < word.length; i++) {
            arr[i / (columns - 1) + 1][i % (rows - 1) + 1] = word[i];
        }

        for (int i = 0; i < key1.length() - 1; i++) {
            for (int j = 0; j < key1.length() - i - 1; j++) {
                if (arr[0][j + 1] > arr[0][j + 2]) {
                    for (int k = 0; k < rows; k++) {
                        char temp = arr[k][j + 1];
                        arr[k][j + 1] = arr[k][j + 2];
                        arr[k][j + 2] = temp;
                    }
                }
            }
        }

        for (int i = 0; i < key2.length() - 1; i++) {
            for (int j = 0; j < key2.length() - i - 1; j++) {
                if (arr[j + 1][0] > arr[j + 2][0]) {
                    for (int k = 0; k < columns; k++) {
                        char temp = arr[j + 1][k];
                        arr[j + 1][k] = arr[j + 2][k];
                        arr[j + 2][k] = temp;
                    }
                }
            }
        }

        char[] result = new char[(rows - 1) * (columns - 1)];
        for (int i = 1; i < rows; i++) {
            System.arraycopy(arr[i], 1, result, (i - 1) * (columns - 1) + 1 - 1, columns - 1);
        }

        return result;
    }

    public static char[] decrypt(char[] word, String key1, String key2) {

        char[] temp = key1.toCharArray();
        Arrays.sort(temp);
        String sortKey1 = new String(temp);

        temp = key2.toCharArray();
        Arrays.sort(temp);
        String sortKey2 = new String(temp);

        int columns = key1.length() + 1;
        int rows = key2.length() + 1;

        char[][] arr = new char[rows][columns];

        for (int i = 1; i < columns; i++) {
            arr[0][i] = sortKey1.charAt(i - 1);
        }

        for (int i = 1; i < rows; i++) {
            arr[i][0] = sortKey2.charAt(i - 1);
        }

        for (int i = 0; i < word.length; i++) {
            arr[i / (columns - 1) + 1][i % (rows - 1) + 1] = word[i];
        }

        char[][] tempArr = arr.clone();

        for (int i = 0; i < rows - 1; i++) {
            arr[i + 1] = tempArr[sortKey2.indexOf(key2.charAt(i)) + 1];
        }

        char[] result = new char[(rows - 1) * (columns - 1)];
        for (int i = 1; i < columns; i++) {
            int index = sortKey1.indexOf(key1.charAt(i - 1));
            for (int j = 1; j < rows; j++) {
                result[(j - 1) * (columns - 1) + i - 1] = arr[j][index + 1];
            }
        }

        return result;
    }
}
