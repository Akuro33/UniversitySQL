package pl.school.quarry;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.school.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;



@Repository
@Transactional
public class HQLQuarry {

    @Autowired
    private EntityManager entityManager;


    public List<Student> getAllStudents () {
        String select = "select s from Student s";
        Query query = entityManager.createQuery(select);
        List <Student> studentDTOS = query.getResultList();
        return studentDTOS;
    }

    public List<Student> cos (String firstName, String lastName, String sort, Integer page, Integer size, String teacher) {
        String select = "select s from Student s";
        boolean and = false;

        if (firstName != null || lastName != null || teacher != null) select = select+" where ";
        if (firstName != null)  {
            select = select + "s.firstName='"+firstName+"'";
            and = true;
        }
        if (lastName != null) {
            if (and == true) {
                select = select = " and ";
                and = false;
            }
            select = select + "s.lastName='"+lastName+"'";
            and = true;
        }
        if (teacher != null) {
            searchByTeacher(teacher, and, select);
        }
        Query query = entityManager.createQuery(select);
        List <Student> students = query.getResultList();
        return students.stream().sorted((user1, user2) -> compareByField(user1, user2, sort))
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());
    }

    private void searchByTeacher(String teacher, Boolean and, String select) {
        if (and == true) {
            select = select = " and ";
            and = false;
        }
        select = select + "='"+teacher+"'";
        and = true;

        Query query = entityManager.createQuery(select);
    }

    public int compareByField(Student student1, Student student2, String fieldName) {
        switch (fieldName) {
            case "id": return student1.getIdStudent().compareTo(student2.getIdStudent());
            case "firstName" : return student1.getFirstName().compareTo(student2.getFirstName());
            case "lastName" : return student1.getLastName().compareTo(student2.getLastName());
            case "age" : return student1.getAge().compareTo(student2.getAge());
            case "email" : return student1.getEmail().compareTo(student2.getEmail());
            case "subject" : return student1.getSubject().compareTo(student2.getSubject());
            default: return 0;
        }
    }


}
