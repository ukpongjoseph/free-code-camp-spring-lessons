package dev.joseph_free_code_camp.freecodecamp.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.joseph_free_code_camp.freecodecamp.Dto.SchoolDto;
import dev.joseph_free_code_camp.freecodecamp.Dto.SchoolResponseDto;
import dev.joseph_free_code_camp.freecodecamp.Services.SchoolService;

import java.util.List;
// import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class SchoolController {

    private SchoolService schoolService;
    public SchoolController(SchoolService schoolService){
        this.schoolService = schoolService;
    }


    @PostMapping("/schools")
    public SchoolResponseDto createSchool(
        @RequestBody SchoolDto school
    ){
       return schoolService.createAndSaveSchool(school);
    }

    @GetMapping("/schools")
    public List<SchoolResponseDto> findAll() {
        return schoolService.fetchAllSchools();
    }
    
}
