package persistence;

import java.io.File;

import model.Statistics;

// A class which reads and parses a json file, extracting appliction-level models
public class JsonReader {
    private String contents;

    /*
     * REQUIRES: A valid path, relative or absolute, to a file
     * EFFECTS: Instantiates this class,
     *          with the contents of the file at the given path.
     */
    public JsonReader(String path){
        // stub
    }

    /*
     * REQUIRES: A file object
     * EFFECTS: Instantiates this class, with the contents of the given file 
     */
    public JsonReader(File file){
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
     *           in the file this JSONReader is reading,
     *           that is if they exist.
     *           If they don't exist returns null
     */
    public Statistics parseStatistics() {
        return new Statistics();
    }

}
