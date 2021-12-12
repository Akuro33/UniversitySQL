package pl.school.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api")
public class HibernateController {

// Powinna być możliwość wyświetlenia wszystkich studentów oraz wszystkich nauczycieli (dwa endpointy, możliwość stronicowania i sortowania).
    @RequestMapping("/getStudents")
    @ResponseBody
    public String getStudents () {


        return "Students";
    }
}
