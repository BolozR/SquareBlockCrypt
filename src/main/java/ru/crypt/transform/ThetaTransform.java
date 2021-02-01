package ru.crypt.transform;

import ru.crypt.model.Square;

public class ThetaTransform {

    private static int[] cParam  = {3, 5, 3, 5};
    private static int[] dParam = {171, 205, 171, 205};

    public static Square transform(Square square, boolean isReverse){
        Square result = new Square();
        int[][] sq = square.getSquare();
        int[] param = isReverse ? dParam : cParam;
        for(int i = 0; i < sq.length; i++){
            for(int j = 0; j < sq[0].length; j++){
                result.getSquare()[i][j] =
                       (mult256(param[3], sq[i][0]) ^
                        mult256(param[2], sq[i][1]) ^
                        mult256(param[1], sq[i][2]) ^
                        mult256(param[0], sq[i][3]));
            }
        }
        return square;
    }

    private static int mult256(int a, int b){
        return (a * b) % 256;
    }


}
