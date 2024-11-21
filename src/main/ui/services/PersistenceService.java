package ui;

import java.io.FileNotFoundException;

import persistence.StateWriter;
import persistence.StateReader;

class PersistenceService {
   private State state;

   PersistenceService(State state) {
      this.state = state;
   }

   public void save(){
      // Save current State  
      try {
         StateWriter sw = new StateWriter(Constants.stateFilePath);
         sw.write(state.getStats()); // always returns true as its open
         sw.close();
         System.out.println("\nSaved statistics to: " + Constants.stateFilePath);
      } catch (FileNotFoundException e) {
         System.out.println("Can't locate the state file, " 
               + "either it is missing or it is specified incorrectly");
      }
   }



   public void load(){
      try {
         state.setStats(new StateReader(Constants.stateFilePath).parseStatistics());
         System.out.println("\nLoaded statistics from: " + Constants.stateFilePath);
      } catch (FileNotFoundException e) {
         System.out.println("Can't locate the state file, " 
               + "either it is missing or it is specified incorrectly");
      }
   }
}

