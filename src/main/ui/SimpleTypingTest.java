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
import persistence.StateReader;
import persistence.StateWriter;

// An application to test typing with random sentences and review statistics of tests
public class SimpleTypingTest {
    private Statistics stats = new Statistics();

    // Constants
    private final String defaultError;
    private final int mainMenuOptionCount;

    private final String stateFilePath;


    private final String wordsFilePath;
    private final int wordsMax;

    private final int timeMax;
    private final int countdownTime;



    private final Scanner scanner;


    /*
     * MODIFIES: this
     * EFFECTS: initializes the variables
     *          starts the main appplication processes/methods
     */
    public SimpleTypingTest() {
        defaultError = "\n\t---Invalid Selection---\n";
        mainMenuOptionCount = 6;
        stateFilePath = "data/state.json";
        wordsFilePath = "data/words.txt";
        wordsMax = 25;
        timeMax = 30;
        countdownTime = 5;
        scanner = new Scanner(System.in);

        run();
    }


    /*
     * EFFECTS: keeps the program running until 
     *          user quits i.e. System.exit(0)
     */
    public void run(){
        while(true) {
            spawnMenu();
        }
    }


    /*
     * EFFECTS: displays the main menu and waits for a selection
     */
    public void spawnMenu() {
        printMenuOptions();
        int choice = awaitUserInput(0, mainMenuOptionCount - 1, defaultError);
        handleMainMenuCases(choice);
    }

    /*
     * EFFECTS: print the main menu 
     */
    public void printMenuOptions() {
        System.out.println("\nSimpleTypingTest:\n" 
                + "\t(1) Start a test\n" 
                + "\t(2) View past statistics\n" 
                + "\t(3) View Graph\n" 
                + "\t(4) Save your statistics to a file\n" 
                + "\t(5) Load past statistics from a file\n" 
                + "\t(0) Quit");
    }


    /*
     * REQUIRES: min and max integer values to define a range of valid inputs,
     *           min <= max ,
     *           an error string to display to the user,
     *           when they input anything invalid
     * EFFECTS: wait for a valid user integer input in the given 
     *          range of integers
     */
    public int awaitUserInput(int min, int max, String errorString) {
        int option = 0;
        boolean error = false;
        do {
            if (error) {
                System.out.println(errorString);
            }
            try {
                System.out.print(">> ");
                option = scanner.nextInt();
                if ((option < min) || (option > max)) {
                    error = true;
                    continue;
                }
                error = false;
            } catch (InputMismatchException e) {
                error = true;
                scanner.nextLine();
            }
        } while (error);
        return option;
    }

    /*
     * REQUIRES: a valid main menu option
     *           between 1 and mainMenuOptionCount
     * EFFECTS: executes the respective method based
     *          on the user's choice
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
            case 4: 
                save();
                break;
            case 5: 
                load();
                break;
            case 0: System.exit(0);
        } 
    }



    public void save(){
        try {
            StateWriter sw = new StateWriter(this.stateFilePath);
            sw.write(this.stats); // always returns true as its open
            sw.close();
            System.out.println("\nSaved statistics to: " + this.stateFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("Can't locate the state file, " 
                    + "either it is missing or it is specified incorrectly");
        }
    }
    /* 
     * MODIFIES: this.stats
     * EFFECTS: Fetches all statistics stored at the constant `stateFilePath`
     *          and updates the current sessions stats object
     *          thus updating the current session's state.
     * */
    public void load() {
        try {
            this.stats = new StateReader(this.stateFilePath).parseStatistics();
            System.out.println("\nLoaded statistics from: " + this.stateFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("Can't locate the state file, " 
                    + "either it is missing or it is specified incorrectly");
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: handles calling all the necassary methods
     *          to complete a test from start to finish
     */
    public void startTestProcess() {
        System.out.println();
        int wordCount = chooseWordsCount();
        int totalDuration = chooseTotalDuration();

        System.out.print("\n");

        String randomSentence = generateRandomSentence(wordCount, new File(wordsFilePath));
        System.out.println("Sentence: " + randomSentence);
        countdown(countdownTime);

        System.out.print("\n");

        long start = System.currentTimeMillis();
        String userSentence = startTest(totalDuration, randomSentence);
        long end = System.currentTimeMillis();
        if(userSentence == "") return;
        int userDuration = (int) TimeUnit.MILLISECONDS.toSeconds(end - start);

        System.out.print("\n");

        Statistic statistic = new Statistic(randomSentence, userSentence, totalDuration, userDuration);
        displayStatistic(statistic);
        stats.addStat(statistic);
    }

    /*
     * EFFECTS: prompt the user to choose
     *          how many words the test should be
     *          then returns this value
     */
    public int chooseWordsCount() {
        System.out.println("How many words to test (Maximum of " + wordsMax + "): ");
        return awaitUserInput(1, wordsMax, defaultError);
    }

    /*
     * EFFECTS: prompt the user to choose
     *          how long the test should be
     *          then returns this value
     */
    public int chooseTotalDuration() {
        System.out.println("How long should the test be in seconds (Maximum of " + timeMax + "s): ");
        return awaitUserInput(1, timeMax, defaultError);
    }

    /*
     * REQUIRES: the file which stores random words,
     *           integer n less than or equal to the number of 
     *           words in the file
     * EFFECTS: pick n random words from the 'file'
     *          then concatenate and return
     */
    public String generateRandomSentence(int n, File file) {
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
        for (int i = 0; i < n; i++)  {
            randomSentence += words.get(i) + " ";
        }
        return randomSentence.stripTrailing();
    }

    /*
     * REQUIRES: integer n, 
     * EFFECTS: shows a countdown to 0 starting from n
     *          essentially blocks the main thread 
     *          to for n seconds
     */
    public void countdown(int n) {
        for (int i = n; i > 0; i--) {
            try {
                System.out.print("\r");
                System.out.print("Starting in " + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    /*
     * REQUIRES: the duration of the test after which to stop,
     *           the sentence the user is trying to match
     * EFFECTS:  prints the test ui elements
     *           and starts reading the user input
     */
    public String startTest(int duration, String sentence) {
        System.out.println("Start!");
        System.out.println("-----");
        System.out.println("Sentence: " + sentence);

        String userSentence = readTestInput(duration);
        this.userSentence = "";

        if (userSentence.equals("")) {
            System.out.println("---No sentence recorded---"); 
        } else { 
            System.out.println("-----");
        }

        return userSentence;
    }


    // needed since it is accessed inside lambda
    private String userSentence = "";

    /*
     * REQUIRES: the duration of the test
     * EFFECTS: reads the user input for the 
     *          duration of the test
     *          and returns it
     */
    @SuppressWarnings("methodLength")
    public String readTestInput(int totalDuration) {

        Thread inputThread = new Thread(() -> {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
                // do nothing as we intentionally interrupt
                System.out.println(""); 
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
        System.out.print(s.toString() + "\n");
    }


    /*
     * REQUIRES: this
     * EFFECTS: iterates over Statistics printing each one out
     *          then prompts the user to display a specific one
     */
    public void viewAllStatistics() {
        ArrayList<Statistic> a = stats.getStats();
        int len = a.size();
        if (len == 0) { 
            System.out.print("\t---No statistics to show---\n"); 
        } else {
            for (int i = 0; i < len; i++) {
                System.out.print("(" + (i + 1) + ") ");
                displayStatistic(a.get(i));
                System.out.print("\n");
            }
            System.out.print("Inspect a statistic (0 to go back to main menu): ");
            int id = awaitUserInput(0, len, defaultError);
            if (id != 0) {
                System.out.print("\n");
                displayStatistic(a.get(id - 1));
            }
        }
    }

    /* 
     * REQUIRES: this
     * EFFECTS: iterates over Statistics printing each one out
     */
    public void showGraph() {
        if (stats.getStats().size() == 0) { 
            System.out.print("\t---No statistics to plot---\n"); 
        } else {
            System.out.print(stats.generateGraph() + "\n");
        }
    }

}
