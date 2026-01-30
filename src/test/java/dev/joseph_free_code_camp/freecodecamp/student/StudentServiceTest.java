package dev.joseph_free_code_camp.freecodecamp.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
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
        // Initializing all our Mock Objects
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
        when(studentMapper.dtoToStudent(dto)).thenReturn(student1);
        // taking the student object and saving it in the db,this method returns a student object
        when(studentRepository.save(student1)).thenReturn(student1);
        // returning a mock responseDto from the saved student object
        when(studentMapper.studentResponseDto(student1)).thenReturn(new StudentResponseDto("Joseph", "Aniekan", "anie@gmail.com"));
        
        // Real responseDto
        StudentResponseDto responseDto = studentService.saveStudent(dto);
        assertEquals(dto.firstname(), responseDto.firstname());

        verify(studentMapper, (times(1))).studentResponseDto(student1);
        verify(studentMapper, times(1)).dtoToStudent(dto);
        verify(studentRepository, times(1)).save(student1);

    }

    @Test
    public void findAllStudentsTest(){
        // Mockito mocks method call...What does this mean?....it means that we can intercept a method and return a controlled output or a defined one. Say we are testing a service, this service needs other dependency, we will mock this dependencies and inject them into the service we are testing. Such that when the tested service perform a method call, at every instance where the dependencies are needed, the mock comes in and do its job and then return a defined and controlled output (since it is a mock call). When the real method is called, it uses the mock and returns a value. This value from the real method call is compared to the expected return value from what the mock is progreammed or told to return

        // Find All students will first find all students, return a list of student and then map through it to return a list of studentResponseDto

        // We generate our own student List returned by findAll()
        List<Student> students = new ArrayList<>();
        students.add(new Student("Joseph", "Aniekan", 24, "joe@gmail.com"));

        // We generate our own StudentresponseDto List returned by the stream and mapping 
        StudentResponseDto stud1 = new StudentResponseDto("Joseph", "Aniekan", "joe@gmail.com");
        List<StudentResponseDto> studentResponseDtos = Arrays.asList(stud1);

        // We start creating our mocks. The process of fetching All students returns a List of Students which is what the mock method is returning
        when(studentRepository.findAll()).thenReturn(students);
        // Then the Student List returned is passed through a stream, mapped through, each item is converted to StudentResponseDto and then collected to a List. The only part of all this process is the conversion which we duplicated below and returned a StudentResponseDto as seen below. which is collected and stored in the actualStudentresponseDtos(which is what mocking is all about). The processof streaaming, mapping and collection is not covered with the mockito.when method since no mock dependency is involved.
        // The mock method is saying when this method is called, (studentMapper.studentResponseDto(student)). perform or call then function in thenAnswer using the argument recived with the aid of invocation
        when(studentMapper.studentResponseDto(any(Student.class))).thenAnswer(invocation -> {
            // we use invocation to fetch the argument passed
            Student student = invocation.getArgument(0);
            return new StudentResponseDto(student.getFirstname(), student.getLastname(), student.getEmail());
        });
        

        List<StudentResponseDto> actualStudentResponseDtos = studentService.fetchAllStudents();

        assertEquals(studentResponseDtos.size(), actualStudentResponseDtos.size());
        assertEquals(studentResponseDtos.getLast(), actualStudentResponseDtos.getLast());
        assertEquals(studentResponseDtos.get(0), actualStudentResponseDtos.get(0));
        assertEquals(studentResponseDtos.get(0).email(), actualStudentResponseDtos.get(0).email());

        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void findAStudentByIdTest(){
        // We would first Find the student By Id, this will return a optional student object. This student object is converted to studentResponseDto via the mapper

        // The controlled output for repository.findStudentByid
        Student expecStudent = new Student("Joseph", "Aniekan", 24, "anie@gmail.com");
        // The expected return value of the service method call
        StudentResponseDto exResponseDto = new StudentResponseDto(expecStudent.getFirstname(), expecStudent.getLastname(), expecStudent.getEmail());

        // When the repository find by id, it returns an optional student object which is why we use the Optional.of(studentObject) in the mocked method return value
        when(studentRepository.findById(1)).thenReturn(Optional.of(expecStudent));
        // The optional student object is then passed to the mapper
        when(studentMapper.studentResponseDto(expecStudent)).thenReturn(exResponseDto);

        // when we run this method, we inject mock and use the controlled output returned by the mock to give this method call a value
        StudentResponseDto actualStudent = studentService.fetchStudentById(1);

        assertEquals(exResponseDto.email(), actualStudent.email());

        verify(studentRepository, times(1)).findById(1); 
    }

    @Test
    public void findStudentByNameTest(){
        // This test involves the studentrepository findAStudentName by recieving a query text, return a List of student object. These stuednt object is then converted to a studentResponseDto object via the mapper when this List is mapped

        // This is our control output for when our mock studentRepository findAStudentByName . This control output is passed to the mapper to get a responseDto wihich will be compared to the expected responseDto
        List<Student> student = Arrays.asList(new Student("Joseph", "Aniekan", 24, "joe@gmail.com"));

        // The expected responseDto
        List<StudentResponseDto> expectedResponseDto = Arrays.asList(new StudentResponseDto("Joseph", "Aniekan", "joe@gmail.com"));

        when(studentRepository.findAllByFirstnameContaining("Joseph")).thenReturn(student);
        when(studentMapper.studentResponseDto(any(Student.class))).thenAnswer(invocator ->{
            Student actualStudent = invocator.getArgument(0);
            return new StudentResponseDto(actualStudent.getFirstname(), actualStudent.getLastname(), actualStudent.getEmail());
        });

        assertEquals(expectedResponseDto.size(), expectedResponseDto.size());
        assertEquals(expectedResponseDto.get(0), expectedResponseDto.get(0));
        assertEquals(expectedResponseDto.get(0).firstname(), expectedResponseDto.get(0).firstname());
    }
}


































































































        // List<Student> students = new ArrayList<>();
        // students.add(new Student("Joseph", "Aniekan", 24, "anie@gmail.com"));
        // when(studentRepository.findAll()).thenReturn(students);
        // when(studentMapper.studentResponseDto(any(Student.class))).thenReturn(new StudentResponseDto("Joseph", "Aniekan", "anie@gmail.com"));

        // List<StudentResponseDto> studentResponseDtos = studentService.fetchAllStudents();

        // assertEquals(students.size(), studentResponseDtos.size());