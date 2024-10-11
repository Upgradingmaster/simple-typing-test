package ui;

import java.util.Scanner;

import ui.SimpleTypingTest;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        new SimpleTypingTest(scanner);
        scanner.close();
    }
}
