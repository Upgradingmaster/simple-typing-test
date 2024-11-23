package ui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import model.Statistic;
import model.Statistics;

// Represents the state of the application which is observable by others
class State {
    private Statistics stats;

    private PropertyChangeSupport support;

    State() {
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
    }

    // EFFECTS: sets the statistic in the state, firing a definite change event
    public void setStats(Statistics stats) {
        support.firePropertyChange("statsDefinite", this.stats, stats);
        this.stats = stats;
    }


    public Statistics getStats() {
        return stats;
    }
}
