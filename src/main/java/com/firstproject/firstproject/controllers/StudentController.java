package com.firstproject.firstproject.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.firstproject.firstproject.dto.StudentDto;
import com.firstproject.firstproject.entities.Student;
import com.firstproject.firstproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ObjectMapper mapper;
    private Student userDtoToUser(StudentDto studentDto){
        Student st = new Student();
        st.setName(studentDto.getName());
        st.setCourse(studentDto.getCourse());
        return st;
    }
    private StudentDto userToUserDto(Student student){
        StudentDto st = new StudentDto();
        st.setId(student.getId());
        st.setName(student.getName());
        st.setCourse(student.getCourse());
        return st;
    }
    @PostMapping("/add_student")
    public ResponseEntity<JsonNode> addStudent(@RequestBody StudentDto st){
        try{
            Student student = this.userDtoToUser(st);
            studentRepository.save(student);
            ObjectNode response = mapper.createObjectNode();
            response.put("success",true);
            response.put("message","Student Created Successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            ObjectNode response = mapper.createObjectNode();
            response.put("success",false);
            response.put("message","Error While Creating Student.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/get_students")
    public ResponseEntity<List<StudentDto>> getStudents(){
        try{
            List<Student> allStudents = studentRepository.findAll();
            List<StudentDto> convertedList = allStudents.stream().map(student->this.userToUserDto(student)).toList();
            return new ResponseEntity<>(convertedList,HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
