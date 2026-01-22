package dev.joseph_free_code_camp.freecodecamp.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.joseph_free_code_camp.freecodecamp.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAllByFirstnameContaining(String text);
    
}