package dev.joseph_free_code_camp.freecodecamp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAllByFirstnameContaining(String text);
    
}