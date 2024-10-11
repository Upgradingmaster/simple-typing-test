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

        assertEquals(stat.calculateWpm(), stat.getWpm());
        assertEquals(stat.calculateAccuracy(), stat.getAccuracy());
        assertEquals(stat.calculateWordsTyped(), stat.getWordsTyped());
        assertEquals(stat.findWorstLetter(), stat.getWorstLetter());
        assertEquals(stat.generateDiff(), stat.getDiff());
    }

    @Test
    void testGenerateDiff() {
        assertEquals(expectedDiff, stat.generateDiff());
    }

    @Test
    void testFindWorstLetter() {
        assertEquals("c", stat.findWorstLetter());
    }

    @Test
    void testCalculateWordsTyped() {
        assertEquals(1, stat.calculateWordsTyped());
    }

    @Test
    void testCalculateWpm() {
        assertEquals((int)(1 / (15 / 60)), stat.calculateWpm());
    }

    @Test
    void testCalculateAccuracy() {
        assertEquals((int)((2 / 3) * 100), stat.calculateAccuracy());
    }

    @Test
    @SuppressWarnings("LineLength")
    void testToString() {
        assertEquals(String.format(
                "Statistic:\n \tTime Taken:%d\n \tWPM:%d\n \tAccuracy:%d%%\n \tWords Typed:%d\n \tWorst Letter: %d\n\n", 
                stat.getUserDuration(), stat.getWpm(), stat.getAccuracy(), stat.getWordsTyped(), stat.getWorstLetter()), 
                stat.toString());
    }
}
