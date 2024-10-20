package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.IOException;

import model.Statistic;
import org.junit.Test;

class StateReaderTest {

    StateReader stateReader;

    @Test
    void testConstructorValidPath(){
        try {
            stateReader = new StateReader("./data/testNotStatsState.json");
            assertEquals("testNotStatsState.json", stateReader.getFile().getName());
        } catch (IOException e){
            fail("Fail to convert string path to file");
        }
    }

    @Test
    void testConstructorInvalidPath(){
        try {
            stateReader = new StateReader("./data/abc.json");
            stateReader = new StateReader("12345");
            fail("Accepeted an invalid path");
        } catch (IOException e){}
    }


    @Test
    void testParseStatsEmpty(){
        try{
            stateReader = new StateReader("./data/testNoStatsState.json");
        }  catch (IOException e)  { 
            fail("Constructor failed");
        }
            assertNull(stateReader.parseStatistics());
    }

    @Test
    void testParseStats(){
        try{
            stateReader = new StateReader("./data/testState.json");
        }  catch (IOException e)  { 
            fail("Constructor failed");
        }

        try{
            Statistic testStateStatistic1 = new Statistic("abc", "def", 10, 2);
            Statistic testStateStatistic2 = new Statistic("ghi", "jkl", 5, 1);
            assertEquals(testStateStatistic1, stateReader.parseStatistics().getStats().get(0));
            assertEquals(testStateStatistic2, stateReader.parseStatistics().getStats().get(1));
        } catch (IndexOutOfBoundsException e){
            fail("Didn't extract all stats");
        }
    }
}


