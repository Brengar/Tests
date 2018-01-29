package main;

import database.DataBase;
import gui.Frame;
import tests.TestSequence;

public class Main {

    public static final int NUMBER_OF_TESTS=5;
    private static Frame frame=new Frame();

    public static void main(String[] args) {

        //Метод, который создает в базе маблицу с тестами (Имя tests, !!!удаляет старую, если была с таким именем!!!)
        //работает с MySQL базой
        //DataBase.intit();

        //метод принимает значение от 0 до 100, означающее примерный уровень знаний,
        //и на его основе выбирает подходящие тесты из базы
        TestSequence.start(50);

    }

    public static Frame getFrame() {
        return frame;
    }
}
