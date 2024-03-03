package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import pojos.Member;
import pojos.Teacher;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
    public static List<Teacher> readTeacherDataFromCSVFile(String csvFile){
        List<Teacher> teacherList = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            // Read CSV data
            List<String[]> csvData = reader.readAll();

            csvData.remove(0); //remove the headers

            // Iterate through each row and create pojos.Teacher objects
            for (String[] row : csvData) {
                String id = row[0];
                String teacherName = row[1];
                String teacherMinute = row[2];

                Teacher teacher = new Teacher(id, teacherName, teacherMinute);

                // Iterate over members and add to the list
                for (int i = 3; i < row.length; i += 2) {
                    if (row[i] != null && row[i + 1] != null) {
                        String memberName = row[i];
                        String memberMinute = row[i + 1];

                        Member member = new Member(memberName, memberMinute);

                        teacher.getMembers().add(member);
                    }
                }

                teacherList.add(teacher);
            }

//            // Print the data
//            for (Teacher teacherData : teacherList) {
//                System.out.println(teacherData);
//            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return teacherList;
    }
}
