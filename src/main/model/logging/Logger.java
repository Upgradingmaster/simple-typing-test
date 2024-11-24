package model;

// Handles user event logging
public class Logger {
	
	// EFFECTS: Writes the desc as a log
	public void log(String desc) {
		EventLog.getInstance().logEvent(new Event(desc));
	}
}
