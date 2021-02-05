package ru.crypt;

import ru.crypt.model.Mode;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static ru.crypt.util.Utils.*;


public class Main {

    private static void encryptFile(String format, Mode mode){
        try {
            FileInputStream fis = new FileInputStream("./src/main/resources/crypt.".concat(format));
            FileOutputStream fos = new FileOutputStream("./src/main/resources/encrypted.".concat(format));

            int[] input = new int[16];
            while (fis.available() > 16) {
                for (int i = 0; i < 16; i++) {
                    input[i] = fis.read();
                }
                int[][] encrypted = encrypt(array2nding(input), mode);
                for (int[] i : encrypted) {
                    for (int j : i) fos.write((byte) j);
                }
            }

            while (fis.available() != 0) fos.write((byte) fis.read());
            fos.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void decryptFile(String format, Mode mode){
        try {
            Thread.sleep(1000);
            FileInputStream fis = new FileInputStream("./src/main/resources/encrypted.".concat(format));
            FileOutputStream fos = new FileOutputStream("./src/main/resources/decrypted.".concat(format));

            int[] input = new int[16];

            while (fis.available() > 16) {
                for (int i = 0; i < 16; i++) {
                    input[i] = fis.read();
                }
                int[][] encrypted = decrypt(array2nding(input), mode);
                for (int[] j : encrypted) {
                    for (int k : j) fos.write((byte) k);
                }
            }

            while (fis.available() != 0) fos.write((byte) fis.read());
            fos.close();
            fis.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String format = "txt"; // txt or jpg
        Mode mode = Mode.CFB; // ECB CFB CBC works
        encryptFile(format, mode);
        decryptFile(format, mode);
    }

}
