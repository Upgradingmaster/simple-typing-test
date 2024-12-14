package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

// Represents the state of the application which is observable by others
public class State {
    private Statistics stats;

    private PropertyChangeSupport support;

    // EFFECTS: Initializes the Statistics with nothing
    //          Adds a PropertyChangeSupport to make it a subject
    public State() {
        stats = new Statistics();
        support = new PropertyChangeSupport(this);
    }


    // EFFECTS: adds an observer
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    // EFFECTS: removes an observer
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    // EFFECTS: adds a statistic in the state, firing an increase event
    public void addStat(Statistic stat) {
        support.firePropertyChange("statsIncremental+", this.stats, stat);
        this.stats.addStat(stat);
        EventLog.getInstance().logEvent(new Event(">> A statistic was added"));
    }

    // EFFECTS: sets the statistic in the state, firing a definite change event
    public void setStats(Statistics stats) {
        support.firePropertyChange("statsDefinite", this.stats, stats);
        this.stats = stats;
        EventLog.getInstance().logEvent(new Event(">> Statistics have been set"));
    }


    public Statistics getStats() {
        return stats;
    }
}
