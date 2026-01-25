package dev.joseph_free_code_camp.freecodecamp.school;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class SchoolService {
    private SchoolRepository schoolRepository;
    private SchoolMapper schoolMapper;

    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    public SchoolResponseDto createAndSaveSchool(SchoolDto school){
        School newSchool = schoolRepository.save(schoolMapper.requestBody(school));
        return schoolMapper.schoolResponseDto(newSchool);
    }

    public List<SchoolResponseDto> fetchAllSchools(){
       return schoolRepository.
            findAll()
            .stream()
            .map(schoolMapper::schoolResponseDto)
            .collect(Collectors.toList());
    }
}
