package com.example.demo.student;

import com.example.demo.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/student2")
public class StudentController {
    private final StudentService studentService;
    private final StudentDataAccessService studentDataAccessService;



    @Autowired
    public StudentController(StudentService studentService, StudentDataAccessService studentDataAccessService) {
        this.studentService = studentService;
        this.studentDataAccessService = studentDataAccessService;
    }


    @GetMapping
    public List<Student> getStudents() {
        return studentDataAccessService.selectAllStudents();
    }
    @GetMapping(path = "{studentId}/courses")
    public List<StudentCourse> getCourseInfoAboutStudents(@PathVariable("studentId") Long id) {
        return studentDataAccessService.getAllCoursesForStudent(id);
    }

    @PostMapping
    public void addNewStudent(@RequestBody @Validated Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteUserById(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long id,
            @RequestParam(required = false) String newName,
            @RequestParam(required = false) String newEmail) {

        studentService.updateUserById(id, newName, newEmail);
    }

}
