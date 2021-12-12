package pl.school.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.school.configurate.HibernateConfigurator;
import pl.school.entity.Student;
import pl.school.memory.StudentInMemory;

import java.util.Collection;

@RestController
@RequestMapping ("/students")
public class HibernateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateConfigurator.class);

    private final StudentInMemory studentInMemory;

    public HibernateController(StudentInMemory studentInMemory) {
        this.studentInMemory = studentInMemory;
    }

    // 1. Obie klasy można tworzyć, usuwać i edytować. Dane powinny być walidowane: poprawny email, imię dłuższe od dwóch liter, wiek > 18.
//2. Powinna być możliwość wyświetlenia wszystkich studentów oraz wszystkich nauczycieli (dwa endpointy, możliwość stronicowania i sortowania).
//3. Dane można filtrować: wyszukać wszystkich studentów danego nauczyciela i odwrotnie.
//4. Studentów oraz nauczycieli można wyszukiwać po imieniu i nazwisku.

    @RequestMapping("/test")
    @ResponseBody
    public String test () {
        return "Students";
    }

/*    public Collection<Student> getStudents (
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "LastName", required = false) String lastName,
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "subject", required = false) String subject,
            @RequestParam(name = "sort", defaultValue = "id") String sort,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
) {
        LOGGER.info("filter: '{}'; sort: '{}', page: {}, size: {}", filter, sort, page, size);
        LOGGER.info("Client header: {}", headers.containsKey("Client") ? headers.get("Client") : '-');


        LOGGER.info("id: '{}',   sort: '{}', page: {}, size: {}", id, firstName, lastName, age, email, subject);
        LOGGER.info("Client header: {}", headers.containsKey("Client") ? headers.get("Client") : '-');
        return null;
    }*/
    public Collection<Student> getStudents (
            @RequestParam(defaultValue = "") String filter,
            @RequestParam(name = "sort", defaultValue = "id") String sort,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        LOGGER.info("filter: '{}'; sort: '{}', page: {}, size: {}", filter, sort, page, size);
        return studentInMemory.findAll(filter, sort, page, size);
    }

}
