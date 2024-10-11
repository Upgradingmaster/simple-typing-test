package model;

// Represents a single Statistic for a test done by the user
public class Statistic {
    // Information about the test
    private final String expectedSentence;
    private final String userSentence;
    private final int totalDuration;
    private final int userDuration;

    // Variables to measure
    private final int wpm;
    private final int accuracy;
    private final int wordsTyped;
    private final int worstLetter;
    private final String diff;

    /*
     * REQUIRES: userSentence, expectedSentence which is not empty,
     * totalDuration > 0, userDuration > 0,
     * and totalDuration >= userDuration
     * MODIFIES: this
     * EFFECTS: Initializes the fields of this object with the parameter values
     * and calculate other variables based on said values
     */
    public Statistic(String expectedSentence, String userSentence, int totalDuration, int userDuration) {
        // stub
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
        // stub
    }

    /*
     * REQUIRES: this
     * MODIFIES: this
     * EFFECTS: finds the character which was incorrectly typed the most number of
     *          times and returns it
     */
    public char findWorstLetter() {
        // stub
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
        // stub
    }

    /*
     * REQUIRES: this
     * MODIFIES: this
     * EFFECTS: calculates the wpm by dividing the wordsTyped and the duration
     *          and returns it
     */
    public char calculateWpm() {
        // stub
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
        // stub
    }

    /*
     * REQUIRES: this
     * EFFECTS: returns a string representation of the Statistic
     */
    @Override
    public String toString() {
        // stub
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

    public int getWorstLetter() {
        return worstLetter;
    }

    public String getDiff() {
        return diff;
    }

}
