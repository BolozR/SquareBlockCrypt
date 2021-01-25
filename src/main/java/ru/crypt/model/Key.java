package ru.crypt.model;


public class Key {
    public static int[][] key = new int[][]{
            {3, 3, 3, 3},
            {3, 3, 3, 3},
            {3, 3, 3, 3},
            {3, 3, 3, 3}};
    private static int[] cConst = new int[]{1, 2, 4, 8};

    public static void keyGen(){
        int[][][] keys = new int[8][4][4];
        keys[0] = key;
        for(int k = 1; k < 8; k++){
            for (int j = 0; j < 3; j++){
                keys[k][0][j+1] = keys[k-1][0][j] ^ rotl(keys[k-1][3][j]) ^ cConst[j];
            }
        }
    }

    private static int rotl(int a) {
        int x = a & 0xFF;
        return  ((x << 1) | (x >> 7));
    }
}
