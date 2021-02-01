package ru.crypt;

import ru.crypt.model.Key;
import ru.crypt.model.Square;
import ru.crypt.transform.DeltaTransform;
import ru.crypt.transform.ThetaTransform;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Try {

    private static void print(Square square){
        Arrays.stream(square.getSquare())
                .forEach(v -> System.out.println(Arrays.toString(v)));
        System.out.println("");
    }

    private static int mult256(int a, int b){
        return (a * b)%256;
    }

    private static int rotl(int a) {
        int x = a & 0xFF;
        return ((x << 1) | (x >> 7)) & 0xFF;
    }

    public static void main(String[] args) throws IOException {
        InputStream is1 = Main.class.getClassLoader().getResourceAsStream("crypt.txt");
        InputStream is2 = Main.class.getClassLoader().getResourceAsStream("encrypted.txt");
        InputStream is3 = Main.class.getClassLoader().getResourceAsStream("decrypted.txt");
        for(int i = 0; i < 16; i++){
            System.out.printf("%d %d %d\n", is1.read(), is2.read(), is3.read());
        }
    }

}
