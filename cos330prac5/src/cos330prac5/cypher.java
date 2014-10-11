/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cos330prac5;

/**
 *
 * @author Teshlen
 */
public class cypher {

    private String origionalText = "YTIRUCESZXWVQPONMLKJHGFDBA";
    private String[][] vigenereTableau;
    private String message, passphrase;
    private String encodedMessage;
    private String decodedMessage;

    public
            cypher(String mes, String pas) {
        vigenereTableau = new String[26][26];
        message = mes;
        passphrase = pas;

        encodedMessage = "";
        decodedMessage = "";

        for (int i = 0; i < origionalText.length(); i ++) {
            for (int j = 0; j < origionalText.length(); j ++) {
                vigenereTableau[i][j] = "@";

            }

        }
    }

    public
            void createTableau() {

        for (int i = 0; i < origionalText.length(); i ++) {

            boolean flag = false;
            int count = i;
            for (int j = 0; j < origionalText.length() - i; j ++) {

                vigenereTableau[i][j] = origionalText.charAt(count ++) + "";
            }

            int c = 0;
            for (int j = origionalText.length() - i; j < origionalText.length(); j ++) {

                vigenereTableau[i][j] = origionalText.charAt(c ++) + "";
            }
        }

    }

    public void printTable() {
        for (int i = 0; i < origionalText.length(); i ++) {
            for (int j = 0; j < origionalText.length(); j ++) {
                System.out.print(vigenereTableau[i][j] + " ");

            }
            System.out.println("");
        }

    }

    public
            String encode() {

        String longPassPhrase = "";
        origionalText = origionalText.toLowerCase();

        int numberOfPasses = (message.length() / passphrase.length()) + 10;

        for (int i = 0; i < numberOfPasses; i ++) {
            longPassPhrase += passphrase;
        }

        char letterOfMessage;
        char letterOfPassphrase;
        int indexOfLetterOfMessage;
        int indexOfLetterOfPassphrase;
        for (int i = 0; i < message.length(); i ++) {

            letterOfMessage = message.charAt(i);
            letterOfPassphrase = longPassPhrase.charAt(i);

            indexOfLetterOfMessage = origionalText.indexOf(letterOfMessage);
//            System.out.println("mes" + indexOfLetterOfMessage);
            indexOfLetterOfPassphrase = origionalText.indexOf(letterOfPassphrase);

            encodedMessage += vigenereTableau[indexOfLetterOfMessage][indexOfLetterOfPassphrase];

        }
        System.out.println(encodedMessage);
        return encodedMessage;

    }

    public
            String decrypt(String encodedMessageForDecryption) {
        String longPassPhrase = "";
        origionalText = origionalText.toLowerCase();

        int numberOfPasses = (message.length() / passphrase.length()) + 10;

        for (int i = 0; i < numberOfPasses; i ++) {
            longPassPhrase += passphrase;
        }

        int indexOfOrigionalMessage = 0;
        int indexInLongPassPhrase = 0;
        for (int i = 0; i < encodedMessageForDecryption.length(); i ++) {

            indexInLongPassPhrase = origionalText.indexOf(longPassPhrase.charAt(i) + "");

            for (int j = 0; j < 26; j ++) {
                if (vigenereTableau[indexInLongPassPhrase][j].contains(encodedMessageForDecryption.charAt(i) + "")) {
                    indexOfOrigionalMessage = j;
                }
            }

            decodedMessage += origionalText.charAt(indexOfOrigionalMessage) + "";

        }

        return decodedMessage;
    }

}
