package ru.crypt.controller;

import ru.crypt.model.Square;
import ru.crypt.transform.DeltaTransform;
import ru.crypt.transform.IpsilonTransform;
import ru.crypt.transform.PiTransform;
import ru.crypt.transform.ThetaTransform;

import java.util.Arrays;

public class Crypt {
    private static boolean print = false;
    private static void print(Square square, String name){
        if(print) {
            System.out.println(name);
            Arrays.stream(square.getSquare())
                    .forEach(v -> System.out.println(Arrays.toString(v)));
        }
    }

    public static Square roundDecrypt(Square toDecrypt, Square key){

        print(toDecrypt,"Start, round");

        Square delta = DeltaTransform.transform(toDecrypt, key.getSquare());
        print(delta, "Delta:");

        Square pi = PiTransform.transform(delta);
        print(pi, "Pi:");

        Square ipsilon = IpsilonTransform.transform(pi, true);
        print(ipsilon, "Ipsilon:");

        Square theta = ThetaTransform.transform(ipsilon, true);
        print(theta, "Theta:");

        return theta;
    }

    public static Square roundEncrypt(Square toEncrypt, Square key){

        print(toEncrypt, "Start, round");

        Square theta = ThetaTransform.transform(toEncrypt, false);
        print(theta, "Theta:");

        Square ipsilon = IpsilonTransform.transform(theta, false);
        print(ipsilon, "Ipsilon:");

        Square pi = PiTransform.transform(ipsilon);
        print(pi, "Pi:");

        Square delta = DeltaTransform.transform(pi, key.getSquare());
        print(delta, "Delta:");

        return delta;
    }

    public static Square firstRound(Square square, Square key, boolean isReverse){
        if(isReverse){
            Square delta = DeltaTransform.transform(square, key.getSquare());
            print(delta, "Delta:");

            Square theta = ThetaTransform.transform(delta, false);
            print(theta, "Theta:");

            return theta;
        } else {
            Square theta = ThetaTransform.transform(square, true);
            print(theta, "Theta:");

            Square delta = DeltaTransform.transform(theta, key.getSquare());
            print(delta, "Delta:");

            return delta;
        }
    }

    public static Square ofbMode(Square square, int[][] text){
        Square result = new Square();
        for (int i = 0; i < square.getSquare().length; i++){
            for(int j = 0; j < square.getSquare()[0].length; j++){
                result.getSquare()[i][j] = square.getSquare()[i][j] ^ text[i][j];
            }
        }
        return result;
    }
}
