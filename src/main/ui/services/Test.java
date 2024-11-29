package ui;

import model.Statistic;

// Represents a test instance
class Test {
    private int wordLimit;
    private int timeLimit;
    private String randomSentence;
    private int userTime;
    private String userSentence;


    // EFFECTS: Initializes a test instance with the given parameters
    public Test(int wordLimit, int timeLimit, String randomSentence, int userTime, String userSentence) {
        this.wordLimit = wordLimit;
        this.timeLimit = timeLimit;
        this.randomSentence = randomSentence;
        this.userTime = userTime;
        this.userSentence = userSentence;
    }

    // EFFECTS: Converts the test information to a statistic
    public Statistic toStatistic() {
        return new Statistic(randomSentence, userSentence, timeLimit, userTime);
    }

    public int getUserTime() {
        return userTime;
    }

    public void setUserTime(int userTime) {
        this.userTime = userTime;
    }

    public String getUserSentence() {
        return userSentence;
    }

    public void setUserSentence(String userSentence) {
        this.userSentence = userSentence;
    }

    public int getWordLimit() {
        return wordLimit;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public String getRandomSentence() {
        return randomSentence;
    }
}
