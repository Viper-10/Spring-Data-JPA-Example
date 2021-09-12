package com.springdatajpa.example.respoistories;

import com.springdatajpa.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public List<Student> getByGuardianEmail(String email);
    public List<Student> getByFirstNameContaining(String name);
    public List<Student> getByLastNameNotNull();
}
