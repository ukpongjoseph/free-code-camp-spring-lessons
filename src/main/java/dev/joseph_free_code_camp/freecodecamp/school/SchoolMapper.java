package dev.joseph_free_code_camp.freecodecamp.school;

import org.springframework.stereotype.Service;

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
