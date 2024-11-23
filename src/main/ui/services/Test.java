package ui;

import model.Statistic;

class Test {

    private int wordLimit;
    private int timeLimit;
    private String randomSentence;
    private int userTime;
    private String userSentence;




    public Test(int wordLimit, int timeLimit, String randomSentence, int userTime, String userSentence) {
        this.wordLimit = wordLimit;
        this.timeLimit = timeLimit;
        this.randomSentence = randomSentence;
        this.userTime = userTime;
        this.userSentence = userSentence;
    }

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
