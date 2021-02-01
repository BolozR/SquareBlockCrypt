package ru.crypt.transform;

import ru.crypt.model.Square;

public class DeltaTransform {

    public static Square transform(Square square, int[][] roundKey){
        Square result = new Square();
        int[][] sq = square.getSquare();
        for(int i = 0; i < sq.length; i++){
            for(int j = 0; j < sq[0].length; j++){
                result.getSquare()[i][j] = (sq[i][j] ^ roundKey[i][j]);
            }
        }
        return result;
    }
}
