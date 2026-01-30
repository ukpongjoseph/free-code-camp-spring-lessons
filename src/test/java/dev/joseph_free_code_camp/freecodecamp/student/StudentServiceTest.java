package dev.joseph_free_code_camp.freecodecamp.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
        // We plan to test the POST method of creating a new student. From the method in the service class, it can be seen that the POST method recives a StudentDto, converts it to a Student object, saves the Student object and returns a StudentResponseDto. We would create a mock and a real responseDto and then compare
        StudentDto dto = new StudentDto("Joseph", "Aniekan", "anie@gmail.com", 1);
        // Student student = new Student("Joseph", "Aniekan", 24, "anie@gmail.com");
        Student student1 = new Student("Joseph", "Aniekan", 24, "anie@gmail.com");
        
        // Creating mock responseDto
        // first is taking the dto object and converting it to a student object
        Mockito.when(studentMapper.dtoToStudent(dto)).thenReturn(student1);
        // taking the student object and saving it in the db,this method returns a student object
        Mockito.when(studentRepository.save(student1)).thenReturn(student1);
        // returning a mock responseDto from the saved student object
        Mockito.when(studentMapper.studentResponseDto(student1)).thenReturn(new StudentResponseDto("Joseph", "Aniekan", "anie@gmail.com"));
        
        // Real responseDto
        StudentResponseDto responseDto = studentService.saveStudent(dto);
        assertEquals(dto.firstname(), responseDto.firstname());

        verify(studentMapper, (times(1))).studentResponseDto(student1);
        verify(studentMapper, times(1)).dtoToStudent(dto);
        verify(studentRepository, times(1)).save(student1);

    }

    @Test
    public void findAllStudentsTest(){
        List<Student> students = new ArrayList<>();
        students.add(new Student("Joseph", "Aniekan", 24, "anie@gmail.com"));
        Mockito.when(studentRepository.findAll()).thenReturn(students);
        Mockito.when(studentMapper.studentResponseDto(any(Student.class))).thenReturn(new StudentResponseDto("Joseph", "Aniekan", "anie@gmail.com"));

        List<StudentResponseDto> studentResponseDtos = studentService.fetchAllStudents();

        assertEquals(students.size(), studentResponseDtos.size());
    }
}
