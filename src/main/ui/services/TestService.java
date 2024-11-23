package ui;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Timer;

import model.Statistic;

// Handles the test logic
class TestService {
    private State state;
    private Test testInstance; 
    private TestView testView;

    private Timer testTimer;

    private long start;

    TestService(State state) {
        this.state = state;
    }

    // EFFECTS: Creates a new test instance to track the position in the test
    public Test newTestInstance(int wordLimit, int timeLimit, TestView testView) {
        this.testView = testView;
        String randomSentence = generateRandomSentence(wordLimit, new File(Constants.wordsFilePath));
        testInstance = new Test(wordLimit, timeLimit, randomSentence, 0, "");
        return testInstance;
    }

    // EFFECTS: Waits for the users selected time limit
    //          If it reaches it, then it forces the test to end
    //          Also saves the current timestamp to be calculated later
    public void beginTest() {
        testCountdown(testInstance.getTimeLimit(), () -> forceEnd());
        start = System.currentTimeMillis();
    }

    // EFFECTS: Stops the test timer
    //          Saves the state of the test and adds it to the application state
    public void onEnd(String userSentence) {
      // Recieves signal from ui. Stop timer. Add statistic
        testTimer.stop();
        int userTime = (int)((System.currentTimeMillis() - start) / 1000.0);
        this.testInstance.setUserTime(userTime);
        this.testInstance.setUserSentence(userSentence);
        Statistic statistic = testInstance.toStatistic();
        state.addStat(statistic);
    }


    // EFFECTS: Send a signal to frontend to terminate
    public void forceEnd() {
        testView.onForceEnd();
    }


   /*
    * REQUIRES: the file which stores random words,
    *           integer n less than or equal to the number of 
    *           words in the file
    * EFFECTS: pick n random words from the 'file'
    *          then concatenate and return
    */
    private String generateRandomSentence(int n, File file) {
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

    // EFFECTS: Starts the test counter
    private void testCountdown(int seconds, Runnable callback) {
        testTimer = new Timer(seconds * 1000, e -> {
            testTimer.stop();
            callback.run();
        });
        testTimer.setRepeats(false);
        testTimer.start();
    }

}
