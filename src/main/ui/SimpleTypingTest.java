package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import model.*;

// An application to test typing with random sentences and review statistics of tests
public class SimpleTypingTest {
    private Statistics stats = new Statistics();

    private volatile boolean doneTyping = false;

    // Constants
    private final int mainMenuOptionCount;
    private final String wordsFilePath;
    private final int wordsFileMax;
    private Scanner scanner;

    /*
     * MODIFIES: this
     * EFFECTS: initializes the variables
     *          starts the main appplication processes/methods
     */
    public SimpleTypingTest(Scanner scanner) {
        mainMenuOptionCount = 4;
        wordsFilePath = "data/words.txt";
        wordsFileMax = 25;
        this.scanner = scanner;
        startupSequence();

    }

    /*
     * EFFECTS: starts the main appplication processes/methods,
     *          can be used to restart
     */
    public void startupSequence() {
        //scanner = new Scanner(System.in);
        displayMainMenu();
        int choice = awaitUserInput(1, mainMenuOptionCount, "\n\n\t---Invalid Option---\n\n");
        handleMainMenuCases(choice);
    }

    /*
     * EFFECTS: displays the main menu 
     */
    @SuppressWarnings("lineLength")
    public void displayMainMenu() {
        System.out.println("\nSimpleTypingTest:\n\t(1) Start a test\n\t(2) View past statistics\n\t(3) View Graph\n\t(4) Quit");
    }


    /*
     * REQUIRES: min and max values to define a range
     *           min <= max
     *           an error string to display to the user
     *           when they input anything invalid
     * EFFECTS: wait for a valid user input in the given 
     *          range of integers
     */
    @SuppressWarnings("methodLength")
    public int awaitUserInput(int min, int max, String errorString) {
        int option = 0;
        boolean error = false;
        do {
            if (error) {
                System.out.println(errorString);
            }
            try {
                System.out.print(">> ");
                option = new Scanner(System.in).nextInt();
                if ((option < min) || (option > max)) {
                    error = true;
                    continue;
                }
                error = false;
            } catch (InputMismatchException e) {
                error = true;
                scanner.next();
            } catch (NoSuchElementException e) {
                //e.printStackTrace();
            }
        } while (error);
        return option;
    }

    /*
     * REQUIRES: a valid option
     * EFFECTS: executes the respective method based
     *          on the user's choice
     *          
     */
    public void handleMainMenuCases(int choice) {
        switch (choice) {
            case 1:
                startTestProcess();
                break;
            case 2: 
                viewAllStatistics();
                break;
            case 3: 
                showGraph();
                break;
            case 4:System.exit(0);
        } 
    }

    /*
     * EFFECTS: handles calling all the necassary methods
     *           to complete a test from start to finish
     */
    @SuppressWarnings("methodLength")
    public void startTestProcess() {
        int wordCount = chooseWordsCount();
        System.out.print("\n\n");
        int totalDuration = chooseTotalDuration();
        System.out.print("\n\n");

        String randomSentence = generateRandomSentence(wordCount, new File(wordsFilePath));
        System.out.println("Sentence: " + randomSentence);

        System.out.println("\nStarting in 5 seconds.");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Start!\n");
        System.out.println("Sentence: " + randomSentence);
        long start = System.currentTimeMillis();
        String userSentence = readUserInput(totalDuration);
        long end = System.currentTimeMillis();
        int userDuration = (int) TimeUnit.MILLISECONDS.toSeconds(end - start);
        if (userSentence.equals("")) {
            System.out.println("\t---No sentence recorded--\n"); 
            startupSequence();
            return;
        }
        Statistic statistic = new Statistic(randomSentence, userSentence, totalDuration, userDuration);

        System.out.print("\n\n");

        displayStatistic(statistic);
        stats.getStats().add(statistic);

        startupSequence();
    }


    /*
     * EFFECTS: prompt the user to choose
     *          how many words the test should be
     *          then returns this value
     */
    public int chooseWordsCount() {
        System.out.println("How many words to test (Maximum of 25): ");
        return awaitUserInput(1,25, "\n\n\t---Invalid Selection---\n\n");
    }

    /*
     * EFFECTS: prompt the user to choose
     *          how long the test should be
     *          then returns this value
     */
    public int chooseTotalDuration() {
        System.out.println("How long should the test be in seconds (Maximum of 30s): ");
        return awaitUserInput(1, 30, "\n\n\t---Invalid Selection---\n\n");
    }

    /*
     * EFFECTS: pick 'wordCount' random words from the 'file'
     *          then concatenate and return
     */
    public String generateRandomSentence(int wordCount, File file) {
        List<String> words = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String word;
            while ((word = br.readLine()) != null) {
                words.add(word); 
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }

        Collections.shuffle(words);
        String randomSentence = "";
        for (int i = 0; i < wordCount; i++)  {
            randomSentence += words.get(i) + " ";
        }
        return randomSentence;
    }



    private String userSentence = "";

    /*
     * EFFECTS: reads the user input for the test
     *          and returns it
     */
    @SuppressWarnings("methodLength")
    public String readUserInput(int totalDuration) {

        Thread inputThread = new Thread(() -> {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
            try {
                // Workaraound to make readline non-blocking
                while (System.in.available() < 1) {
                    Thread.sleep(200);
                }

                userSentence = br.readLine();

            } catch (InterruptedException e) {
                System.out.println("\nTime's up!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread timerThread = new Thread(() -> {
            try {
                Thread.sleep(totalDuration * 1000); 
                inputThread.interrupt();
            } catch (InterruptedException e) {
                // Do nothing 
            }
        });

        inputThread.start();
        timerThread.start();

        try {
            inputThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timerThread.interrupt();
        return userSentence;
    } 


    /*
     * REQUIRES: a statistic to print
     * EFFECTS:  displays a given statistic
     */
    public void displayStatistic(Statistic s) {
        System.out.println("\n" + s.toString() + "\n");
    }



    /*
     * EFFECTS: iterates over Statistics printing each one out
     */
    public void viewAllStatistics() {
        if (stats.getStats().size() == 0) { 
            System.out.println("\t---No statistics to show---\n"); 
            startupSequence();
            return;
        }
        ArrayList<Statistic> a = stats.getStats();
        int len = a.size();
        for (int i = 0; i < len; i++) {
            System.out.print("(" + i + ")");
            displayStatistic(a.get(i));
        }
        System.out.println("Inspect a statistic: ");
        int id = awaitUserInput(1, len, "\n\n\t---Invalid Selection---\n\n");
        displayStatistic(a.get(id));
    }

    /* 
     * REQUIRES: this
     * EFFECTS: iterates over Statistics printing each one out
     */
    public void showGraph() {
        if (stats.getStats().size() == 0) { 
            System.out.println("\t---No statistics to show---\n"); 
        } else {
            System.out.println(stats.generateGraph() + "\n");
        }
        startupSequence();
    }

}
