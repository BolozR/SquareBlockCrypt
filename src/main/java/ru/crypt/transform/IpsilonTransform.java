package ru.crypt.transform;

import ru.crypt.model.Square;

import java.util.ArrayList;
import java.util.Arrays;

public class IpsilonTransform {

    private static final ArrayList<Integer> table = new ArrayList<>();
    static {
        Arrays.stream(new int[]
                {177, 206, 195, 149,  90, 173, 231,   2,  77,  68, 251, 145,  12, 135, 161,  80,
                        203, 103,  84, 221,  70, 143, 225,  78, 240, 253, 252, 235, 249, 196,  26, 110,
                        94, 245, 204, 141,  28,  86,  67, 254,   7,  97, 248, 117,  89, 255,   3,  34,
                        138, 209,  19, 238, 136,   0,  14,  52,  21, 128, 148, 227, 237, 181,  83,  35,
                        75,  71,  23, 167, 144,  53, 171, 216, 184, 223,  79,  87, 154, 146, 219,  27,
                        60, 200, 153,   4, 142, 224, 215, 125, 133, 187,  64,  44,  58,  69, 241,  66,
                        101,  32,  65,  24, 114,  37, 147, 112,  54,   5, 242,  11, 163, 121, 236,   8,
                        39,  49,  50, 182, 124, 176,  10, 115,  91, 123, 183, 129, 210,  13, 106,  38,
                        158,  88, 156, 131, 116, 179, 172,  48, 122, 105, 119,  15, 174,  33, 222, 208,
                        46, 151,  16, 164, 152, 168, 212, 104,  45,  98,  41, 109,  22,  73, 118, 199,
                        232, 193, 150,  55, 229, 202, 244, 233,  99,  18, 194, 166,  20, 188, 211,  40,
                        175,  47, 230,  36,  82, 198, 160,   9, 189, 140, 207,  93,  17,  95,   1, 197,
                        159,  61, 162, 155, 201,  59, 190,  81,  25,  31,  63,  92, 178, 239,  74, 205,
                        191, 186, 111, 100, 217, 243,  62, 180, 170, 220, 213,   6, 192, 126, 246, 102,
                        108, 132, 113,  56, 185,  29, 127, 157,  72, 139,  42, 218, 165,  51, 130,  57,
                        214, 120, 134, 250, 228,  43, 169,  30, 137,  96, 107, 234,  85,  76, 247, 226})
                .forEach(v -> table.add(v));

        System.out.println(table);
    }

    public static Square transform(Square square, boolean isReverse){
        Square result = new Square();
        int[][] sq = square.getSquare();
        int rev = isReverse ? -1 : 1;
        for(int i = 0; i < sq.length; i++){
            for(int j = 0; j < sq[0].length; j++){
                result.getSquare()[i][j] = table.get(getIndex(sq[i][j], rev));//(table.indexOf(sq[i][j]) + rev) % table.size());
            }
        }
        return result;
    }

    private static int getIndex(int value, int rev){
        int res = (table.indexOf(value) + rev);
        if (rev == -1 && res < 0){
            res += 256;
        }
        return res % table.size();
    }
}
