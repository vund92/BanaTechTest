package tests;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.Data;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TeacherData {
    private String id;
    private String teacherName;
    private String teacherMinute;
    private List<MemberData> members;

    public TeacherData(String id, String teacherName, String teacherMinute) {
        this.id = id;
        this.teacherName = teacherName;
        this.teacherMinute = teacherMinute;
        this.members = new ArrayList<>();
    }

    public static List<TeacherData> readTeacherDataFromCSVFile(String csvFile){
        List<TeacherData> teacherDataList = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            // Read CSV data
            List<String[]> csvData = reader.readAll();

            csvData.remove(0); //remove the headers

            // Iterate through each row and create tests.TeacherData objects
            for (String[] row : csvData) {
                String id = row[0];
                String teacherName = row[1];
                String teacherMinute = row[2];

                TeacherData teacherData = new TeacherData(id, teacherName, teacherMinute);

                // Iterate over members and add to the list
                for (int i = 3; i < row.length; i += 2) {
                    if (row[i] != null && row[i + 1] != null) {
                        String memberName = row[i];
                        String memberMinute = row[i + 1];

                        MemberData member = new MemberData(memberName, memberMinute);
                        teacherData.getMembers().add(member);
                    }
                }

                teacherDataList.add(teacherData);
            }

//            // Print the data
//            for (TeacherData teacherData : teacherDataList) {
//                System.out.println(teacherData);
//            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return teacherDataList;
    }

    public static List<TeacherData> getQualifiedClasses(List<TeacherData> teacherDataList){
        List<TeacherData> qualifiedClasses = teacherDataList.stream()
                .filter(teacherData -> !teacherData.getTeacherName().isEmpty() &&
                        !teacherData.getTeacherMinute().isEmpty() &&
                        Double.parseDouble(teacherData.getTeacherMinute()) >= 90 &&
                        countQualifiedMembers(teacherData.getMembers()) >= 2)
                .collect(Collectors.toList());

//        // Print the data
//        for (TeacherData teacherData : qualifiedClasses) {
//            System.out.println(teacherData);
//        }

        return  qualifiedClasses;
    }

    private static long countQualifiedMembers(List<MemberData> members) {
        return members.stream()
                .filter(member ->
                        !member.getName().isEmpty() &&
                                !member.getMinute().isEmpty() &&
                                Double.parseDouble(member.getMinute()) >= 75)
                .count();
    }

    public static List<TeacherData> getUnqualifiedClasses(List<TeacherData> allTeachers, List<TeacherData> qualifiedTeachers) {
        List<TeacherData> unqualifiedTeachers = allTeachers.stream()
                .filter(teacher -> !qualifiedTeachers.contains(teacher))
                .collect(Collectors.toList());

//        // Print the data
//        for (TeacherData teacherData : unqualifiedTeachers) {
//            System.out.println(teacherData);
//        }

        return unqualifiedTeachers;
    }
}
