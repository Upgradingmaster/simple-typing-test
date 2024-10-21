package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Statistics;
import model.Statistic;

// A class which reads and parses the state file in json, extracting appliction-level models
public class StateReader {
    private File file;


    /*
     * REQUIRES: must have the key "Statistics".
     * MODIFIES: this
     * EFFECTS: Instantiates this class, with the file at the given path.
     *          Only if the file exists and does not point to a directory
     *          otherwise throws a FileNotFoundException
     */
    public StateReader(String path) throws FileNotFoundException {
        File file = new File(path);
        if (file.isFile() && file.exists()) {
            this.file = file;
        } else {
            throw new FileNotFoundException("File is invalid");
        }
    }

    /*
     * REQUIRES: the file must have the key "Statistics".
     * MODIFIES: this
     * EFFECTS: Instantiates this class, with the given file 
     *          Only if the file exists and does not point to a directory
     *          otherwise throws a FileNotFoundException
     */
    public StateReader(File file) throws FileNotFoundException {
        if (file.isFile() && file.exists()) {
            this.file = file;
        } else {
            throw new FileNotFoundException("File is invalid");
        }
    }


    /*
     * REQUIRES: this 
     * EFFECTS: Returns the string representing the contents of the current file
     *          throws IOException if if Files.readString() fails to parse the file
     *
     */
    public String parseContents() throws IOException {
        return Files.readString(this.file.toPath()).trim();
    }


    /*
     * REQUIRES: this
     * EFFECTS:  Returns the "Statistics" collection
     *           represented by all the "Statistic" json objects 
     *           in the file associated with this object,
     *           that is, if they exist.
     *           If none exist, or if there is an error parsing the file
     *           returns null
     */
    public Statistics parseStatistics() {
        String contents;
        try {
            contents = parseContents();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        JSONArray statisticsJsonArray;
        try {
            statisticsJsonArray = new JSONObject(contents).getJSONArray("Statistics");
        } catch (JSONException e) {
            return null;
        }
        
        Statistics statistics = new Statistics();
        for (Object o : statisticsJsonArray) {
            statistics.addStat(jsonToStatistic((JSONObject) o));
        }
        if (statistics.getStats().size() == 0) {
            return null;
        }
        return statistics;
    }


    
    /*
     * REQUIRES: A JsonObject formatted correctly to represent a Statistic
     * EFFECTS: returns a statistic populated with the data in the JsonObject
     */
    private Statistic jsonToStatistic(JSONObject jo) {
        return new Statistic(jo.getString("expectedSentence"),
                jo.getString("userSentence"),
                jo.getInt("totalDuration"),
                jo.getInt("userDuration"));
    }




    public File getFile() {
        return file;
    }
}
