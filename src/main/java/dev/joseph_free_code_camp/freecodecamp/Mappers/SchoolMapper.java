package dev.joseph_free_code_camp.freecodecamp.Mappers;

import org.springframework.stereotype.Service;

import dev.joseph_free_code_camp.freecodecamp.Dto.SchoolDto;
import dev.joseph_free_code_camp.freecodecamp.Dto.SchoolResponseDto;
import dev.joseph_free_code_camp.freecodecamp.Entity.School;

@Service
public class SchoolMapper {
    public School requestBody(SchoolDto schoolDto){
        School school = new School();
        school.setName(schoolDto.name());
        return school;   
    }

    public SchoolResponseDto schoolResponseDto(School school){
        return new SchoolResponseDto(school.getName());
    }
}
