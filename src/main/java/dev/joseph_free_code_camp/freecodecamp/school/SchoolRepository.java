package dev.joseph_free_code_camp.freecodecamp.school;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {
    
}
