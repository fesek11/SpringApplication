package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JANUARY;
import static java.time.Month.OCTOBER;

//@Configuration
public class StudentConfig {
//    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student den = new Student(
                    "Den",
                    "dfesek1@gmail.com",
                    LocalDate.of(2005, JANUARY, 23));
            Student alex = new Student(
                    "Alex",
                    "alex1@gmail.com",
                    LocalDate.of(2004, JANUARY, 23));
            Student flex = new Student(
                    "Flex",
                    "flex1@gmail.com",
                    LocalDate.of(2001, OCTOBER, 8));
            studentRepository.saveAll(List.of(den, alex, flex));
        };
    }

}
