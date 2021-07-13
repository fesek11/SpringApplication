package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(path = "v1/student2")
public class StudentDataAccessService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<Student> selectAllStudents() {
        String sql = "" +
                " SELECT" +
                " student_id," +
                " first_name," +
                " email," +
                " dob," +
                " age " +
                " FROM student2";
        return jdbcTemplate.query(sql, mapStudentFromDb());
    }

    private RowMapper<Student> mapStudentFromDb() {
        return (resultSet, i) -> {
            Long studentIdStr = resultSet.getLong("student_id");
            String firstName = resultSet.getString("first_name");
            String email = resultSet.getString("email");
//            Date dob = resultSet.getDate("dob");
            Integer age = resultSet.getInt("age");
            return new Student(studentIdStr, firstName, email, age);
        };
    }
}
