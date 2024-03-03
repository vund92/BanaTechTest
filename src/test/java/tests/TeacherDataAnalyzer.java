package tests;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.Data;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class TeacherDataAnalyzer {
    public static void printClassCountsAndSalary(List<TeacherPojo> qualifiedClasses, List<TeacherPojo> unqualifiedClasses) {
        // Map to store qualified and unqualified class counts for each teacher name
        Map<String, int[]> classCounts = new HashMap<>();

        // Count qualified classes
        for (TeacherPojo teacherPojo : qualifiedClasses) {
            String teacherName = teacherPojo.getTeacherName();
            classCounts.putIfAbsent(teacherName, new int[2]);
            classCounts.get(teacherName)[0]++;
        }

        // Count unqualified classes
        for (TeacherPojo teacherPojo : unqualifiedClasses) {
            String teacherName = teacherPojo.getTeacherName();
            classCounts.putIfAbsent(teacherName, new int[2]);
            classCounts.get(teacherName)[1]++;
        }

        // Print class counts for each teacher name
        for (Map.Entry<String, int[]> entry : classCounts.entrySet()) {
            String teacherName = entry.getKey();
            int[] counts = entry.getValue();
            int qualifiedCount = counts[0];
            int unqualifiedCount = counts[1];
            int salaryBeforeFine = qualifiedCount*10;
            int fine = unqualifiedCount>=10?3:0;
            int finalSalary = salaryBeforeFine - fine;
            System.out.println("Teacher: " + teacherName + ", Qualified Classes: " + qualifiedCount + ", Unqualified Classes: " + unqualifiedCount);
            System.out.println("Salary Before Fine: $" + salaryBeforeFine + ", Fine: $" + fine + ", Final Salary = (SalaryBeforeFine)-Fine = $" + finalSalary);
            System.out.println("-----");
        }
    }

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

    public static List<TeacherPojo> getQualifiedClasses(List<TeacherPojo> teacherPojoList){
        List<TeacherPojo> qualifiedClasses = teacherPojoList.stream()
                .filter(teacherPojo -> !teacherPojo.getTeacherName().isEmpty() &&
                        !teacherPojo.getTeacherMinute().isEmpty() &&
                        Double.parseDouble(teacherPojo.getTeacherMinute()) >= 90 &&
                        countQualifiedMembers(teacherPojo.getMembers()) >= 2)
                .collect(Collectors.toList());

//        // Print the data
//        for (TeacherPojo teacherData : qualifiedClasses) {
//            System.out.println(teacherData);
//        }

        return  qualifiedClasses;
    }

    private static long countQualifiedMembers(List<MemberPojo> members) {
        return members.stream()
                .filter(member ->
                        !member.getName().isEmpty() &&
                                !member.getMinute().isEmpty() &&
                                Double.parseDouble(member.getMinute()) >= 75)
                .count();
    }

    public static List<TeacherPojo> getUnqualifiedClasses(List<TeacherPojo> allTeachers, List<TeacherPojo> qualifiedTeachers) {
        List<TeacherPojo> unqualifiedTeachers = allTeachers.stream()
                .filter(teacher -> !qualifiedTeachers.contains(teacher))
                .collect(Collectors.toList());

//        // Print the data
//        for (TeacherPojo teacherData : unqualifiedTeachers) {
//            System.out.println(teacherData);
//        }

        return unqualifiedTeachers;
    }
}
