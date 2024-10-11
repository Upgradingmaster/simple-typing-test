package ui;

import java.io.File;
import java.util.Scanner;

import model.*;

// An application to test typing with random sentences and review statistics of tests
public class SimpleTypingTest {
    private Statistics stats = new Statistics();


    public final String wordsFilePath;
    private final Scanner scanner;

    /*
     * MODIFIES: this
     * EFFECTS: initializes the variables
     *          starts the main appplication processes/methods
     */
    public SimpleTypingTest() {
        //stub
    }

    /*
     * EFFECTS: initializes the variables 
     *          for the current instance of the application
     */

    public void init() {
        //stub
    }

    /*
     * EFFECTS: displays the main menu 
     */
    public void displayMainMenu() {
        //stub
    }


    /*
     * REQUIRES: a valid option
     * EFFECTS: executes the respective method based
     *          on the user's choice
     *          
     */
    public void handleMainMenuCases(int choice) {
        //stub
    }

    /*
     * REQUIRES: min and max values to define a range
     *           min <= max
     * EFFECTS: wait for a valid user input in the given 
     *          range of integers
     */
    public int awaitUserInput(int min, int max) {
        return 0;
    }


    /*
     * EFFECTS: handles calling all the necassary methods
     *           to complete a test from start to finish
     */
    public void startTestProcess() {
        //stub
    }


    /*
     * EFFECTS: prompt the user to choose
     *          how many words the test should be
     *          then returns this value
     */
    public int chooseWordsCount() {
        return 0;
    }

    /*
     * EFFECTS: prompt the user to choose
     *          how long the test should be
     *          then returns this value
     */
    public int chooseTotalDuration() {
        return 0;
    }

    /*
     * EFFECTS: pick 'wordCount' random words from the 'file'
     *          then concatenate and return
     */
    public int generateRandomSentence(int wordCount, File file) {
        return 0;
    }


    /*
     * EFFECTS: reads the user input for the test
     *          and returns it
     */
    public String readUserInput() {
        return "";
    }


    /*
     * REQUIRES: a statistic to print
     * EFFECTS:  displays a given statistic
     */
    public void displayStatistic(Statistic s) {
    }



    /*
     * EFFECTS: iterates over Statistics printing each one out
     */
    public void viewAllStatistics() {

    }


    /*
     * REQUIRES: an int i which a valid index of Statistics
     * EFFECTS: display the statistic at the index i
     *          in Statistics
     */
    public void drilldownStatistic(int i) {
    }



}
