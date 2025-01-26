package com.firstproject.firstproject.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.firstproject.firstproject.entities.Student;
import com.firstproject.firstproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ObjectMapper mapper;
    @PostMapping("/add_student")
    public ResponseEntity<JsonNode> addStudent(@RequestBody Student st){
        try{
            studentRepository.save(st);
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
    public ResponseEntity<JsonNode> getStudents(){
        try{
            List<Student> allStudents = studentRepository.findAll();
            ObjectNode response = mapper.createObjectNode();
            response.put("students",mapper.valueToTree(allStudents));
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            ObjectNode response = mapper.createObjectNode();
            response.put("success",false);
            response.put("message","Error While Fetching Students.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
