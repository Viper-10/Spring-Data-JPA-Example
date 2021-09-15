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

		logger.info("\nStudents under G1 : {}\n", studentRepository.findByGuardianEmail("G1@gmail.com"));
		logger.info("\nStudents containing \"dharsh\" in their first name : {}\n", studentRepository.findByFirstNameContaining("dharsh"));
		logger.info("\nStudents having last name : {}\n", studentRepository.findByLastNameNotNull());
		logger.info("\nStudents with guardian email G1@gmail.com : {}\n", studentRepository.getGuardianEmail_JPQLVersion("G1@gmail.com"));
		logger.info("\nStudents' first name with guardian email G1@gmail.com : {}\n", studentRepository.getFirstNameGivenGuardianEmail_JPQLVersion("G1@gmail.com"));
		logger.info("\nStudents name with guardian email S1@gmail.com : {}\n", studentRepository.getFirstNameGivenStudentEmail_NativeQueryMethod("S1@gmail.com"));
		logger.info("\nUpdating the first name of s1 from Beepee to Sara and second name from Ravichandran to Vijayakumar\n");
		studentRepository.updateFirstNameGivenStudentEmail_NativeQueryMethod("S1@gmail.com", "Sara", "Vijayakumar");
		logger.info("\nAll students : {}\n", studentRepository.findAll());
	}

}