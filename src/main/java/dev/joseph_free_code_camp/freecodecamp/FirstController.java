package dev.joseph_free_code_camp.freecodecamp;

import java.util.List;

import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;


@RestController
// @RequestMapping(value = "/test")
public class FirstController {

    private final StudentRepository repository;

    public FirstController(StudentRepository studentRepository){
        this.repository = studentRepository;
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
    public Student createStudent(
        @RequestBody Student student
    ){
        if (student!=null) {
            return repository.save(student);
        }else{
            return null;
        }
    }

    @GetMapping(value = "/students")
    public List<Student> allStudents(){
        return repository.findAll();
    }

    @GetMapping(value = "/students/{student_id}")
    public Student findStudentById(
        @PathVariable("student_id") int id
    ){
        return repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Content Not Found"));
    }
    
    @GetMapping(value = "/students/search/{text}")
    public List<Student> findStudentByName(
        @PathVariable("text") String text
    ){
        return repository.findAllByFirstnameContaining(text);
    }

    @DeleteMapping(value = "/students/{id}")
    public void deleteStudentById(
        @PathVariable("id") int id
    ){
        repository.deleteById(id);
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
