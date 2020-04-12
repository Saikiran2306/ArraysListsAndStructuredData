package ObjectOrientedCaesarCipher;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher()
    {

    }

    public CaesarCipher(int key) {
        mainKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    }

    public String encrypt(String input) {
        StringBuilder encyptedMessage = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            int index = alphabet.indexOf(input.charAt(i));
            if (index != -1) {
                encyptedMessage.append(shiftedAlphabet.charAt(index));
            } else if (Character.isAlphabetic(input.charAt(i))) {
                // getting ascii value of uppercase character
                int getAsciiValue = input.charAt(i) - 32;
                // converting it to the character
                char ch = (char) getAsciiValue;
                // getting the shifted character index of tha above character
                index = alphabet.indexOf(ch);
                //System.out.println(input.charAt(i)+" "+(char)getAsciiValue);
                // converting it to lowercase character
                char lowCase = (char) (shiftedAlphabet.charAt(index) + 32);
                encyptedMessage.append(lowCase);
            } else {
                encyptedMessage.append(input.charAt(i));
            }
        }
        return encyptedMessage.toString();
    }

    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }

}
