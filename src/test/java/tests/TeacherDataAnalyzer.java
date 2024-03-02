package tests;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class TeacherDataAnalyzer {
    public static void printClassCountsAndSalary(List<TeacherData> qualifiedClasses, List<TeacherData> unqualifiedClasses) {
        // Map to store qualified and unqualified class counts for each teacher name
        Map<String, int[]> classCounts = new HashMap<>();

        // Count qualified classes
        for (TeacherData teacherData : qualifiedClasses) {
            String teacherName = teacherData.getTeacherName();
            classCounts.putIfAbsent(teacherName, new int[2]);
            classCounts.get(teacherName)[0]++;
        }

        // Count unqualified classes
        for (TeacherData teacherData : unqualifiedClasses) {
            String teacherName = teacherData.getTeacherName();
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
}
