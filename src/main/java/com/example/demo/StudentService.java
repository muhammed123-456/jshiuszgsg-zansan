package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    public Iterable<Student> getStudents() {
       return studentRepository.findAll();


    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student  updateStudent(Student data, long id) {
        Student student= studentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Student not found"));
                String name = data.getName();
                String email = data.getEmail();
                if(name!=null)student.setName(name);
                if(email!=null)student.setEmail(email);
                return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student not found"));
        studentRepository.delete(student);
    }
}
