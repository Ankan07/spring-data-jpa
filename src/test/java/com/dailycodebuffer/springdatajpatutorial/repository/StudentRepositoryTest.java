package com.dailycodebuffer.springdatajpatutorial.repository;

import com.dailycodebuffer.springdatajpatutorial.entity.Guardian;
import com.dailycodebuffer.springdatajpatutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;
    @Test
    public void SaveStudent(){
        Student student = Student.builder().emailId("ankan@gmail.com")
                .firstName("ankan")
                //.guardianName("Nikhil")
                .build();
        studentRepository.save(student);
    }
    @Test
    public void printAllStudent(){
        List<Student> students = studentRepository.findAll();

    }
    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder().email("nikhil@gmail.com").build();
        Student student = Student.builder().guardian(guardian).build();
          studentRepository.save(student);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> students = studentRepository.findByFirstName("shivam");
        System.out.println("students = "+ students);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContaining("sh");
        System.out.println("students = "+ students);
    }
    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> students = studentRepository.findByGuardianName("Nikhil");
    }

    @Test
    public void printgetStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("ankan@gmail.com");
    }

    @Test
    public void printgetStudentFirstNameByEmailAddress(){
        String firstName = studentRepository.getStudentFirstNameByEmailAddress("ankan@gmail.com");
    }


    @Test
    public void printgetStudentFirstNameByEmailAddressNamedParam(){
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("ankan@gmail.com");
    }
    @Test
    public void printgetStudentFirstNameByEmailAddressNamedParamTransactional(){
       studentRepository.updateStudentNameByEmailId("ankan","ankan@gmail.com");
    }




}