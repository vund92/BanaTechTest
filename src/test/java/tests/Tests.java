package tests;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Tests {
    @Test
    public static void printClassCountsForEachTeacherName() {
        String csvFile = "src/main/resources/MOCK_DATA.csv";
        List<TeacherPojo> teacherPojoList = new ArrayList<>();
        teacherPojoList = TeacherDataAnalyzer.readTeacherDataFromCSVFile(csvFile);
        List<TeacherPojo> qualitifiedClasses = new ArrayList<>();
        qualitifiedClasses = TeacherDataAnalyzer.getQualifiedClasses(teacherPojoList);
        System.out.println("Qualitified Class Count = " + qualitifiedClasses.size());
        List<TeacherPojo> unqualitifiedClasses = new ArrayList<>();
        unqualitifiedClasses = TeacherDataAnalyzer.getUnqualifiedClasses(teacherPojoList,qualitifiedClasses);
        System.out.println("Unqualitified Class Count = " + unqualitifiedClasses.size());
        System.out.println("-----");
        TeacherDataAnalyzer.printClassCountsAndSalary(qualitifiedClasses, unqualitifiedClasses);
    }
}
