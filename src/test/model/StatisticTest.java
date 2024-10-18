package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StatisticTest {

    // Test object
    private Statistic stat; 

    // Constants
    private final String red = "\u001B[31m";
    private final String green = "\u001B[32m";
    private final String original = "\u001B[0m";


    // Dummy values
    private final String expectedSentence = "abc";
    private final String userSentence = "abd";
    private final int totalDuration = 30;
    private final int userDuration = 15;
    private final int wpm = (int)((double)1 / (double)(15 / 60.0));
    private final int accuracy = (int)(((double) 2 / (double) 3) * 100.0);
    private final int wordsTyped = 1;
    private final char worstLetter = 'c';
    private final String diff = "\tExpected Sentence: " + expectedSentence + "\n\t"
            + "Your Sentence:     "
            + green + "a" + green + "b" + red + "d" + original;


    @BeforeEach
    void runBefore() {
        stat = new Statistic(expectedSentence,userSentence,totalDuration,userDuration);
    }

    @Test
    void testConstructor() {
        assertEquals(expectedSentence, stat.getExpectedSentence());
        assertEquals(userSentence, stat.getUserSentence());
        assertEquals(totalDuration, stat.getTotalDuration());
        assertEquals(userDuration, stat.getUserDuration());

        assertEquals(stat.calculateWpm(), stat.getWpm());
        assertEquals(stat.calculateAccuracy(), stat.getAccuracy());
        assertEquals(stat.calculateWordsTyped(), stat.getWordsTyped());
        assertEquals(stat.findWorstLetter(), stat.getWorstLetter());
        assertEquals(stat.generateDiff(), stat.getDiff());
    }

    @Test
    void testGenerateDiff() {
        assertEquals(diff, stat.generateDiff());
    }

    @Test
    void testFindWorstLetter() {
        assertEquals(worstLetter, stat.findWorstLetter());
    }

    @Test
    void testCalculateWordsTyped() {
        assertEquals(wordsTyped, stat.calculateWordsTyped());
    }

    @Test
    void testCalculateWpm() {
        assertEquals(wpm, stat.calculateWpm());
    }

    @Test
    void testCalculateAccuracy() {
        assertEquals(accuracy, stat.calculateAccuracy());
    }

    @Test
    @SuppressWarnings("lineLength")
    void testToString() {
        assertEquals(String.format("Statistic:\n\tTime Taken: %d\n\tWPM: %d\n\tAccuracy: %d%%\n\tWords Typed: %d\n\tWorst Letter: %s\n\n%s", 
                userDuration, wpm, accuracy, wordsTyped, worstLetter, diff), 
                stat.toString());
    }

    void testGetErrors() {
        assertEquals(new boolean[]{false, false, true}, stat.getErrors(expectedSentence, userSentence));
    }
}
