package ObjectOrientedCaesarCipher;

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftAlphabet1;
    private String shiftAlphabet2;
    int key1;
    int key2;

    public CaesarCipherTwo(int key11, int key22) {
        key1 = key11;
        key2 = key22;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    }

    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if (idx != -1 && i % 2 == 0) {
                if (currChar == Character.toUpperCase(currChar)) {
                    char newChar = shiftAlphabet1.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                } else {
                    char newChar = shiftAlphabet1.charAt(idx);
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                }
            } else if (idx != -1 && i % 2 == 1) {
                if (currChar == Character.toUpperCase(currChar)) {
                    char newChar = shiftAlphabet2.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                } else {
                    char newChar = shiftAlphabet2.charAt(idx);
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                }
            }
        }
        return encrypted.toString();
    }

    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - key1, 26 - key2);
        return cc.encrypt(input);
    }
}
