package com.springdatajpa.example.student;

import com.springdatajpa.example.entity.Guardian;
import com.springdatajpa.example.entity.Student;
import com.springdatajpa.example.respoistories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void OnSave_CheckIfStudentIsSaved_withoutGuardian(){
        studentRepository.save(Student.builder()
                .emailId("abcd@gmail.com")
                .firstName("Priyadharshan")
                .lastName("Ravichandran")
//                .guardianEmail("xyz@gmail.com")
//                .guardianName("Snake")
//                .guardianMobile("8234")
                .build()
        );

//        assertEquals(1, studentRepository.findAll().size());
    }

    @Test
    public void OnSave_CheckIfStudentIsSaved_withGuardian(){
        Guardian guardian = Guardian.builder().mobile(82934).name("Tom").email("xyz@gmail.com").build();

        studentRepository.save(Student.builder()
                .emailId("pd@gmail.com")
                .firstName("Beepee")
                .lastName("Ravichandran")
                .guardian(guardian)
                .build()
        );

    }
}