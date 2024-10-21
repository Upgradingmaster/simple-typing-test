package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import model.Statistics;
import model.Statistic;


// Responsible for writing all Statistics to a given file in json
public class StateWriter {

    private PrintWriter pw;
    

    /*
     * MODIFIES: this
     * EFFECTS: if the given File exists
     *          and is not a directory, it instatiates 
     *          this class with a PrintWriter attached to the file
     *          Otherwise throws a FileNotFoundException
     * */
    public StateWriter(File file) throws FileNotFoundException {
        // stub
    }

    /*
     * MODIFIES: this
     * EFFECTS: if the File at the given path exists
     *          and is not a directory, it instatiates 
     *          this class with a PrintWriter attached to the file
     *          Otherwise throws a FileNotFoundException
     * */
    public StateWriter(String path) throws FileNotFoundException {
        // stub
    }


    /*
     * REQUIRES: a File which exists and isn't a directory
     * MODIFIES: this.pw
     * EFFECTS: closes the printWriter
     * */
    public void open(File file) {
        // stub
    };

    /*
     * MODIFIES: this.pw
     * EFFECTS: closes the printWriter
     * */
    public void close() {
        // stub
    };

    /*
     * MODIFIES: the file this.pw is writing to
     * EFFECTS: writes all `Statistic` objects to the json state file 
     * */
    public void write(Statistics statistics) {
        //stub
    }
}
