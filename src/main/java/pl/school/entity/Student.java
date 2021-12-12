package pl.school.entity;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_student")
    private Integer idStudent;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="age")
    private Integer age;

    @Column(name="email")
    private String email;

    @Column(name="subject")
    private String subject;

    @ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "teacher_student", joinColumns = @JoinColumn(name = "id_student"), inverseJoinColumns = @JoinColumn(name = "id_teacher"))
    private List<Teacher> teacherList;


    public void addTeacher (Teacher teacher) {
        if (teacherList == null) {
            teacherList = new ArrayList<>();
        }
        teacherList.add(teacher);
    }

    public Student() {
    }

    public Student(Integer idstudent, String firstName, String lastName, int age, String email, String subject) {
        this.idStudent = idstudent;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.subject = subject;
    }

    public Student(Integer idstudent, String firstName, String lastName, int age, String email, String subject, List<Teacher> teacherList) {
        this.idStudent = idstudent;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.subject = subject;
        this.teacherList = teacherList;
    }

    public Integer getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Integer idstudent) {
        this.idStudent = idstudent;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }
}
