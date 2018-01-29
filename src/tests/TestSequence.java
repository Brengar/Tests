package tests;

import database.DataBase;
import main.Main;

public class TestSequence {

    private static Test[] tests=new Test[Main.NUMBER_OF_TESTS];
    private static int counter=0;

    public static void nextTest(){
        if (counter< Main.NUMBER_OF_TESTS) {
            Main.getFrame().showTest(tests[counter]);
            counter++;
        } else {
            Main.getFrame().showResults();
        }
    }

    public static void result(int result){
        if (tests[counter-1].getRightAnswer()==result) {
            tests[counter-1].setResult(true);
        }
    }

    public static int getResult(){
        float result=0;
        for (Test i :
                tests) {
            if (i.isRight()) result++;
        }
        result=result/ Main.NUMBER_OF_TESTS;
        if (result>0.80) return 5;
        if (result>0.65) return 4;
        if (result>0.50) return 3;
        return 2;
    }

    public static void start(int grade) {
        TestSequence.tests = DataBase.getTests(50);
        nextTest();
    }
}
