package com.example.demo.student;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;

@Table(name = "Student")
@Entity
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            initialValue=32,
            allocationSize = 1)
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "student_sequence")
    private Long id;

    @Column(name = "firstName",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true)
    private String name;

    @Column(name = "email",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true)
    private String email;

    private LocalDate dob = LocalDate.now();

    @Transient
    @Column(name = "age",
            columnDefinition = "TEXT")
    private Integer age;

    public Student(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Student(@JsonProperty("student_sequence") Long studentIdStr, @JsonProperty("firstName") String firstName, @JsonProperty("email") String email, @JsonProperty("age") Integer age) {
        this.id = studentIdStr;
        this.name = firstName;
        this.email = email;
        this.age = age;
    }

    public Student() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
