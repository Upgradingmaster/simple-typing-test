package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class StatisticsTest {
    private Statistics statistics;

    private Statistic testStat1 = new Statistic("abc", "abd", 30, 15); // wpm = 4
    private Statistic testStat2 = new Statistic("xyz", "xaw", 18, 5); // wpm = 12

    @BeforeEach
    void runBefore() {
        statistics = new Statistics(testStat1, testStat2);
    }

    @Test
    void testConstructor() {
        assertEquals("abc", statistics.getStats().get(0).getExpectedSentence());
        assertEquals("xyz", statistics.getStats().get(1).getExpectedSentence());
    }

    @Test
    void testGenerateGraph() {
        // Is essentially UI method which does not require testing
        // as mentioned in the instructions
    }

}
