package ui;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Cli(1) or Gui(2): ");
        int selection = scanner.nextInt();
        switch (selection) {
            case 1: new SimpleTypingTestCli(); break;
            case 2: new SimpleTypingTestGui(); break;
            default: System.exit(0);
        }
        scanner.close();
    }
}
