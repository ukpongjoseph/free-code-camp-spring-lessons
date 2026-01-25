package dev.joseph_free_code_camp.freecodecamp.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void studentResponseDtoTest(){
        // Here we are converting from a Student object to a studentResponseDto. We will create a student object that will serve as a test case for this test
        Student student = new Student("Abaga", "Michael", 34, "mike@gmail.com");
        // After creating the student object, we will create the actual studentResponseDto using the mapper method
        StudentResponseDto studentResponse = studentMapper.studentResponseDto(student);
        // After creating the actual value, we will be creating the expected studentResponse using our manual method
        // First is to declare a StudentResponseDto instance
        StudentResponseDto responseDto;
        // We then analyze what should be contained in the StudentResponseDto. It should include firstname, lastname, email. All these values will be gottend from the Student object using getters
        responseDto = new StudentResponseDto(student.getFirstname(), student.getLastname(), student.getEmail());
        // Now that we have both the expected value (studentResponse) and the actual value (responseDto), we will compare using the assertEqual method
        assertEquals(studentResponse, responseDto);
    }

    // The test below is to check or test for exception, precisely a nullPointerException when a null dto is passed to the studentMapper
    @Test
    public void dtoToStudentNullPointerExceptionTest(){
        // In this test, we plan to recieve a null dto as test case, pass it to the studentMapper method to get an Exception (NullPointer Eception) as the expected response. We then plan to compare this expected outcome with a custom NullPointer exception class via the assertThrows method which expects two parameters, the expected type (In this case a nullPointerException.class) and the executable (more like a function and this can be achieved using a lambda expression or a functional interface)
        assertThrows(NullPointerException.class, () -> studentMapper.dtoToStudent(null));

    }
}
