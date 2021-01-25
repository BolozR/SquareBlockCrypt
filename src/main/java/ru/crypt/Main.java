package ru.crypt;

import ru.crypt.controller.IpsilonTransform;
import ru.crypt.controller.PiTransform;
import ru.crypt.controller.ThetaTransform;
import ru.crypt.model.Square;

import java.util.Arrays;


public class Main {

    private static void print(Square square){
        Arrays.stream(square.getSquare())
                .forEach(v -> System.out.println(Arrays.toString(v)));
    }

    public static void main(String[] args) {
        Square square = new Square();
        square.setSquare(new int[][]{
                {0x02, 0x01, 0x01, 0x03},
                {0x03, 0x02, 0x01, 0x01},
                {0x01, 0x03, 0x02, 0x01},
                {0x01, 0x01, 0x03, 0x02}});

        Square theta = ThetaTransform.transform(square);
        System.out.println("Theta:");
        print(theta);

        Square ipsilon = IpsilonTransform.transform(theta);
        System.out.println("Ipsilon:");
        print(ipsilon);

        Square pi = PiTransform.transform(ipsilon);
        System.out.println("Pi:");
        print(pi);

    }


}
