package tests;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static tests.CSVUtils.readTeacherDataFromCSVFile;
import static tests.TeacherDataAnalyzer.getQualifiedClasses;
import static tests.TeacherDataAnalyzer.getUnqualifiedClasses;

public class Tests {
    @Test
    public static void printClassCountsForEachTeacherName() {
        String csvFile = "src/main/resources/MOCK_DATA.csv";
        List<TeacherPojo> teacherPojoList = new ArrayList<>();
        teacherPojoList = readTeacherDataFromCSVFile(csvFile);
        List<TeacherPojo> qualitifiedClasses = new ArrayList<>();
        qualitifiedClasses = getQualifiedClasses(teacherPojoList);
        System.out.println("Qualitified Class Count = " + qualitifiedClasses.size());
        List<TeacherPojo> unqualitifiedClasses = new ArrayList<>();
        unqualitifiedClasses = getUnqualifiedClasses(teacherPojoList,qualitifiedClasses);
        System.out.println("Unqualitified Class Count = " + unqualitifiedClasses.size());
        System.out.println("-----");
        TeacherDataAnalyzer.printClassCountsAndSalary(qualitifiedClasses, unqualitifiedClasses);
    }
}
