package ru.crypt.controller;

import ru.crypt.model.Square;
import ru.crypt.transform.DeltaTransform;
import ru.crypt.transform.IpsilonTransform;
import ru.crypt.transform.PiTransform;
import ru.crypt.transform.ThetaTransform;

import java.util.Arrays;

public class Crypt {
    private static boolean print = true;
    private static void print(Square square){
        if(print)
        Arrays.stream(square.getSquare())
                .forEach(v -> System.out.println(Arrays.toString(v)));
    }

    public static Square roundDecrypt(Square toDecrypt, Square key){

        System.out.println("Start, round");
        print(toDecrypt);

        System.out.println("Delta:");
        Square delta = DeltaTransform.transform(toDecrypt, key.getSquare());
        print(delta);

        Square pi = PiTransform.transform(delta);
        System.out.println("Pi:");
        print(pi);

        Square ipsilon = IpsilonTransform.transform(pi, true);
        System.out.println("Ipsilon:");
        print(ipsilon);

        Square theta = ThetaTransform.transform(ipsilon, true);
        System.out.println("Theta:");
        print(theta);

        return theta;
    }

    public static Square roundEncrypt(Square toEncrypt, Square key){

        System.out.println("Start, round");
        print(toEncrypt);

        Square theta = ThetaTransform.transform(toEncrypt, false);
        System.out.println("Theta:");
        print(theta);

        Square ipsilon = IpsilonTransform.transform(theta, false);
        System.out.println("Ipsilon:");
        print(ipsilon);

        Square pi = PiTransform.transform(ipsilon);
        System.out.println("Pi:");
        print(pi);

        System.out.println("Delta:");
        Square delta = DeltaTransform.transform(pi, key.getSquare());
        print(delta);

        return delta;
    }

    public static Square firstRound(Square square, Square key, boolean isReverse){
        Square theta = ThetaTransform.transform(square, !isReverse);
        System.out.println("Theta:");
        print(theta);

        System.out.println("Delta:");
        Square delta = DeltaTransform.transform(theta, key.getSquare());
        print(delta);

        return delta;
    }
}
