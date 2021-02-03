package ru.crypt.transform;

import ru.crypt.model.Square;

public class ThetaTransform {

    private static int[][] c = {
            {3, 5, 7, 9},
            {11, 13, 15, 17},
            {19, 21, 23, 25},
            {27, 29, 31, 33}};
    private static int[][] d ={
            {171, 205, 183, 57},
            {163, 197, 239, 241},
            {27, 61, 167, 41},
            {19, 53, 223, 225}};

    public static Square transform(Square square, boolean isReverse){
        Square result = new Square();
        int[][] sq = square.getSquare();
        int[][] param = isReverse ? d : c;
        for(int i = 0; i < sq.length; i++){
            for(int j = 0; j < sq[0].length; j++){
                result.getSquare()[i][j] = mult256(param[i][j], sq[i][j]);
            }
        }
        return result;
    }

    private static int mult256(int a, int b){
        return (a * b) % 256;
    }


}
