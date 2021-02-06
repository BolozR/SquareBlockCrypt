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
            while (fis.available() > 15) {
                for (int i = 0; i < 16; i++) {
                    input[i] = fis.read();
                }
                int[][] encrypted = encrypt(array2nding(input), mode);
                for (int[] i : encrypted) {
                    for (int j : i) fos.write((byte) j);
                }
            }

            int remaind = 0;
            while (fis.available() != 0) {
                input[remaind] = fis.read();
                remaind++;
            }
            for(; remaind < 16; remaind++) {
                input[remaind] = 0;
            }

            int[][] encrypted = encrypt(array2nding(input), mode);
            for (int[] i : encrypted) {
                for (int j : i) {
                    fos.write((byte) j);
                }
            }

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

            while (fis.available() > 15) {
                for (int i = 0; i < 16; i++) {
                    input[i] = fis.read();
                }
                int[][] encrypted = decrypt(array2nding(input), mode);
                for (int[] j : encrypted) {
                    for (int k : j) {
                        if (fis.available() > 15 || k != 0) fos.write((byte) k);
                    }
                }
            }

            fos.close();
            fis.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String format = "jpg"; // txt or jpg
        Mode mode = Mode.CFB; // ECB CFB CBC works
        encryptFile(format, mode);
        decryptFile(format, mode);
    }

}
