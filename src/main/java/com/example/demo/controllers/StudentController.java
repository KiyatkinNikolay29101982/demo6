package com.example.demo.controllers;

import com.example.demo.dao.CourseDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.models.Student;
import com.example.demo.services.CourseService;
import com.example.demo.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {


    private final StudentService studentService;

    private final CourseService courseService;

    public StudentController(StudentDao studentDao, StudentService studentService, CourseDao courseDao, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping("/")
    public String show(Model model){
        System.out.println(studentService.showAllStudents());
        model.addAttribute("students", studentService.showAllStudents());
        model.addAttribute("courses", courseService.showAllCourses());
        model.addAttribute("student", new Student());
        return "home";
    }


   @RequestMapping(value = "saveStudent", method = RequestMethod.POST)
    public String saveNewStudent(@RequestParam(name="name") String name,
                                 @RequestParam(name="email") String email,
                                 @RequestParam(name="age") String age,
                                 @RequestParam(name="courseId") String courseId,
                                 Model model){
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setAge(Integer.parseInt(age));
        student.setCourseId(Long.parseLong(courseId));
        studentService.saveNewStudent(student);
        return "redirect:/";
    }

    @PostMapping("/deleteStudent")
    public String deleteStudent(@RequestParam(name="studentId") String id){
        Long id1 = Long.parseLong(id);
        System.out.println(id1);
        studentService.deleteStudent(id1);
        return"redirect:/";
    }
}
