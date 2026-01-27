package dev.joseph_free_code_camp.freecodecamp.student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentMapper studentMapper;
    @Mock
    private StudentRepository studentRepository;
   
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveStudentTest(){
        // We plan to test the POST method of creating a new student. From the method in the service class, it can be seen that the POST method recives a StudentDto, converts it to a Student object, saves the Student object and returns a StudentResponseDto
        // We will first create a test case StudentDto called dto, then perform all necessary opefrations with as seen in the Student service class.
        StudentDto dto = new StudentDto("Joseph", "Aniekan", "anniejammie49@gmail.com", 1);
        // Creating a Student object with the dto
        Student student = studentMapper.dtoToStudent(dto);
        // Now that we have the Student object, we will generate a StudentResponseDto as response from saving the student details
        StudentResponseDto studentResponse = studentMapper.studentResponseDto(studentRepository.save(student));
        // Now that we have all expected values such as the responseDto and the studentObject gotten frm the dto, we will now create our actual values of type Student and StudentResponse Dto
        Student expectedStudent = new Student(dto.firstname(), dto.lastname(), 25, dto.email());
        StudentResponseDto expecStudentResponseDto = new StudentResponseDto(dto.firstname(), dto.lastname(), dto.email());
        // Now that both expected and actual are present, we will test and compare
        assertEquals(expectedStudent.getFirstname(), student.getFirstname());
    }
}
