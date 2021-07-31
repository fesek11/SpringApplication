package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping(path = "v1/student2")
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

    @GetMapping(path = "{studentId}")
    public List<StudentCourse> getAllCoursesForStudent(Long studentId) {
        String sql = "SELECT " +
                " student2.student_id," +
                " course.course_id," +
                " course.name," +
                " course.description," +
                " course.department," +
                " course.teacher_name," +
                " student_course.start_date," +
                " student_course.end_date," +
                " student_course.grade " +
                "FROM student2 " +
                "JOIN student_course USING (student_id) " +
                "JOIN course USING (course_id) " +
                "WHERE student2.student_id = ?";
        return jdbcTemplate.query(sql, new Object[]{studentId}, mapAllInfoAboutStudentFromDb());
    }

    int insertStudent(Student student) {
        String sql = "" +
                "INSERT INTO student2 (" +
                " student_id," +
                " first_name," +
                " email," +
                " dob," +
                " age)"
                + "VALUES (?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getDob(),
                student.getAge());
    }

    private RowMapper<StudentCourse> mapAllInfoAboutStudentFromDb() {
        return (resultSet, i) -> new StudentCourse(
            resultSet.getLong("student_id"),
            resultSet.getLong("course_id"),
            resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getString("department"),
                resultSet.getString("teacher_name"),
                resultSet.getDate("start_date").toLocalDate(),
                resultSet.getDate("end_date").toLocalDate(),
                Optional.ofNullable(resultSet.getString("grade")).map(Integer::parseInt).orElse(null)
        );

    }

    private RowMapper<Student> mapStudentFromDb() {
        return (resultSet, i) -> {
            Long studentIdStr = resultSet.getLong("student_id");
            String firstName = resultSet.getString("first_name");
            String email = resultSet.getString("email");
            Integer age = resultSet.getInt("age");
            return new Student(studentIdStr, firstName, email, age );
        };
    }
}
