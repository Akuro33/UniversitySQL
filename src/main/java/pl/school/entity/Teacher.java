package pl.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "teacher")
public class Teacher {
// Nauczyciel powinien mieć pola: imię, nazwisko, wiek, email oraz przedmiot.
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column(name="id_teacher")
    private Integer idTeacher;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="age")
    private int age;

    @Column(name="email")
    private String email;

    @Column(name="subject")
    private String subject;

    @JsonIgnore
    @ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "teacher_student", joinColumns = @JoinColumn(name = "id_teacher"), inverseJoinColumns = @JoinColumn(name = "id_student"))
    private List<Student> studentList;

    /** Constructor **/
    public Teacher() {
    }

    public Teacher(String firstName, String lastName, int age, String email, String subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.subject = subject;
    }

    /** Methods **/
    public void addStudent (Student student) {
        if (studentList == null) {
            studentList = new ArrayList<>();
        }
        studentList.add(student);
    }

    public Integer getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(Integer idTeacher) {
        this.idTeacher = idTeacher;
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

    public int getAge() {
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

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "idTeacher=" + idTeacher +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return age == teacher.age && Objects.equals(idTeacher, teacher.idTeacher) && Objects.equals(firstName, teacher.firstName) && Objects.equals(lastName, teacher.lastName) && Objects.equals(email, teacher.email) && Objects.equals(subject, teacher.subject) && Objects.equals(studentList, teacher.studentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTeacher, firstName, lastName, age, email, subject, studentList);
    }
}
