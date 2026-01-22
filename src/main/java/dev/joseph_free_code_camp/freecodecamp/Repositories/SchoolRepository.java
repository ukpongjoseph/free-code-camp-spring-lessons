package dev.joseph_free_code_camp.freecodecamp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.joseph_free_code_camp.freecodecamp.Entity.School;

public interface SchoolRepository extends JpaRepository<School, Integer> {
    
}
