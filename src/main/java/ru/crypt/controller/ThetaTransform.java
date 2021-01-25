package ru.crypt.controller;

import ru.crypt.model.Square;

public class ThetaTransform {

    private static int[] cParam  = {0x02, 0x01, 0x01, 0x03};

    public static Square transform(Square square){
        Square result = new Square();
        int[][] sq = square.getSquare();
        for(int i = 0; i < sq.length; i++){
            for(int j = 0; j < sq[0].length; j++){
                result.getSquare()[i][j] =  (mult256(cParam[j], sq[i][0]) ^ mult256(cParam[j], sq[i][1])
                                        ^ mult256(cParam[j], sq[i][2]) ^ mult256(cParam[j], sq[i][3])) % 256;
            }
        }
        return result;
    }

    private static int mult256(int a, int b){
        return (int) ((a * b) % 256);
    }
}
