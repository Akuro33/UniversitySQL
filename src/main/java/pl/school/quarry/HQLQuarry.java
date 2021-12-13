package pl.school.quarry;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import pl.school.entity.Student;
import pl.school.memory.StudentInMemory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static pl.school.memory.StudentInMemory.compareByField;

@Repository
@Transactional
public class HQLQuarry {

    @Autowired
    private EntityManager entityManager;


    public List<Student> cos (String firstName, String lastName, String sort, Integer page, Integer size) {
        String select = "select s from Student s";
        boolean and = false;

        if (firstName != null || lastName != null) select = select+" where ";
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

        Query query = entityManager.createQuery(select);
        List <Student> students = query.getResultList();
        return students.stream().sorted((user1, user2) -> compareByField(user1, user2, sort))
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());
    }


    /*

     */
}
