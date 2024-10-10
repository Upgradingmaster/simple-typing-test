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
     *          totalDuration > 0, userDuration > 0. 
     * MODIFIES: this
     * EFFECTS: Initializes the fields of this object with the parameter values
     *          and calculate other variables based on said values
     */
    public Statistic(String expectedSentence, String userSentence, int totalDuration, int userDuration){
        //stub
    }


    /* REQUIRES: this
     * MODIFIES: this  
     * EFFECTS: Generates a "diff" based on the expectedSentence and the userSentence of this instance and assigns it to the diff field
     *         
     *          A diff is a string with two sentences, the expectedSentence and the userSentence, 
     *          however the userSentence is color coded to highlight errors in red and correct letters in green
    */
    private void generateDiff() {
        //stub
    }

    /* REQUIRES: this
     * MODIFIES: this  
     * EFFECTS: finds the character which was incorrectly typed the most number of times 
    */
    private char findWorstLetter() {
        //stub
    }

    /* REQUIRES: this
     * MODIFIES: this  
     * EFFECTS: calculates the words typed by the user before the end of the totalDuration
     *          a word is rounded up so if a word is incomplete due to the time, it is still counted as a word
    */
    private int calculateWordsTyped() {
        //stub
    }

    /* REQUIRES: this
     * MODIFIES: this  
     * EFFECTS:  calculates the wpm by dividing the wordsTyped and the duration 
    */
    private char calculateWpm() {
        //stub
    }

    /* REQUIRES: this
     * MODIFIES: this  
     * EFFECTS:  calculates accuracy by comparing each character of userSentence
     *           with the respective expectedSentence, summing the correct characters,
     *           and then dividing by the total number of characters
    */
    private int calculateAccuracy() {
        //stub
    }



    /*
     * REQUIRES: this    
     * EFFECTS:  returns a string representation of the Statistic 
     */
   @Override
   public String toString(){
//stub
   }



}
