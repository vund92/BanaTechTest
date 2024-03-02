package tests;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static tests.TeacherData.readTeacherDataFromCSVFile;

public class Tests {

    @Test
    public static void printClassCountsForEachTeacherName() {
        String csvFile = "src/main/resources/MOCK_DATA.csv";
        List<TeacherData> teacherDataList = new ArrayList<>();
        teacherDataList = readTeacherDataFromCSVFile(csvFile);
        List<TeacherData> qualitifiedClasses = new ArrayList<>();
        qualitifiedClasses = TeacherData.getQualifiedClasses(teacherDataList);
        System.out.println("Qualitified Class Count = " + qualitifiedClasses.size());
        List<TeacherData> unqualitifiedClasses = new ArrayList<>();
        unqualitifiedClasses = TeacherData.getUnqualifiedClasses(teacherDataList,qualitifiedClasses);
        System.out.println("Unqualitified Class Count = " + unqualitifiedClasses.size());
        System.out.println("-----");
        TeacherDataAnalyzer.printClassCountsAndSalary(qualitifiedClasses, unqualitifiedClasses);
    }
}
