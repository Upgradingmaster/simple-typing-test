package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import persistence.IJsonObject;

// Represents a single Statistic for a test done by the user
public class Statistic implements IJsonObject {
    // Information about the test
    private final String expectedSentence;
    private final String userSentence;
    private final int totalDuration; // Redundant for now
    private final int userDuration;

    // Variables to measure
    private final int wpm;
    private final int accuracy;
    private final int wordsTyped;
    private final char worstLetter;
    private final String diff;




    // Constants
    //  Colors
    private final String red = "\u001B[31m";
    private final String green = "\u001B[32m";
    private final String original = "\u001B[0m";

    /*
     * REQUIRES: userSentence, expectedSentence which is not empty,
     * totalDuration > 0, userDuration > 0,
     * and totalDuration >= userDuration
     * MODIFIES: this
     * EFFECTS: Initializes the fields of this object with the parameter values
     *          and calculate other variables based on said values
     */
    public Statistic(String expectedSentence, String userSentence, int totalDuration, int userDuration) {
        this.expectedSentence = expectedSentence;
        this.userSentence = userSentence;
        this.totalDuration = totalDuration;
        this.userDuration = userDuration;
        this.wpm = calculateWpm();
        this.accuracy = calculateAccuracy();
        this.wordsTyped = calculateWordsTyped();
        this.worstLetter = findWorstLetter();
        this.diff = generateDiff();
    }


    /*
     * REQUIRES: this
     * MODIFIES: this
     * EFFECTS: Generates a "diff" based on the expectedSentence and the
     *          userSentence of this instance and returns it
     * 
     *          A diff is a string with two sentences, the expectedSentence and the
     *          userSentence,
     *          however the userSentence is color coded to highlight errors in red and
     *          correct letters in green
     */
    public String generateDiff() {
        StringBuilder diff = new StringBuilder(
                10 + expectedSentence.length() + 1 
                + 8 + (5 * userSentence.length()) + 1);
        diff.append("\tExpected Sentence: " + expectedSentence + "\n\tYour Sentence:     ");
        boolean[] errors = getErrors(expectedSentence, userSentence);
        for (int i = 0; i < errors.length; i++) {
            if (errors[i]) { 
                diff.append(red); 
            } else { 
                diff.append(green); 
            }
            diff.append(userSentence.charAt(i));
        }

        diff.append(original);
        return diff.toString();
    }

    /*
     * REQUIRES: this
     * MODIFIES: this
     * EFFECTS: finds the character which was incorrectly typed the most number of
     *          times using a hashmap and iterating through it,
     *          then returns it
     */
    public char findWorstLetter() {
        Map<Character, Integer> counts = getCounts();
        if (counts.isEmpty()) {
            return 0; 
        }
        // TODO: check for if all error counts are the same -> return  some error
        char max = Collections.max(counts.entrySet(), Map.Entry.comparingByValue()).getKey();
        if (max == ' ') {
            max = '_';
        }
        return max;
    }

    private Map<Character, Integer> getCounts() {
        Map<Character, Integer> counts = new HashMap<>();
        char userChar;
        char expectedChar;

        // Populate map with the incorrect characters and their respective frequency
        int len = Math.min(userSentence.length(), expectedSentence.length());
        for (int i = 0; i < len; i++) {
            userChar = userSentence.charAt(i);
            expectedChar = expectedSentence.charAt(i);
            if (userChar != expectedChar) {
                counts.put(expectedChar, counts.getOrDefault(expectedChar, 0) + 1);
            }
        }
        return counts;
    }

    /*
     * REQUIRES: this
     * MODIFIES: this
     * EFFECTS: calculates the words typed by the user before the end of the
     *          totalDuration and returns it.
     *          A word is rounded up so if a word is incomplete due to the time, it is still
     *          counted as a word
     */
    public int calculateWordsTyped() {
        return userSentence.split(" ").length;

    }

    /*
     * REQUIRES: this
     * MODIFIES: this
     * EFFECTS: calculates the wpm by dividing the wordsTyped and the duration
     *          and returns it
     */
    public int calculateWpm() {
        return (int)(((double)(Math.max(calculateWordsTyped(), 1)) 
                    / (double)(userDuration / 60.0)));
    }

    /*
     * REQUIRES: this
     * MODIFIES: this
     * EFFECTS: calculates accuracy by comparing each character of userSentence
     *          with the respective expectedSentence, summing the correct characters,
     *          and then dividing by the total number of characters.
     *          Then returns it
     */
    public int calculateAccuracy() {
        boolean[] errors = getErrors(expectedSentence, userSentence);
        int correctCount = 0;
        for (boolean b : errors) {
            correctCount += (b) ? 0 : 1;
        } 
        return (int)((double)correctCount / (double)(errors.length) * 100.0);
    }



    // Helper Functions

    /*
     * REQUIRES: two strings of any length
     * EFFECTS: compares each character of two strings and
     *          appends a true or false to an Array based on if they
     *          are different or not respectively
     *          It does this until it reaches the end of any of the strings
     *          Then returns the array
     */
    public boolean[] getErrors(String s1, String s2) {
        int length = Math.min(s1.length(), s2.length());
        boolean[] errArr = new boolean[length];
        for (int i = 0; i < length; i++) {
            errArr[i] = !(s1.charAt(i) == s2.charAt(i));
        }
        return errArr;
    }


    /*
     * REQUIRES: this
     * EFFECTS: checks if this, and another statistic are equivalent,
     *          based on a criteria.
     *
     *          returns false if it fails the criteria, or
     *          if it is compared to "null". or 
     *          if it is compared to some non-Statistic object
     *
     *          otherwise returns true
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { 
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Statistic o = (Statistic) obj;
        return (this.expectedSentence.equals(o.expectedSentence)) 
            && (this.userSentence.equals(o.userSentence)) 
            && (this.totalDuration == o.totalDuration) 
            && (this.userDuration == o.userDuration);
    }

    /*
     * REQUIRES: this
     * EFFECTS: returns a string representation of the Statistic
     */
    @Override
    public String toString() {
        String w = (worstLetter == 0) ? "No mistakes" :  worstLetter + "";
        return String.format(
                "Statistic:\n"
                + "\tTime Taken: %d\n"
                + "\tWPM: %d\n"
                + "\tAccuracy: %d%%\n"
                + "\tWords Typed: %d\n"
                + "\tWorst Letter: %s\n\n"
                + "%s",
                userDuration,
                wpm,
                accuracy, 
                wordsTyped,
                w,
                diff);   
    }

    @Override
    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        json.put("expectedSentence", this.expectedSentence);
        json.put("userSentence", this.userSentence);
        json.put("totalDuration", this.totalDuration);
        json.put("userDuration", this.userDuration);
        return json;
    }

    // GETTERS
    public String getExpectedSentence() {
        return expectedSentence;
    }

    public String getUserSentence() {
        return userSentence;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public int getUserDuration() {
        return userDuration;
    }

    public int getWpm() {
        return wpm;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getWordsTyped() {
        return wordsTyped;
    }

    public char getWorstLetter() {
        return worstLetter;
    }

    public String getDiff() {
        return diff;
    }



}
