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
}
