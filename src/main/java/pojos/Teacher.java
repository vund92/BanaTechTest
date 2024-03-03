package pojos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Teacher {
    private String id;
    private String teacherName;
    private String teacherMinute;
    private List<Member> members;

    public Teacher(String id, String teacherName, String teacherMinute) {
        this.id = id;
        this.teacherName = teacherName;
        this.teacherMinute = teacherMinute;
        this.members = new ArrayList<>();
    }
}
