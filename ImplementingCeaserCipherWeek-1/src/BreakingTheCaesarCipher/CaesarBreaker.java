package BreakingTheCaesarCipher;

import ImplementingCeaserCipher.CeaserCipher;
import edu.duke.FileResource;

public class CaesarBreaker {
    static String decrypt(String encrypted) {
        CeaserCipher cipher = new CeaserCipher();
        int getdkey = getKey(encrypted);
        return cipher.encrypt(encrypted, 26 - getdkey);
    }

    static int[] countLetters(String message) {
        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        int count[] = new int[26];
        for (int i = 0; i < message.length(); i++) {
            char ch = Character.toLowerCase(message.charAt(i));
            int index = alphabets.indexOf(ch);
            if (index != -1) {
                count[index]++;
            }
        }
        return count;
    }

    static int maxIndex(int values[]) {
        int maxValue = Integer.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > maxValue) {
                maxValue = values[i];
                index = i;
            }
        }
        return index;
    }

    static void testDecrypt() {
        FileResource resource = new FileResource();
        CeaserCipher cipher = new CeaserCipher();
        String message = resource.asString();
        String encrypted = cipher.encrypt(message, 20);
        String decrypted = decrypt(encrypted);
        System.out.println("Encrypted message = " + encrypted + "\n Decrypted message  = " + decrypted);

    }

    static String halfOfString(String message, int start) {
        StringBuilder halfOfString = new StringBuilder();
        for (int i = start; i < message.length(); i += 2) {
            halfOfString.append(message.charAt(i));
        }
        return halfOfString.toString();
    }

    static void testHalfOfString() {

//        System.out.println(halfOfString("Qbkm Zgis", 0));
//        System.out.println(halfOfString("Qbkm Zgis", 1));

        FileResource resource = new FileResource();
        String message = resource.asString();
        System.out.println(message);
        String halOfMessage = halfOfString(message, 0);

        CeaserCipher cipher = new CeaserCipher();
        String encrypted = cipher.encrypt(halOfMessage, 20);
        String decrypted = decrypt(encrypted);
        System.out.println("Encrypted message = " + encrypted + "\n Decrypted message  = " + decrypted);

    }

    static int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }

    static String decryptTwoKeys(String encrypted) {
        String halfOfFirst = halfOfString(encrypted, 0);
        String halfOfSecond = halfOfString(encrypted, 1);
        //System.out.println("Half of first string is " + halfOfFirst);
        //System.out.println("Second of first string is " + halfOfSecond);

        int key1 = getKey(halfOfFirst);
        int key2 = getKey(halfOfSecond);

        System.out.println("Two keys found are key1= " + key1 + ", key2= " + key2);
        CeaserCipher cipher = new CeaserCipher();
        System.out.println("Decrypted message is " + "\n" + cipher.encryptTwoKeys(encrypted, 26 - key1, 26 - key2));

        return "";
    }

    static void testDecryptTwoKeys() {
        FileResource resource = new FileResource();
        CeaserCipher cipher = new CeaserCipher();
        String message = resource.asString();
        String encrypted = cipher.encrypt(message, 20);
        System.out.println("Encrypted message is " + "\n" + encrypted);
        decryptTwoKeys(encrypted);
    }

    public static void main(String args[]) {
        //   testDecrypt();
        // testHalfOfString();
        testDecryptTwoKeys();
    }
}
