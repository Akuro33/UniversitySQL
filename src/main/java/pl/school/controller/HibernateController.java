package pl.school.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.school.configurate.HibernateConfigurator;
import pl.school.entity.Student;
import pl.school.entity.Teacher;
import pl.school.quarry.HQLQuarry;


import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping ("/api")
public class HibernateController {

    @Autowired
    private HQLQuarry hqlQuarry;

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateConfigurator.class);

    @RequestMapping("/students")
    public Collection<Student> getStudents (
            @RequestParam(defaultValue = "") String filter,
            @RequestParam(name = "firstname", required = false) String firstName,
            @RequestParam(name = "lastname", required = false) String lastName,
            @RequestParam(name = "sort", defaultValue = "id") String sort,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(name = "teacher", required = false) String teacher
    ) {
        LOGGER.info("filter: '{}'; sort: '{}', page: {}, size: {}", filter, sort, page, size);
        return hqlQuarry.getStudentsListSortedAndFiltered(firstName, lastName, sort, page, size, teacher);
    }
    @RequestMapping("/teachers")
    public List<Teacher> getTeachers (
            @RequestParam(defaultValue = "") String filter,
            @RequestParam(name = "firstname", required = false) String firstName,
            @RequestParam(name = "lastname", required = false) String lastName,
            @RequestParam(name = "sort", defaultValue = "id") String sort,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(name = "teacher", required = false) String teacher
    ) {
        LOGGER.info("filter: '{}'; sort: '{}', page: {}, size: {}", filter, sort, page, size);
        return hqlQuarry.getTeacherListSortedAndFiltered(firstName, lastName, sort, page, size, teacher);
    }


// @RequestParam(name = "teachers") boolean showTeachers
// @RequestParam(name = "students") boolean showStudents
}
