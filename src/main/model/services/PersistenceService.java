package model;

import java.io.FileNotFoundException;

import persistence.StateWriter;
import persistence.StateReader;

// Handles for persistence logic
public class PersistenceService {
    private State state;

    // EFFECTS: Initializes the PersistenceService with the state
    PersistenceService(State state) {
        this.state = state;
    }

   // EFFECTS: Save the current state
    public void save() {
        try {
            StateWriter sw = new StateWriter(Constants.stateFilePath);
            sw.write(state.getStats()); // always returns true as its open
            sw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can't locate the state file, " 
                             + "either it is missing or it is specified incorrectly");
        }
    }

   // EFFECTS: Load the state from file defined in Constants
    public void load() {
        try {
            state.setStats((new StateReader(Constants.stateFilePath)).parseStatistics());
        } catch (FileNotFoundException e) {
            System.out.println("Can't locate the state file, " 
                            + "either it is missing or it is specified incorrectly");
        }
    }
}

