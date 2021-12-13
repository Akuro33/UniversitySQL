package pl.school.quarry;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.school.entity.Human;
import pl.school.entity.Student;
import pl.school.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;



@Repository
@Transactional
public class HQLQuarry {

    @Autowired
    private EntityManager entityManager;


    public List<Student> getStudentsListSortedAndFiltered(String firstName, String lastName, String sort, Integer page, Integer size, String teacher) {
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
            return searchByTeacher(teacher, and, select);
        }
        Query query = entityManager.createQuery(select);
        List <Student> students = query.getResultList();
        if (sort.equals("id"))  {
            Collections.sort(students, Comparator.comparing(Student::getIdStudent));
        }
        return students.stream().sorted((user1, user2) -> compareByField(user1, user2, sort))
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());
    }
    public List<Teacher> getTeacherListSortedAndFiltered(String firstName, String lastName, String sort, Integer page, Integer size, String student) {
        String select = "select s from Teacher s";
        boolean and = false;

        if (firstName != null || lastName != null || student != null) select = select+" where ";
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
        if (student != null) {
            return searchByStudent(student, and, select);
        }
        Query query = entityManager.createQuery(select);
        List <Teacher> resultList = query.getResultList();
        if (sort.equals("id"))  {
            Collections.sort(resultList, (t1, t2) -> {return t1.getIdTeacher().compareTo(t2.getIdTeacher());});
        }
        return resultList.stream().sorted((user1, user2) -> compareByField(user1, user2, sort))
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());
    }
//Dane można filtrować: wyszukać wszystkich studentów danego nauczyciela i odwrotnie.


    private List <Student> searchByTeacher(String teacher, Boolean and, String select) {
        String select1="select t from Teacher t where lastName='"+teacher+"'";
        Query query1 = entityManager.createQuery(select1);
        Teacher teacher1 = (Teacher) query1.getSingleResult();
        return teacher1.getStudentList();
    }
    private List <Teacher> searchByStudent(String student, Boolean and, String select) {
        String select1="select s from Student s where lastName='"+student+"'";
        Query query1 = entityManager.createQuery(select1);
        Student student1 = (Student) query1.getResultList();
        return student1.getTeacherList();
    }




    public int compareByField(Human student1, Human student2, String fieldName) {
        switch (fieldName) {
            case "firstName" : return student1.getFirstName().compareTo(student2.getFirstName());
            case "lastName" : return student1.getLastName().compareTo(student2.getLastName());
            case "age" : return student1.getAge().compareTo(student2.getAge());
            case "email" : return student1.getEmail().compareTo(student2.getEmail());
            case "subject" : return student1.getSubject().compareTo(student2.getSubject());
            default: return 0;
        }
    }
}
