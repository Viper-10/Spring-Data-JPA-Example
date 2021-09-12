package com.springdatajpa.example;

import com.springdatajpa.example.entity.Guardian;
import com.springdatajpa.example.entity.Student;
import com.springdatajpa.example.respoistories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataJpaDemoprojectApplication implements CommandLineRunner {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaDemoprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Guardian guardian = Guardian.builder()
				.email("G1@gmail.com")
				.name("Alex")
				.mobile(9873294782L)
				.build();

		Student s1 = Student.builder()
				.emailId("S1@gmail.com")
				.firstName("Beepee")
				.lastName("Ravichandran")
				.guardian(guardian)
				.build();

		Student s2 = Student.builder()
				.emailId("S2@gmail.com")
				.firstName("Priyadharshan")
				.lastName("Ravichandran")
				.guardian(guardian)
				.build();

		studentRepository.save(s1);
		studentRepository.save(s2);

		logger.info("\nStudents under G1 : {}\n", studentRepository.getByGuardianEmail("G1@gmail.com"));
		logger.info("\nStudents containing \"dharsh\" in their first name : {}\n", studentRepository.getByFirstNameContaining("dharsh"));
		logger.info("\nStudents having last name : {}\n", studentRepository.getByLastNameNotNull());
	}

}