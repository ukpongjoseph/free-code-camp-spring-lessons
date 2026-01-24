package dev.joseph_free_code_camp.freecodecamp.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.server.ResponseStatusException;

import dev.joseph_free_code_camp.freecodecamp.Dto.StudentDto;
import dev.joseph_free_code_camp.freecodecamp.Dto.StudentResponseDto;
import dev.joseph_free_code_camp.freecodecamp.Services.StudentService;
import jakarta.validation.Valid;


@RestController
// @RequestMapping(value = "/test")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    

    @GetMapping
    public String sayHello(){
        return "Hello from here";
    }

    @GetMapping("/2")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String sayHello2(){
        return "Hello 2";
    }

    @GetMapping("/error")
    public String displayError(){
        return "Error page";
    }

    @PostMapping("/create")
    public String create(
        @RequestBody String message
    ){
        return "Your request body as a string is :" + message;
    }

    @PostMapping(value = "/students/new")
    public StudentResponseDto createStudent(
        @Valid  @RequestBody StudentDto student
    ){
        if (student!=null) {
            return studentService.saveStudent(student);
        }else{
            return null;
        }
    }

    @GetMapping(value = "/students")
    public List<StudentResponseDto> allStudents(){
        return studentService.fetchAllStudents();
    }

    @GetMapping(value = "/students/{student_id}")
    public StudentResponseDto findStudentById(
        @PathVariable("student_id") int id
    ){
        return studentService.fetchStudentById(id);
    }
    
    @GetMapping(value = "/students/search/{text}")
    public List<StudentResponseDto> findStudentByName(
        @PathVariable("text") String text
    ){
        return studentService.fetchStudentByNamme(text);
    }

    @DeleteMapping(value = "/students/{id}")
    public void deleteStudentById(
        @PathVariable("id") int id
    ){
        studentService.deleteAStudent(id);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> exceptionHandler(MethodArgumentNotValidException err){
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Invalid request : " + err.getMessage());
    }

























    // @PostMapping("/create-order")
    // public String create_order(
    //     @RequestBody Order order
    // ){
    //     return order.toString();
    // }

    // @PostMapping("/create-order-record")
    // public String create_order_record(
    //     @RequestBody OrderRecord record
    // ){
    //     return record.toString();
    // }

    // @GetMapping("/test-dynamic/{user-name}")
    // public String returnUser(
    //     // Since dynamic parameter name is not matching with the method parameter name, we pass the dynamic parameter name to the @PathVariable annotation for proper mapping
    //     @PathVariable("user-name") String userName
    // ){
    //     return "My name is : " + userName;
    // }

    // @GetMapping("/test-request")
    // public String getMethodName(
    //     @RequestParam("user-name") String name,
    //     @RequestParam("user-age") String age
    // ) {
    //     return "My name is : " + name + " while i am : " + age + " years old";
    // }
    
}
