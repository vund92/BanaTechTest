package datas;

import lombok.Data;
import pojos.Member;
import pojos.Teacher;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class TeacherDataAnalyzer {
    public static void printClassCountsAndSalary(List<Teacher> qualifiedClasses, List<Teacher> unqualifiedClasses) {
        // Map to store qualified and unqualified class counts for each teacher name
        Map<String, int[]> classCounts = new HashMap<>();

        // Count qualified classes
        for (Teacher teacher : qualifiedClasses) {
            String teacherName = teacher.getTeacherName();
            classCounts.putIfAbsent(teacherName, new int[2]);
            classCounts.get(teacherName)[0]++;
        }

        // Count unqualified classes
        for (Teacher teacher : unqualifiedClasses) {
            String teacherName = teacher.getTeacherName();
            classCounts.putIfAbsent(teacherName, new int[2]);
            classCounts.get(teacherName)[1]++;
        }

//        // Print class counts for each teacher name
//        for (Map.Entry<String, int[]> entry : classCounts.entrySet()) {
//            String teacherName = entry.getKey();
//            int[] counts = entry.getValue();
//            int qualifiedCount = counts[0];
//            int unqualifiedCount = counts[1];
//            int salaryBeforeFine = qualifiedCount*10;
//            int fine = unqualifiedCount>=10?3:0;
//            int finalSalary = salaryBeforeFine - fine;
//            System.out.println("Teacher: " + teacherName + ", Qualified Classes: " + qualifiedCount + ", Unqualified Classes: " + unqualifiedCount);
//            System.out.println("Salary Before Fine: $" + salaryBeforeFine + ", Fine: $" + fine + ", Final Salary = (SalaryBeforeFine)-Fine = $" + finalSalary);
//            System.out.println("-----");
//        }

        String outputFile = "Results_Report.txt";

        try (FileWriter writer = new FileWriter(new File(outputFile))) {
            // Write counts of qualified and unqualified classes
            writer.write("Qualified Class Count = " + qualifiedClasses.size() + "\n");
            writer.write("Unqualified Class Count = " + unqualifiedClasses.size() + "\n");
            writer.write("-----\n");

            System.out.println("Qualitified Class Count = " + qualifiedClasses.size());
            System.out.println("Unqualitified Class Count = " + unqualifiedClasses.size());
            System.out.println("-----");

            // Print class counts for each teacher name
            for (Map.Entry<String, int[]> entry : classCounts.entrySet()) {
                String teacherName = entry.getKey();
                int[] counts = entry.getValue();
                int qualifiedCount = counts[0];
                int unqualifiedCount = counts[1];
                int salaryBeforeFine = qualifiedCount * 10;
                int fine = unqualifiedCount >= 10 ? 3 : 0;
                int finalSalary = salaryBeforeFine - fine;

                // Write data to the file
                writer.write("Teacher: " + teacherName + ", Qualified Classes: " + qualifiedCount + ", Unqualified Classes: " + unqualifiedCount + "\n");
                writer.write("Salary Before Fine: $" + salaryBeforeFine + ", Fine: $" + fine + ", Final Salary = (SalaryBeforeFine)-Fine = $" + finalSalary + "\n");
                writer.write("-----\n");

                System.out.println("Teacher: " + teacherName + ", Qualified Classes: " + qualifiedCount + ", Unqualified Classes: " + unqualifiedCount);
                System.out.println("Salary Before Fine: $" + salaryBeforeFine + ", Fine: $" + fine + ", Final Salary = (SalaryBeforeFine)-Fine = $" + finalSalary);
                System.out.println("-----\n");
            }

            System.out.println("\n>>>>>>>> Results have been written to " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static List<Teacher> getQualifiedClasses(List<Teacher> teacherList){
//        List<Teacher> qualifiedClasses = teacherList.stream()
//                .filter(teacher -> !teacher.getTeacherName().isEmpty() &&
//                        !teacher.getTeacherMinute().isEmpty() &&
//                        Double.parseDouble(teacher.getTeacherMinute()) >= 90 &&
//                        countQualifiedMembers(teacher.getMembers()) >= 1)
//                .collect(Collectors.toList());
//
////        // Print the data
////        for (Teacher teacherData : qualifiedClasses) {
////            System.out.println(teacherData);
////        }
//
//        return  qualifiedClasses;
//    }

//    private static long countQualifiedMembers(List<Member> members) {
//        return members.stream()
//                .filter(member ->
//                        !member.getName().isEmpty() &&
//                                !member.getMinute().isEmpty() &&
//                                Double.parseDouble(member.getMinute()) >= 75)
//                .count();
//    }

    public static List<Teacher> getUnqualifiedClasses(List<Teacher> allTeachers, List<Teacher> qualifiedTeachers) {
        List<Teacher> unqualifiedTeachers = allTeachers.stream()
                .filter(teacher -> !qualifiedTeachers.contains(teacher))
                .collect(Collectors.toList());

//        // Print the data
//        for (Teacher teacherData : unqualifiedTeachers) {
//            System.out.println(teacherData);
//        }

        return unqualifiedTeachers;
    }

    //-----------------------------

    public static List<Teacher> getQualifiedClasses(List<Teacher> teacherList) {
        return teacherList.stream()
                .filter(teacher ->
                        !teacher.getTeacherName().isEmpty() &&
                                !teacher.getTeacherMinute().isEmpty() &&
                                Double.parseDouble(teacher.getTeacherMinute()) >= 90 &&
                                countQualifiedMembers(teacher.getMembers()) >= 2 &&
                                hasQualifiedMember(teacher.getMembers()))
                .collect(Collectors.toList());
    }

    private static long countQualifiedMembers(List<Member> members) {
        return members.stream()
                .filter(member ->
                        !member.getName().isEmpty() &&
                                !member.getMinute().isEmpty())
                .count();
    }

    private static boolean hasQualifiedMember(List<Member> members) {
        return members.stream()
                .filter(member ->
                        !member.getName().isEmpty() &&
                                !member.getMinute().isEmpty() &&
                                Double.parseDouble(member.getMinute()) >= 75)
                .count() >= 1;
    }
}
