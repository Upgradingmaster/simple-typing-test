package ui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import model.Statistic;
import model.Statistics;

class State {
    private Statistics stats;

    private PropertyChangeSupport support;


    State(){
        stats = new Statistics();
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void addStat(Statistic stat) {
        support.firePropertyChange("statsIncremental+", null, stat);
        this.stats.addStat(stat);
    }


    //public void removeStat(int index) {
    //    support.firePropertyChange("statsIncremental-", null, index);
    //    this.stats.removeStat(index);
    //}

    public Statistics getStats() {
        return stats;
    }

    public void setStats(Statistics stats) {
        support.firePropertyChange("statsDefinite", this.stats, stats);
        this.stats = stats;
    }
}
