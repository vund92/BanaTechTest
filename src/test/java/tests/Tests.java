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
        List<Teacher> qualitifiedClasses = new ArrayList<>();
        qualitifiedClasses = getQualifiedClasses(teacherList);
        System.out.println("Qualitified Class Count = " + qualitifiedClasses.size());
        List<Teacher> unqualitifiedClasses = new ArrayList<>();
        unqualitifiedClasses = getUnqualifiedClasses(teacherList,qualitifiedClasses);
        System.out.println("Unqualitified Class Count = " + unqualitifiedClasses.size());
        System.out.println("-----");
        TeacherDataAnalyzer.printClassCountsAndSalary(qualitifiedClasses, unqualitifiedClasses);
    }
}
