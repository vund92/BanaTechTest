package tests;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
    public static List<TeacherPojo> readTeacherDataFromCSVFile(String csvFile){
        List<TeacherPojo> teacherPojoList = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            // Read CSV data
            List<String[]> csvData = reader.readAll();

            csvData.remove(0); //remove the headers

            // Iterate through each row and create tests.TeacherPojo objects
            for (String[] row : csvData) {
                String id = row[0];
                String teacherName = row[1];
                String teacherMinute = row[2];

                TeacherPojo teacherPojo = new TeacherPojo(id, teacherName, teacherMinute);

                // Iterate over members and add to the list
                for (int i = 3; i < row.length; i += 2) {
                    if (row[i] != null && row[i + 1] != null) {
                        String memberName = row[i];
                        String memberMinute = row[i + 1];

                        MemberPojo member = new MemberPojo(memberName, memberMinute);
                        teacherPojo.getMembers().add(member);
                    }
                }

                teacherPojoList.add(teacherPojo);
            }

//            // Print the data
//            for (TeacherPojo teacherData : teacherPojoList) {
//                System.out.println(teacherData);
//            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return teacherPojoList;
    }
}
