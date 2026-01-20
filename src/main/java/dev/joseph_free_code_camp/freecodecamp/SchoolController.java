package dev.joseph_free_code_camp.freecodecamp;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class SchoolController {
    private SchoolRepository schoolRepository;
    public SchoolController(SchoolRepository schoolRepository){
        this.schoolRepository = schoolRepository;
    }

    public School requestBody(SchoolDto schoolDto){
        School school = new School();
        school.setName(schoolDto.name());
        return school;   
    }

    public SchoolResponseDto schoolResponseDto(School school){
        return new SchoolResponseDto(school.getName());
    }

    @PostMapping("/schools")
    public SchoolResponseDto createSchool(
        @RequestBody SchoolDto school
    ){
        School schoolObject = schoolRepository.save(requestBody(school));
        return schoolResponseDto(schoolObject);
    }

    @GetMapping("/schools")
    public List<SchoolResponseDto> findAll() {
        return schoolRepository
            .findAll()
            .stream()
            .map((item)->schoolResponseDto(item))
            .collect(Collectors.toList());
    }
    
}
