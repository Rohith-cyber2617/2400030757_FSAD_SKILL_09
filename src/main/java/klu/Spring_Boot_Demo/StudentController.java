package klu.Spring_Boot_Demo;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    private static final List<Student> students = new ArrayList<>();

    static {
        students.add(new Student(1, "Lasyadeep", "CSE"));
        students.add(new Student(2, "Ravi", "ECE"));
        students.add(new Student(3, "Sita", "IT"));
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {

        if (id <= 0) {
            throw new InvalidInputException("ID must be positive");
        }

        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with ID: " + id));
    }
}