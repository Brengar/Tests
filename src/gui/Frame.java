package gui;

import main.Main;
import tests.Test;
import tests.TestSequence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Frame extends JFrame{

    private TextArea question=new TextArea();
    private JButton next=new JButton();
    public static final int MAX_ANSWERS=5;
    private JRadioButton[] answers=new JRadioButton[MAX_ANSWERS];
    private ButtonGroup answersGroup=new ButtonGroup();


    public Frame(){
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS)
        );
        super.setResizable(false);
        super.setSize(800,600);
        super.setTitle("Tests");
        super.setLocationRelativeTo(null);
        question.setFocusable(false);
        super.add(question);

        for (int i=0;i<MAX_ANSWERS;i++){
            answers[i]=new JRadioButton();
            answers[i].setFocusable(false);
            answersGroup.add(answers[i]);
            super.add(answers[i]);
        }

        next.setFocusable(false);
        next.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isAnswered=false;
                int i=0;
                for (;i<MAX_ANSWERS;i++){
                    if (answers[i].isSelected()) {
                        isAnswered=true;
                        break;
                    }
                }
                if (isAnswered) {
                    TestSequence.result(i+1);
                    TestSequence.nextTest();
                }
            }
        });
        next.setText(">>");
        super.add(next);

        super.setVisible(true);
    }

    public void showTest(Test test){
        this.question.setText(test.getQuestion());
        for (int i = test.getNumberOfAnswers(); i<MAX_ANSWERS; i++){
            answers[i].setVisible(false);
        }
        for (int i = 0; i<test.getNumberOfAnswers(); i++){
            answers[i].setText(test.getAnswer(i));
            answers[i].setVisible(true);
        }
        answersGroup.clearSelection();
    }

    public void showResults(){
        this.remove(next);
        for (JRadioButton i :
                answers) {
            this.remove(i);
        }
        question.setFont(question.getFont().deriveFont(40f));
        question.setText("Ваша оценка: "+TestSequence.getResult());
        this.validate();
    }

}
