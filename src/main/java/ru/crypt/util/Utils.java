package ru.crypt.util;

import ru.crypt.controller.Crypt;
import ru.crypt.model.Key;
import ru.crypt.model.Square;

import java.util.Arrays;

public class Utils {

    private static Square[] keys = keysGen();

    private static Square[] keysGen(){
        Square[] keys = new Square[8];
        keys[0] = Key.key;
        for (int i = 1; i < 8; i++){
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
        print(sq);
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

    public static int[][] encrypt(int[][] toEncrypt){
        Square to = new Square();
        to.setSquare(toEncrypt);
        Square first = Crypt.firstRound(to, keys[0], false);
        System.out.printf("Round encrypt: %d\n", 1);
        Square encrypted = Crypt.roundEncrypt(first, keys[1]);
        for (int i = 2; i < 8; i++) {
            System.out.printf("Round encrypt: %d\n", i);
            encrypted = Crypt.roundEncrypt(encrypted, keys[i]);
        }
        return encrypted.getSquare();
    }

    public static int[][] decrypt(int[][] toEncrypt){
        Square decrypted = new Square();
        decrypted.setSquare(toEncrypt);
        for (int i = 7; i >= 1; i--) {
            System.out.printf("Round decrypt: %d\n", i);
            decrypted = Crypt.roundDecrypt(decrypted, keys[i]);
        }
        decrypted = Crypt.firstRound(decrypted, keys[0], true);

        return decrypted.getSquare();
    }
}
