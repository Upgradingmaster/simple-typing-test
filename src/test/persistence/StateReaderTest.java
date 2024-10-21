package persistence;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import model.Statistic;
import model.Statistics;

import org.junit.jupiter.api.Test; 

class StateReaderTest {

    StateReader stateReader;

    @Test
    void testConstructorInvalidPath(){
        try {
            stateReader = new StateReader("./data/abc.json");
            fail("Accepeted an invalid path");
        } catch (FileNotFoundException e){}

        try {
            stateReader = new StateReader("12345");
            fail("Accepeted an invalid path");
        } catch (FileNotFoundException e){}

        try {
            stateReader = new StateReader(new File("12345"));
            fail("Accepeted an invalid path");
        } catch (FileNotFoundException e){}

        try {
            stateReader = new StateReader(new File("./data/abc.json"));
            fail("Accepeted an invalid path");
        } catch (FileNotFoundException e){}
    }

    @Test
    void testConstructorValidPath(){
        try {
            stateReader = new StateReader("./data/testNoStatsState.json");
            assertEquals("testNoStatsState.json", stateReader.getFile().getName());
        } catch (FileNotFoundException e){
            fail("String constructor does not accept valid path");
        }

        try {
            stateReader = new StateReader(new File("./data/testNoStatsState.json"));
            assertEquals("testNoStatsState.json", stateReader.getFile().getName());
        } catch (FileNotFoundException e){
            fail("File constructor does not accept valid path");
        }
    }

    
    @Test
    void testParseContents(){
        try{
            stateReader = new StateReader("./data/testNoStatsState.json");
        }  catch (IOException e)  { 
            fail("Constructor failed");
        }

        try{
            assertEquals("{\n    \"Statistics\": []\n}", stateReader.parseContents());
        } catch (IOException e) {
            fail(e.toString());
        }
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
    void testParseStatsMultiple(){
        try{
            stateReader = new StateReader("./data/testMultipleStatsState.json");
        }  catch (IOException e)  { 
            fail("Constructor failed");
        }

            Statistic testStateStatistic1 = new Statistic("abc", "def", 10, 2);
            Statistic testStateStatistic2 = new Statistic("ghi", "jkl", 5, 1);

            Statistics s;
            try{
              s  =  stateReader.parseStatistics();
            } catch (NullPointerException e) {
                fail("Returned null");
                return; // To signal to compiler that the function will not proceed
            }

            try{
                assertEquals(testStateStatistic1, s.getStats().get(0));
                assertEquals(testStateStatistic2, s.getStats().get(1));
            } catch (IndexOutOfBoundsException e){
                fail("Didn't extract all stats");
            }

    }
}


