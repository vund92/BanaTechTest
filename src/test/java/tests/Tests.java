package tests;

import datas.TeacherDataAnalyzer;
import org.testng.annotations.Test;
import pojos.Teacher;

import java.util.ArrayList;
import java.util.List;

import static utils.CSVUtils.readTeacherDataFromCSVFile;
import static datas.TeacherDataAnalyzer.getQualifiedClasses;
import static datas.TeacherDataAnalyzer.getUnqualifiedClasses;

public class Tests {
    @Test
    public static void printClassCountsForEachTeacherName() {
        String csvFile = "src/main/resources/MOCK_DATA.csv";
        List<Teacher> teacherList = new ArrayList<>();
        teacherList = readTeacherDataFromCSVFile(csvFile);
        List<Teacher> qualifiedClasses = new ArrayList<>();
        qualifiedClasses = getQualifiedClasses(teacherList);
        List<Teacher> unqualifiedClasses = new ArrayList<>();
        unqualifiedClasses = getUnqualifiedClasses(teacherList,qualifiedClasses);
        TeacherDataAnalyzer.printClassCountsAndSalary(qualifiedClasses, unqualifiedClasses);
    }
}
