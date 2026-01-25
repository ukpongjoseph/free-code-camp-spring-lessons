package dev.joseph_free_code_camp.freecodecamp.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.joseph_free_code_camp.freecodecamp.school.School;

// import dev.joseph_free_code_camp.freecodecamp.school.School;

public class StudentMapperTest {

    private StudentMapper studentMapper;

    private School school;

    @BeforeEach
    void init()
    {
        studentMapper = new StudentMapper();
        school = new School();
    }
    @Test
    public void dtoToStudentTest(){
        // Test Case data
        StudentDto dto = new StudentDto("Emmanuel", "Ubong", "emmaubong49@gmail.com", 1);
        // declaring our Student object to hold our expected value
        Student firstDto;
        // calling on method or unit to be tested to convert our dto to Student
        firstDto = studentMapper.dtoToStudent(dto);
        // creating a second Student Object to hold our actual value
        Student secondDto = new Student();
        // setting the value, firstName for the student object
        secondDto.setFirstname(dto.firstname());
        // setting the value, lastName for the student object
        secondDto.setLastname(dto.lastname());
        // setting the value, email for the student object
        secondDto.setEmail(dto.email());
        // We want to set SchoolId for the student object. First we need to create a school object, pass the school object to the student, then use the school object to set the school id from the dto, which is the test case data
        // passing the school object to the student
        secondDto.setSchool(school);
        // getting the school object from the student and then passing the schoolId to the school object 
        secondDto.getSchool().setId(dto.schoolId());
        assertEquals(firstDto.getFirstname(), secondDto.getFirstname());
        assertEquals(firstDto.getLastname(), secondDto.getLastname());
        assertEquals(firstDto.getEmail(), secondDto.getEmail());
        assertEquals(firstDto.getSchool().getId(), secondDto.getSchool().getId());
        assertNotNull(secondDto);
    }
}
