package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StatisticTest {
    private Statistic stat; 
    private final String expectedSentence = "abc";
    private final String userSentence = "abd";
    private final int totalDuration = 30;
    private final int userDuration = 15;

    private final String red = "\u001B[31m";
    private final String green = "\u001B[32m";
    private final String original = "\u001B[0m";

    private final String expectedDiff = expectedSentence + "\n" + green + "ab" + red + "d";

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

      // all other cases are covered by the other tests
    }

    @Test
    void testGenerateDiff() {
      //stat.generateDiff(); not needed since constructor in runBefore() calls it
        assertEquals(expectedDiff, stat.getDiff());
    }

    @Test
    void testFindWorstLetter() {
      //stat.findWorstLetter(); not needed since constructor in runBefore() calls it
        assertEquals("c", stat.getWorstLetter());
    }

    @Test
    void testCalculateWordsTyped() {
      //stat.calculateWordsTyped(); not needed since constructor in runBefore() calls it
        assertEquals(1, stat.getWordsTyped());
    }

    @Test
    void testCalculateWpm() {
      //stat.calculateWpm(); not needed since constructor in runBefore() calls it
        assertEquals((int)(1 / (15 / 60)), stat.getWpm());
    }

    @Test
    void testCalculateAccuracy() {
      //stat.calculateAccuracy(); not needed since constructor in runBefore() calls it
        assertEquals((int)((2 / 3) * 100), stat.getAccuracy());
    }
}
