package pl.school.memory;

import org.springframework.stereotype.Repository;
import pl.school.entity.Student;
import pl.school.entity.Teacher;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class StudentInMemory {

    private final Map<Integer, Student> students = new HashMap<>();

    public StudentInMemory() {
        populateStudents();
    }

    public Collection<Student> findAll(String filter, String sort, Integer page, Integer size) {
        return students.values().stream()
                .filter(user -> userPlainText(user).contains(filter.toLowerCase()))
                .sorted((user1, user2) -> compareByField(user1, user2, sort))
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());
    }

    // Metoda powinna być bardziej rozbudowana, jednak jest ona w celach testowych
    private String userPlainText(Student user) {
        String plainText = user.getFirstName() + user.getLastName() + user.getEmail() + user.getSubject() + user.getAge();
        return plainText.toLowerCase();
    }
    private int compareByField(Student student1, Student student2, String fieldName) {
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

    private void populateStudents() {
        students.put(1, new Student(1, "Adam", "Adamowicz", 19, "Liam2312@gmail.com", "IT"));
        students.put(1, new Student(2, "Beata", "Brandicz", 20, "Brandicz@gmail.com", "IT"));
        students.put(1, new Student(3, "Celina", "Cekarska", 21, "Cekarska12@gmail.com", "IT"));
        students.put(1, new Student(4, "Daniel", "Dwakowski", 19, "Dwakowski@gmail.com", "IT"));
        students.put(1, new Student(5, "Eryk", "Ezkiel", 20, "Ezkiel@gmail.com", "IT"));
        students.put(1, new Student(6, "Fiona", "Frankowska", 21, "Frankowska@gmail.com", "IT"));
        students.put(1, new Student(7, "Gabriel", "Grzanowsi", 19, "Grzanowsi@gmail.com", "IT"));
        students.put(1, new Student(8, "Horacy", "Hyundai", 20, "Hyundai@gmail.com", "IT"));
        students.put(1, new Student(9, "Iga", "Ineczko", 21, "Ineczko@gmail.com", "IT"));
        students.put(1, new Student(10, "Juliam", "Juwin", 22, "Juwin@gmail.com", "IT"));
        students.put(1, new Student(11, "Karol", "Krawczyk", 19, "Krawczyk@gmail.com", "IT"));
        students.put(1, new Student(12, "Liam", "Lorency", 20, "Lorency@gmail.com", "IT"));
        students.put(1, new Student(13, "Monika", "Manerska", 21, "Manerska@gmail.com", "IT"));
        students.put(1, new Student(14, "Norbert", "Nawarski", 22, "Nawarski@gmail.com", "IT"));
        students.put(1, new Student(15, "Ola", "Orkisz", 19, "Orkisz@gmail.com", "IT"));
        students.put(1, new Student(16, "Piotr", "Pawlewski", 20, "Pawlewski@gmail.com", "IT"));
        students.put(1, new Student(17, "Radek", "Rodecki", 21, "Rodecki@gmail.com", "IT"));
        students.put(1, new Student(18, "Sylwanas", "Sandrowska", 19, "Sandrowska@gmail.com", "IT"));
        students.put(1, new Student(19, "Tomek", "Tamczyk", 20, "Tamczyk@gmail.com", "IT"));

    }
}


