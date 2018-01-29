package tests;

import gui.Frame;
import main.Main;

public class Test {

    private String question;
    private String[] answers=new String[Frame.MAX_ANSWERS];
    private boolean result=false;
    private int numberOfAnswers,rightAnswer;

    public Test(){

    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer(int number) {
        return answers[number];
    }

    public boolean isRight() {
        return result;
    }

    public int getNumberOfAnswers() {
        return numberOfAnswers;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer,int i) {
        this.answers[i] = answer;
    }

    public void setNumberOfAnswers(int numberOfAnswers) {
        this.numberOfAnswers = numberOfAnswers;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}
