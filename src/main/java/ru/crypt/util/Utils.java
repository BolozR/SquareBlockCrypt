package ru.crypt.util;

import ru.crypt.controller.Crypt;
import ru.crypt.model.Key;
import ru.crypt.model.Mode;
import ru.crypt.model.Square;

import java.util.Arrays;

public class Utils {

    private static Square[] keys = keysGen();

    private static Square[] keysGen(){
        Square[] keys = new Square[9];
        keys[0] = Key.key;
        for (int i = 1; i < 9; i++){
            keys[i] = Key.keyGen(keys[i-1], i);
        }
        return keys;
    }

    public static void print(Square square){
        Arrays.stream(square.getSquare())
                .forEach(v -> System.out.println(Arrays.toString(v)));
    }

    public static int[][] array2nding(int[] in){
        int[][] res = new int[4][4];
        int count = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                res[i][j] = in[count++];
            }
        }
        Square sq = new Square();
        sq.setSquare(res);
        return res;
    }

    public static void example(){
        Square to = new Square();
        int[][] toEncrypt = {
                {121, 111, 111, 113},
                {113, 121, 111, 111},
                {111, 113, 112, 111},
                {111, 111, 113, 121}};
        to.setSquare(toEncrypt);
        Square[] keys = keysGen();

        Square first = Crypt.firstRound(to, keys[0], false);
        System.out.println("First:");
        print(first);
        Square encrypted = Crypt.roundEncrypt(first, keys[1]);
        System.out.println("Encrypted:");
        print(encrypted);

        Square decrypted = Crypt.roundDecrypt(encrypted, keys[1]);
        Square result = Crypt.firstRound(decrypted, keys[0], true);
        System.out.println("Decrypted:");
        print(result);
    }

    public static int[][] encrypt(int[][] toEncrypt, Mode mode){
        Square to = new Square();
        to.setSquare(toEncrypt);
        Square encrypted;

        switch (mode){
            case ECB: {
                encrypted = Crypt.roundEncrypt(to, keys[0]);
                break;
            }
            case CBC: {
                encrypted = Crypt.roundEncrypt(to, keys[1]);
                for (int i = 2; i < 9; i++) {
                    encrypted = Crypt.roundEncrypt(encrypted, keys[i]);
                }
                break;
            }
            case OFB: {
                Square first = Crypt.firstRound(to, keys[0], false);
                encrypted = Crypt.roundEncrypt(first, keys[1]);
                encrypted = Crypt.ofbMode(encrypted, toEncrypt);
                for (int i = 2; i < 9; i++) {
                    encrypted = Crypt.roundEncrypt(encrypted, keys[i]);
                    encrypted = Crypt.ofbMode(encrypted, toEncrypt);
                }
                break;
            }
            default: {
                Square first = Crypt.firstRound(to, keys[0], false);
                encrypted = Crypt.roundEncrypt(first, keys[1]);
                for (int i = 2; i < 9; i++) {
                    encrypted = Crypt.roundEncrypt(encrypted, keys[i]);
                }
                break;
            }
        }

        return encrypted.getSquare();
    }

    public static int[][] decrypt(int[][] toDecrypt, Mode mode){
        Square decrypted = new Square();
        decrypted.setSquare(toDecrypt);

        switch (mode){
            case ECB: {
                decrypted = Crypt.roundDecrypt(decrypted, keys[0]);
                break;
            }
            case CBC: {
                for (int i = 8; i >= 1; i--) {
                    decrypted = Crypt.roundDecrypt(decrypted, keys[i]);
                }
                break;
            }
            case OFB: {
                for (int i = 8; i >= 1; i--) {
                    decrypted = Crypt.roundDecrypt(decrypted, keys[i]);
                    decrypted = Crypt.ofbMode(decrypted, toDecrypt);
                }
                decrypted = Crypt.firstRound(decrypted, keys[0], true);
                break;
            }
            default: {
                for (int i = 8; i >= 1; i--) {
                    decrypted = Crypt.roundDecrypt(decrypted, keys[i]);
                }
                decrypted = Crypt.firstRound(decrypted, keys[0], true);
                break;
            }
        }

        return decrypted.getSquare();
    }
}
