package com.dailycodebuffer.springdatajpatutorial.repository;

import com.dailycodebuffer.springdatajpatutorial.entity.Course;
import com.dailycodebuffer.springdatajpatutorial.entity.Student;
import com.dailycodebuffer.springdatajpatutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher = Teacher.builder().firstName("Ankan").build();
        Course course = Course.builder().teacher(teacher).build();
        courseRepository.save(course);

    }
    @Test
    public void findAllPagintion(){
        Pageable firstPageWithThreeRecords = PageRequest.of(0,3);
        Pageable secondPageWithTwoRecords = PageRequest.of(1,2);
        List<Course> courses = courseRepository.findAll(firstPageWithThreeRecords).getContent();
        long totalElements = courseRepository.findAll(firstPageWithThreeRecords).getTotalElements(); // 5
        long totalPages = courseRepository.findAll(firstPageWithThreeRecords).getTotalPages(); // 3
    }

    @Test
    public void findAllSorting(){
       Pageable sortByTitle = PageRequest.of(0,2, Sort.by(("title")));
       Pageable sortByCreditDesc = PageRequest.of(0,2, Sort.by("credit").descending());
       Pageable sortByTitleAndCreditDesc = PageRequest.of(0,2,Sort.by("title").descending().and(Sort.by("credit")));
       List<Course> courses = courseRepository.findAll(sortByTitleAndCreditDesc).getContent();

    }
    @Test
    public void printByTitleContaining(){
        Pageable firstPageTenRecords =
                PageRequest.of(0,10);
        List<Course> courses = courseRepository.findByTitleContaining("D",firstPageTenRecords).getContent();
    }
    @Test
    public void saveCourseWithStudentAndTeacher(){
        Teacher teacher = Teacher.builder().firstName("Ankan").lastName("Sample").build();
        Student student = Student.builder().firstName("Abhishek").emailId("abc").build();
       Course course = Course.builder().courseTitle("yay").credit(12).teacher(teacher).build();
       course.addStudents(student);

    }
}