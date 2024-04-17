package util;

import java.util.Random;
import java.util.Scanner;

public class Util {

    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    public Util() {
    }

    public static Random getRandom() {
        return random;
    }

    public static Scanner getScanner() {
        return scanner;
    }
}
