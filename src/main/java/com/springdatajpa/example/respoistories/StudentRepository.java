package com.springdatajpa.example.respoistories;

import com.springdatajpa.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /*
     Link to see various methods to define for various queries.
     https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
      */

    public List<Student> findByGuardianEmail(String email);
    public List<Student> findByFirstNameContaining(String name);
    public List<Student> findByLastNameNotNull();

    // JPQL
    @Query("select s from Student s where s.guardian.email = ?1")
    public List<Student> getGuardianEmail_JPQLVersion(String email);

    // JPQL
    @Query("select s.firstName from Student s where s.guardian.email = ?1")
    public List<String> getFirstNameGivenGuardianEmail_JPQLVersion(String email);

    // native Query
    @Query(value = "SELECT * from tbl_student s where s.email_address = :email", nativeQuery = true)
    public Student getFirstNameGivenStudentEmail_NativeQueryMethod(@Param("email") String email);

    /*
    @Transactional
    Used on service methods that do a series of additions/updates  to the database. It creates a new
    Transaction for the method under @Transaction.If any one of those queries fail, then the whole transaction is rolled back as to how it was before
    the function under @Transactional was called.

    we have to use @Transactional on the modifying query method declaration as all user defined repository methods are read only by default

    @Modifying
    Must be used on all DML queries or else it'll prompt with error.
     */

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "Update tbl_student set first_name = :first_name, last_name = :last_name  where email_address = :email")
    public void updateFirstNameGivenStudentEmail_NativeQueryMethod(@Param("email") String email,@Param("first_name") String firstName,@Param("last_name") String lastName);


}

