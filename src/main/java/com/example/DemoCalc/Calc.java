package com.example.DemoCalc;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RestController
public class Calc {

    @GetMapping("/sum")
    public int sum(@RequestParam int a, @RequestParam int b){
        return a+b;
    }

    @GetMapping("/sub")
    public int sub(@RequestParam int a, @RequestParam int b){
        return a-b;
    }

    @GetMapping("/divide")
    public int divide(@RequestParam int a, @RequestParam int b){
        if (b==0){
            throw new IllegalArgumentException("Делить нельзя на 0");      }
        return a/b;
    }

    // Список для хранения студентов
    private List<Students> studentList = new ArrayList<>();

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public Students getInfo() {
        return new Students("Ivan", 33, "aaa@aa.ru");
    }

    @GetMapping(value = "/getstudent")
    public Students getStudent(){
        Students student = new Students("Lily", 23, "lil@mail.ru");
        studentList.add(student);
        return student;
    }

    //http://localhost:8080/students/create
    @PostMapping(value = "/students/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Students> createStudent(@RequestBody Students student){
        studentList.add(student);
        System.out.println(student.getName());
        System.out.println(student.getAge());
        System.out.println(student.getEmail());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

/* Для body в Postman
{
    "name":"Alex",
    "age":30,
    "email":"alex@mail.ru"
    }
*/


    // Получение всего списка студентов
    @GetMapping("/all")
    public ResponseEntity<List<Students>> getAllStudents() {
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping("/students/{name}/{age}/{email}")
    public ResponseEntity<Students> pathVariableDemo(@PathVariable String name, @PathVariable int age, @PathVariable String email){
        System.out.println(name);
        Students student = new Students();
        student.setName(name);
        student.setAge(age);
        student.setEmail(email);
        return ResponseEntity.ok(student);
    }


}
