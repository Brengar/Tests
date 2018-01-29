package database;

import main.Main;
import tests.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBase {

    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String password = "root";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    private static Test[] tests=new Test[Main.NUMBER_OF_TESTS];
    private static ArrayList<Test> allSelectedTests=new ArrayList<>();

    public static void intit(){

        String querry="DROP TABLE tests;";
        insert(querry);

        querry = "CREATE TABLE tests (\n" +
                "    Grade int,\n" +
                "    NumberOfAnswers int,\n" +
                "    RightAnswer int,\n" +
                "    Question varchar(255),\n" +
                "    Answer1 varchar(255),\n" +
                "    Answer2 varchar(255),\n" +
                "    Answer3 varchar(255),\n" +
                "    Answer4 varchar(255),\n" +
                "    Answer5 varchar(255)\n" +
                ");";
        insert(querry);

        for (int i=0;i<101;i++){
            int n=2+(int)(Math.random()*4);
            int a=(int)(Math.random()*(n)+1);
            querry="INSERT INTO tests\n" +
                    "VALUES ("+i+", "+n+", "+a+", 'Example of test N"+i+" (answer is "+a+")?', '1', '2', '3', '4', '5');";
            insert(querry);
        }


    }

    public static Test[] getTests(int grade){

        int i=0;
        for (;i<101;i+=5){
            if (isEnoughTests(grade-i,grade+i)) break;
        }

        select(grade-i,grade+i);

        for (int j=0;j<Main.NUMBER_OF_TESTS;j++){
            tests[j]=allSelectedTests.remove((int)(Math.random()*allSelectedTests.size()));
        }

        return tests;

    }

    private static void insert(String querry){

        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();

            stmt.executeUpdate(querry);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }

    }

    private static boolean isEnoughTests(int gradeMin,int gradeMax){

        String querry="SELECT COUNT(*)\n" +
                "FROM tests\n" +
                "WHERE Grade BETWEEN "+gradeMin+" AND "+gradeMax+";";

        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(querry);

            while (rs.next()) {
                int count = rs.getInt(1);
                if (count>=Main.NUMBER_OF_TESTS) return true;
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }

        return false;

    }

    private static void select(int gradeMin,int gradeMax){

        String querry="SELECT DISTINCT *\n" +
                "FROM tests\n" +
                "WHERE Grade BETWEEN "+gradeMin+" AND "+gradeMax+";";

        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(querry);

            while (rs.next()) {
                Test test=new Test();
                test.setNumberOfAnswers(rs.getInt(2));
                test.setRightAnswer(rs.getInt(3));
                test.setQuestion(rs.getString(4));
                test.setAnswer(rs.getString(5),0);
                test.setAnswer(rs.getString(6),1);
                test.setAnswer(rs.getString(7),2);
                test.setAnswer(rs.getString(8),3);
                test.setAnswer(rs.getString(9),4);
                allSelectedTests.add(test);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }

    }

}
