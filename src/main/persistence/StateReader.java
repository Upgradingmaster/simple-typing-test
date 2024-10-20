package persistence;

import java.io.File;
import java.io.IOException;

import model.Statistics;

// A class which reads and parses the state file in json, extracting appliction-level models
public class StateReader {
    private File file;


    /*
     * EFFECTS: Instantiates this class,
     *          with the file at the given path.
     */
    public StateReader(String path) throws IOException{
        // stub
    }

    /*
     * EFFECTS: Instantiates this class, with the given file 
     */
    public StateReader(File file){
        // stub
    }


    /*
     * REQUIRES: a file to read 
     * EFFECTS:  Returns the string representing the contents of a given file
     */
    private String parseContents(File file) {
        return "";
    }


    /*
     * REQUIRES: this
     * EFFECTS:  Returns the "Statistics" collection
     *           represented by all the "Statistic" json objects 
     *           in the file associated with this object,
     *           that is, if they exist.
     *           If none exist returns null
     */
    public Statistics parseStatistics() {
        return new Statistics();
    }

    public File getFile() {
        return file;
    }
}
