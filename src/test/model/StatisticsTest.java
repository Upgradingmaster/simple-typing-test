package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Unit Tests for the Statistics class
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
        assertEquals("abc", statistics.getStatsArrayList().get(0).getExpectedSentence());
        assertEquals("xyz", statistics.getStatsArrayList().get(1).getExpectedSentence());
    }

    //@Test
    //void testGenerateGraph() {
    //    String graph = "   12|                     x\n"
    //                 + "     |                      \n"
    //                 + "     |                      \n"
    //                 + "     |                      \n"
    //                 + "     |                      \n"
    //                 + "     |                      \n"
    //                 + "     |                      \n"
    //                 + "     |                      \n"
    //                 + "     |          x           \n"
    //                 + "    3|----------|----------|";
    //    assertEquals(graph, statistics.generateGraph());
    //}

}
