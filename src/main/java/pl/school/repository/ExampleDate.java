package pl.school.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.school.entity.Student;
import pl.school.entity.Teacher;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExampleDate {

    private Session session;
    private final Map<Integer, Student> students = new HashMap<>();
    private List <Teacher> teacherList;
    private List <Student> studentList;

    public ExampleDate(Session session) {
        this.session = session;
    }

    public void checkDB () {
        //Tak, wiem iż tak nie powinno się robić
        try {
            String select = "SELECT s from Student s where firstName='adam'";
            Query query = session.createQuery(select);
            Student student = (Student) query.getSingleResult();
        } catch (NoResultException e) {
            this.studentList= giveAll();
            addStudentsToDB();
        }
       try {
            String select = "SELECT t from Teacher t where firstName='Zenon'";
            Query query = session.createQuery(select);
            Teacher teacher = (Teacher) query.getSingleResult();
        } catch (NoResultException e) {
           this.teacherList = populateTeacher();
            addTeacherToDB();
           addTeachersAndStudentsToInsideList();
        }
    }

    private void addStudentsToDB () {
        for (Student student: studentList){
            Student student1 = new Student();
            student1.setFirstName(student.getFirstName());
            student1.setLastName(student.getLastName());
            student1.setAge((int)student.getAge());
            student1.setSubject(student.getSubject());
            student1.setEmail(student.getEmail());

            session.persist(student1);
        }
    }

    private void addTeacherToDB () {

        for (Teacher teacher: teacherList) {
            Teacher teacher1 = new Teacher();
            teacher1.setFirstName(teacher.getFirstName());
            teacher1.setLastName(teacher.getLastName());
            teacher1.setAge((int)teacher.getAge());
            teacher1.setSubject(teacher.getSubject());
            teacher1.setEmail(teacher.getEmail());
            session.persist(teacher1);
        }
    }

    private List<Student> giveAll (){
        populateStudents();
        return students.values().stream().collect(Collectors.toList());
    }

    private void populateStudents() {
        students.put(1, new Student(1, "Adam", "Adamowicz", 19, "Liam2312@gmail.com", "IT"));
        students.put(2, new Student(2, "Beata", "Brandicz", 20, "Brandicz@gmail.com", "IT"));
        students.put(3, new Student(3, "Celina", "Cekarska", 21, "Cekarska12@gmail.com", "IT"));
        students.put(4, new Student(4, "Daniel", "Dwakowski", 19, "Dwakowski@gmail.com", "Chemistry"));
        students.put(5, new Student(5, "Eryk", "Ezkiel", 20, "Ezkiel@gmail.com", "Chemistry"));
        students.put(6, new Student(6, "Fiona", "Frankowska", 21, "Frankowska@gmail.com", "Chemistry"));
        students.put(7, new Student(7, "Gabriel", "Grzanowsi", 19, "Grzanowsi@gmail.com", "Chemistry"));
        students.put(8, new Student(8, "Horacy", "Hyundai", 20, "Hyundai@gmail.com", "Chemistry"));
        students.put(9, new Student(9, "Iga", "Ineczko", 21, "Ineczko@gmail.com", "Physics"));
        students.put(10, new Student(10, "Juliam", "Juwin", 22, "Juwin@gmail.com", "Physics"));
        students.put(11, new Student(11, "Karol", "Krawczyk", 19, "Krawczyk@gmail.com", "Physics"));
        students.put(12, new Student(12, "Liam", "Lorency", 20, "Lorency@gmail.com", "Physics"));
        students.put(13, new Student(13, "Monika", "Manerska", 21, "Manerska@gmail.com", "Physics"));
        students.put(14, new Student(14, "Norbert", "Nawarski", 22, "Nawarski@gmail.com", "IT"));
        students.put(15, new Student(15, "Ola", "Orkisz", 19, "Orkisz@gmail.com", "IT"));
        students.put(16, new Student(16, "Piotr", "Pawlewski", 20, "Pawlewski@gmail.com", "IT"));
        students.put(17, new Student(17, "Radek", "Rodecki", 21, "Rodecki@gmail.com", "IT"));
        students.put(18, new Student(18, "Sylwanas", "Sandrowska", 19, "Sandrowska@gmail.com", "IT"));
        students.put(19, new Student(19, "Tomek", "Tamczyk", 20, "Tamczyk@gmail.com", "IT"));
    }

    private List<Teacher> populateTeacher () {
        List <Teacher> teacherList = new ArrayList<>();
        teacherList.add(new Teacher("Zenon", "Zakrawski", 30, "Zakrawski@gmail.com", "IT"));
        teacherList.add(new Teacher("Yenefer", "Yolik", 30, "Yoliki@gmail.com", "IT"));
        teacherList.add(new Teacher("Xawier", "Xenomorf", 30, "Xenomorf@gmail.com", "Physics"));
        teacherList.add(new Teacher("Wiktor", "Wrona", 30, "Wrona@gmail.com", "Physics"));
        teacherList.add(new Teacher("Urszula", "Ulik", 30, "Ulik@gmail.com", "Chemistry"));
        return teacherList;
    }

    private void addTeachersAndStudentsToInsideList () {
        String selectAllStudents = "SELECT s from Student s";
        Query query1 = session.createQuery(selectAllStudents);
        List<Student> students = query1.getResultList();
        String selectAllTeachers= "SELECT t from Teacher t";
        Query query2 = session.createQuery(selectAllTeachers);
        List<Teacher> teachers = query2.getResultList();

        List <String> subjects = new ArrayList<>();

        subjects.add("IT");
        subjects.add("Chemistry");
        subjects.add("Physics");
        for (Teacher teacher: teachers){
            if (teacher.getSubject().equals(subjects.get(0))) PutInside(students, subjects, 0, teacher);
            if (teacher.getSubject().equals(subjects.get(1))) PutInside(students, subjects, 1, teacher);
            if (teacher.getSubject().equals(subjects.get(2))) PutInside(students, subjects, 2, teacher);
        }
        teachers.forEach(s -> session.persist(s));
    }

    private void PutInside(List<Student> studentList, List<String> subjects, int a, Teacher teacher) {
            for (Student student: studentList){
                if (student.getSubject().equals(subjects.get(a))){
                    teacher.addStudent(student);
              }
            }


    }

}
