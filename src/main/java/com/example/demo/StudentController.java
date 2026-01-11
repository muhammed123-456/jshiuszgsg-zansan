package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<?> getStudents() {
        System.out.println("Get request made");
        return ResponseEntity.ok(studentService.getStudents());
    }
    @PostMapping
    public ResponseEntity<?> createStudents( @RequestBody Student student){
        Student student1 = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student>getStudent(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getStudent(id));

    }
    @PatchMapping("{id}")
    public ResponseEntity<?>updateStudent(@RequestBody Student data,@PathVariable long id){
        try {
            return ResponseEntity.ok(studentService.updateStudent(data, id));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Message",e.getMessage()));
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?>deleteStudent(@PathVariable long id){
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(Map.of("Message","Student deleted successfully"));

        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(("Message"),e.getMessage()));
        }
    }
}
