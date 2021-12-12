package pl.school.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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


    @ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "teacher_student", joinColumns = @JoinColumn(name = "id_teacher"), inverseJoinColumns = @JoinColumn(name = "id_student"))
    private List<Student> studentList;

    public void addStudent (Student student) {
        if (studentList == null) {
            studentList = new ArrayList<>();
        }
        studentList.add(student);
    }

}
