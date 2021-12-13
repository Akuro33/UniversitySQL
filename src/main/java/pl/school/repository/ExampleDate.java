package pl.school.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.school.entity.Student;
import pl.school.memory.StudentInMemory;

import javax.persistence.NoResultException;
import java.util.Collection;
import java.util.List;

public class ExampleDate {

    private Session session;

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
            addStudentsToDB();
        }
    }

    public void addStudentsToDB () {
        StudentInMemory studentInMemory = new StudentInMemory();
        List<Student> students = studentInMemory.giveAll();
        for (Student student: students){
            Student student1 = new Student();
            student1.setFirstName(student.getFirstName());
            student1.setLastName(student.getLastName());
            student1.setAge(student.getAge());
            student1.setSubject(student.getSubject());
            student1.setEmail(student.getEmail());
            session.persist(student1);
        }
    }
}
