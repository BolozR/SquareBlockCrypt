package ru.crypt.transform;

import ru.crypt.model.Square;

public class PiTransform {

    public static Square transform(Square square){
        Square result = new Square();
        int[][] sq = square.getSquare();
        for(int i = 0; i < sq.length; i++){
            for(int j = 0; j < sq[0].length; j++){
                result.getSquare()[i][j] = sq[j][i];
            }
        }
        return result;
    }
}
