package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test; 

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

import model.Statistic;
import model.Statistics;

class StateWriterTest {
    @Test
    void testConstructorsInvalid(){
        StateWriter stateWriter;
        try{
            stateWriter = new StateWriter("this/is/invalid");
            fail("Accepted invalid path");
        } catch (FileNotFoundException e) { }

        try{
            stateWriter = new StateWriter("data/");
            fail("Accepted directory");
        } catch (FileNotFoundException e) { }

        try{
            stateWriter = new StateWriter(new File("this/is/invalid"));
            fail("Accepted invalid path");
        } catch (FileNotFoundException e) { }

        try{
            stateWriter = new StateWriter(new File("data/"));
            fail("Accepted directory");
        } catch (FileNotFoundException e) { }
    }
    
    @Test
    void testConstructorsValid(){
        StateWriter stateWriter;

        try {
            stateWriter = new StateWriter("data/state.json");
        } catch (FileNotFoundException e) {
            fail("Didn't accept valid path");
        }

        try {
            stateWriter = new StateWriter(new File("data/state.json"));
        } catch (FileNotFoundException e) {
            fail("Didn't accept valid path");
        }

    }

    @Test
    void testWriteNoStats(){
        StateWriter sw;
        StateReader sr;
        try {
            sw = new StateWriter("data/state.json");
        } catch (FileNotFoundException e) {
            fail("StateWriter: Constructor didn't accept valid path");
            return;
        }
        try {
            sr = new StateReader("data/state.json");
        } catch (FileNotFoundException e) {
            fail("StateReader: Constructor didn't accept valid path");
            return;
        }

        sw.write(new Statistics());
        sw.close();

        Statistics s = sr.parseStatistics();

        assertTrue(s.getStatsArrayList().isEmpty());
    }

    @Test
    void testWriteMultipleStats(){
        StateWriter sw;
        StateReader sr;
        try {
            sw = new StateWriter("data/state.json");
        } catch (FileNotFoundException e) {
            fail("StateWriter: Constructor didn't accept valid path");
            return;
        }
        try {
            sr = new StateReader("data/state.json");
        } catch (FileNotFoundException e) {
            fail("StateReader: Constructor didn't accept valid path");
            return;
        }

        Statistic s1 = new Statistic("abc", "def", 20, 10);
        Statistic s2 = new Statistic("ghi", "jkl", 10, 5);

        sw.write(new Statistics(s1, s2));
        sw.close();
        Statistics s = sr.parseStatistics();
        
        assertEquals(s1, s.getStatsArrayList().get(0));
        assertEquals(s2, s.getStatsArrayList().get(1));
        
    }

}
