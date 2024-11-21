package ui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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

    public void addStatistic(Statistic stat) {
        support.firePropertyChange("stats", null, stat);
        this.stats.addStat(stat);
    }


    public void removeStatistic(Statistic stat) {
        support.firePropertyChange("stats", null, stat);
        this.stats.removeStat(stat);
    }

}
