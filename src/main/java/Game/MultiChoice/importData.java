package Game.MultiChoice;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import dictionary.DatabaseConnection;

public class importData {
    /**
     * import data form file csv.
     *
     * @param source source file csv
     * @return list question
     */
    public static List<Question> importFormFileCSV(String source) {

        List<Question> list = new ArrayList<Question>();
        try {
            Reader reader = new FileReader(source);
            CSVReader csvReader = new CSVReader(reader);
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                Question question = new Question();
                question.setQuestion(line[1]);
                question.setChoice(new String[]{line[2], line[3], line[4], line[5]});
                question.setAnswer(line[6]);
                list.add(question);
            }
            return list;
        } catch (CsvValidationException | IOException e) {
            return null;
        }
    }

    public static List<Question> insertFromDB(String DB_URL) throws SQLException {
        List<Question> list = new ArrayList<Question>();
        Connection conn = DatabaseConnection.connect(DB_URL);
        String sql = "SELECT * FROM multiChoice ";
        PreparedStatement pr = conn.prepareStatement(sql);
        ResultSet rs = pr.executeQuery();
        while (rs.next()) {
            Question question = new Question();
            question.setQuestion(rs.getString("question"));
            question.setChoice(new String[]{rs.getString("choice1"),
                    rs.getString("choice2"),
                    rs.getString("choice3"),
                    rs.getString("choice4")});
            question.setAnswer(rs.getString("answer"));
            list.add(question);
        }
        conn.close();
        return list;
    }

    public static void insertToDB() throws SQLException {
        Connection conn = DatabaseConnection.connect("jdbc:sqlite:src\\Data\\database.db");
        String sql = "INSERT INTO multiChoice (question, choice1 , choice2 , choice3 , choice4, answer ) VALUES (?,?,?,?,?,?)";
        PreparedStatement pr = conn.prepareStatement(sql);
        List<Question> questionList = importFormFileCSV("src/Data/questions.csv");
        for (Question q : questionList) {
            pr.setString(1, q.getQuestion());
            String[] choice = q.getChoice();
            pr.setString(2, choice[0]);
            pr.setString(3, choice[1]);
            pr.setString(4, choice[2]);
            pr.setString(5, choice[3]);
            pr.setString(6, q.getAnswer());
            pr.executeUpdate();
        }
        conn.close();
    }

    public static void main(String[] args) throws SQLException {
        List<Question> questionList = insertFromDB("jdbc:sqlite:src\\Data\\database.db");
        for(Question question : questionList){
            System.out.println(question.getAnswer());
        }
    }
}
