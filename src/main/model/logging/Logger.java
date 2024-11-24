package model;

import java.util.Iterator;

// Handles user event logging
public class Logger {
	
	// EFFECTS: Writes the desc as a log
	public void log(String desc) {
		EventLog.getInstance().logEvent(new Event(desc));
	}

	// EFFECTS: Prints out all the events that have been logged to the terminal
	public void printToTerminal() {
		for (Event e : EventLog.getInstance()) {
			System.out.println(e);
			System.out.println();
		}
	}
}
