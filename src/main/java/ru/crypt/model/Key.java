package ru.crypt.model;


public class Key {

    public static Square key = new Square();
    static {
        key.setSquare(new int[][]{
                {(int) 'a', (int) 'b', (int) 'c', (int) 'd'},
                {(int) 'e', (int) 'f', (int) 'g', (int) 'h'},
                {(int) 'i', (int) 'j', (int) 'k', (int) 'l'},
                {(int) 'm', (int) 'n', (int) 'o', (int) 'p'}});
    }
    private static int[] cConst = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 1};

    public static Square keyGen(Square prevSq, int round){
        int[][] prevKey = prevSq.getSquare();
        Square keys = new Square();
        //for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                keys.getSquare()[0][j] = (prevKey[0][j] ^ rotl(prevKey[3][j]) ^ cConst[round]);
                keys.getSquare()[1][j] = (prevKey[1][j] ^ keys.getSquare()[0][j]);
                keys.getSquare()[2][j] = (prevKey[2][j] ^ keys.getSquare()[1][j]);
                keys.getSquare()[3][j] = (prevKey[3][j] ^ keys.getSquare()[2][j]);
            }
        //}
        return keys;
    }

    private static int rotl(int a) {
        int x = a & 0xFF;
        return ((x << 1) | (x >> 7)) & 0xFF;
    }
}
