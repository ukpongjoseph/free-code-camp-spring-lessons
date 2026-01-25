package dev.joseph_free_code_camp.freecodecamp.student;

import org.springframework.stereotype.Service;

import dev.joseph_free_code_camp.freecodecamp.school.School;

@Service
public class StudentMapper {
        public Student dtoToStudent(StudentDto dto){
        Student student = new Student();
        School school = new School();
        school.setId(dto.schoolId());
        student.setFirstname(dto.firstname());
        student.setLastname(dto.lastname());
        student.setEmail(dto.email());
        student.setSchool(school);
        return student;
    }

    public StudentResponseDto studentResponseDto(Student student){
        return new StudentResponseDto(student.getFirstname(), student.getLastname(), student.getEmail());
    }
}
