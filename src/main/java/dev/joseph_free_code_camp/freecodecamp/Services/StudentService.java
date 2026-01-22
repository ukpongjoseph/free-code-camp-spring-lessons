package dev.joseph_free_code_camp.freecodecamp.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.joseph_free_code_camp.freecodecamp.Dto.StudentDto;
import dev.joseph_free_code_camp.freecodecamp.Dto.StudentResponseDto;
import dev.joseph_free_code_camp.freecodecamp.Entity.Student;
import dev.joseph_free_code_camp.freecodecamp.Mappers.StudentMapper;
import dev.joseph_free_code_camp.freecodecamp.Repositories.StudentRepository;

@Service
public class StudentService {
    
    private final StudentRepository repository;

    private final StudentMapper studentMapper;

    public StudentService(StudentRepository repository, StudentMapper mapper){
        this.studentMapper = mapper;
        this.repository = repository;
    }

    public StudentResponseDto saveStudent(StudentDto student){
        Student studentResponse = repository.save(studentMapper.dtoToStudent(student));
        return studentMapper.studentResponseDto(studentResponse);
    }

    public List<StudentResponseDto> fetchAllStudents(){
        return repository
            .findAll()
            .stream()
            .map((item) -> studentMapper.studentResponseDto(item))
            .collect(Collectors.toList());
    }

    public StudentResponseDto fetchStudentById(int id){
        Student foundStudent = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content Not Found"));
        return studentMapper.studentResponseDto(foundStudent);
    } 

    public List<StudentResponseDto> fetchStudentByNamme(String querytext){
        return repository
            .findAllByFirstnameContaining(querytext)
            .stream()
            .map((item) -> studentMapper.studentResponseDto(item))
            .collect(Collectors.toList());
    }

    public void deleteAStudent(int id){
        repository.deleteById(id);
    }
}
