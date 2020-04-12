package ObjectOrientedCaesarCipher;

import edu.duke.FileResource;

public class TestCaesarCipher {

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

    void simpleTests() {
        FileResource resource = new FileResource();
        String message = resource.asString();
        CaesarCipher cipher = new CaesarCipher(18);
        String encryptedMeaasge = cipher.encrypt(message);
        System.out.println("Encrypted string is " + encryptedMeaasge);
        String decryptedMeaasge = breakCaesarCipher(encryptedMeaasge);
        System.out.println("Decrypted string is " + decryptedMeaasge);

    }

    int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }

    String breakCaesarCipher(String input) {
        int findKey = getKey(input);
        CaesarCipher cipher = new CaesarCipher(findKey);
        return cipher.decrypt(input);
    }

    public static void main(String args[]) {
        TestCaesarCipher cc = new TestCaesarCipher();
        cc.simpleTests();
    }

}
