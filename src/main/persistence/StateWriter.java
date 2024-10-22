package persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;

import org.json.JSONArray;
import org.json.JSONObject;

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
        if (file.isFile() && file.exists()) {
            open(file);
        } else {
            throw new FileNotFoundException("File is invalid");
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: if the File at the given path exists
     *          and is not a directory, it instatiates 
     *          this class with a PrintWriter attached to the file
     *          Otherwise throws a FileNotFoundException
     * */
    public StateWriter(String path) throws FileNotFoundException {
        File file = new File(path);
        if (file.isFile() && file.exists()) {
            open(file);
        } else {
            throw new FileNotFoundException("File is invalid");
        }
    }


    /*
     * REQUIRES: a File which exists and isn't a directory
     * MODIFIES: this.pw
     * EFFECTS: closes the printWriter
     * */
    public boolean open(File file) {
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            return true;
        } catch (IOException e) {
            // Unreachable with constructor usage
            // for abnormal usage i.e. reopening the StateWriter, expects a valid file
            e.printStackTrace();
            return false;
        }
    };

    /*
     * MODIFIES: this.pw
     * EFFECTS: closes the printWriter
     * */
    public void close() {
        this.pw.close();
        this.pw = null;
    };

    /*
     * MODIFIES: the file this.pw is writing to
     * EFFECTS: If this.pw is not closed,
     *          writes all `Statistic` objects to the json state file 
     *          and returns true
     *
     *          Otherwise, returns false
     * */
    public boolean write(Statistics statistics) {
        if (this.pw == null){
            return false;
        }
        JSONArray jsonArrayOfStatistics = statistics.toJsonArray();
        JSONObject stateObj = new JSONObject();
        stateObj.put("Statistics", jsonArrayOfStatistics);
        writeJsonObject(stateObj);
        return true;
    }


    /*
     * MODIFIES: the file this.pw is associated with
     * EFFECTS: Write the jsonObject as a string to the file
     * */
    public void writeJsonObject(JSONObject jsonObject){
        this.pw.print(jsonObject.toString());
    }
}
