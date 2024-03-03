package tests;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TeacherPojo {
    private String id;
    private String teacherName;
    private String teacherMinute;
    private List<MemberPojo> members;

    public TeacherPojo(String id, String teacherName, String teacherMinute) {
        this.id = id;
        this.teacherName = teacherName;
        this.teacherMinute = teacherMinute;
        this.members = new ArrayList<>();
    }

//    public static List<TeacherPojo> readTeacherDataFromCSVFile(String csvFile){
//        List<TeacherPojo> teacherDataList = new ArrayList<>();
//
//        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
//            // Read CSV data
//            List<String[]> csvData = reader.readAll();
//
//            csvData.remove(0); //remove the headers
//
//            // Iterate through each row and create tests.TeacherPojo objects
//            for (String[] row : csvData) {
//                String id = row[0];
//                String teacherName = row[1];
//                String teacherMinute = row[2];
//
//                TeacherPojo teacherData = new TeacherPojo(id, teacherName, teacherMinute);
//
//                // Iterate over members and add to the list
//                for (int i = 3; i < row.length; i += 2) {
//                    if (row[i] != null && row[i + 1] != null) {
//                        String memberName = row[i];
//                        String memberMinute = row[i + 1];
//
//                        MemberPojo member = new MemberPojo(memberName, memberMinute);
//                        teacherData.getMembers().add(member);
//                    }
//                }
//
//                teacherDataList.add(teacherData);
//            }
//
////            // Print the data
////            for (TeacherPojo teacherData : teacherDataList) {
////                System.out.println(teacherData);
////            }
//        } catch (IOException | CsvException e) {
//            e.printStackTrace();
//        }
//
//        return teacherDataList;
//    }
//
//    public static List<TeacherPojo> getQualifiedClasses(List<TeacherPojo> teacherDataList){
//        List<TeacherPojo> qualifiedClasses = teacherDataList.stream()
//                .filter(teacherData -> !teacherData.getTeacherName().isEmpty() &&
//                        !teacherData.getTeacherMinute().isEmpty() &&
//                        Double.parseDouble(teacherData.getTeacherMinute()) >= 90 &&
//                        countQualifiedMembers(teacherData.getMembers()) >= 2)
//                .collect(Collectors.toList());
//
////        // Print the data
////        for (TeacherPojo teacherData : qualifiedClasses) {
////            System.out.println(teacherData);
////        }
//
//        return  qualifiedClasses;
//    }
//
//    private static long countQualifiedMembers(List<MemberPojo> members) {
//        return members.stream()
//                .filter(member ->
//                        !member.getName().isEmpty() &&
//                                !member.getMinute().isEmpty() &&
//                                Double.parseDouble(member.getMinute()) >= 75)
//                .count();
//    }
//
//    public static List<TeacherPojo> getUnqualifiedClasses(List<TeacherPojo> allTeachers, List<TeacherPojo> qualifiedTeachers) {
//        List<TeacherPojo> unqualifiedTeachers = allTeachers.stream()
//                .filter(teacher -> !qualifiedTeachers.contains(teacher))
//                .collect(Collectors.toList());
//
////        // Print the data
////        for (TeacherPojo teacherData : unqualifiedTeachers) {
////            System.out.println(teacherData);
////        }
//
//        return unqualifiedTeachers;
//    }
}
