package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email is taken");
        }
        studentRepository.save(student);
    }

    public void deleteUserById(Long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if (!exist) {
            throw new IllegalStateException("There is no student with this ID");
        } else {
            studentRepository.deleteById(studentId);
        }
    }

    @Transactional
    public void updateUserById(Long id, String newName, String newEmail) {
        boolean exist = studentRepository.existsById(id);
        if (!exist) {
            throw new IllegalStateException("The id is not existing");
        } else {
            Student student = studentRepository
                    .findById(id)
                    .orElseThrow(() -> new IllegalStateException("Id: " + id + " is not existing"));
            if (newName != null && newName.length() > 0) {
                student.setName(newName);
            } else {
                throw new IllegalStateException("Inappropriate  name");
            }
            Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
            Matcher matcher = pattern.matcher(newEmail);
            if (matcher.matches()) {
                Optional<Student> studentOptional = studentRepository.findStudentByEmail(newEmail);
                if (studentOptional.isPresent()) {
                    throw new IllegalStateException("Email is taken");
                } else {
                    student.setEmail(newEmail);
                }
            } else {
                throw new IllegalStateException("Inappropriate  email");
            }
        }
    }
}
