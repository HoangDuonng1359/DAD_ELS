package Game;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class importData {
    /**
     * import data form file csv.
     *
     * @param source source file csv
     * @return list question
     */
    public static List<Question> importFormFileCSV(String source) {

        List<Question> list = new ArrayList<Question>();
        try{
            Reader reader = new FileReader(source);
            CSVReader csvReader = new CSVReader(reader);
            String[] line;
            while((line = csvReader.readNext())!=null){
                Question question = new Question();
                question.setQuestion(line[1]);
                question.setChoice(new String[]{line[2],line[3],line[4],line[5]});
                question.setAnswer(line[6]);
                list.add(question);
            }
            return list;
        } catch (CsvValidationException | IOException e){
            return null;
        }
    }
}
