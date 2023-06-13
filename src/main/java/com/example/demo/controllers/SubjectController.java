package com.example.demo.controllers;

import com.example.demo.models.Subject;
import com.example.demo.services.CourseService;
import com.example.demo.services.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubjectController {

    private final SubjectService subjectService;
    private final CourseService courseService;

    public SubjectController(SubjectService subjectService, CourseService courseService) {
        this.subjectService = subjectService;
        this.courseService = courseService;
    }
    @GetMapping("/subjects")
    public String getSubjects(Model model){
        model.addAttribute("subjects", subjectService.showAllSubjects());
        model.addAttribute("subject", new Subject());
        model.addAttribute("courses", courseService.showAllCourses());

        return "subjects";
    }
    @PostMapping("/saveNewSubject")
    public String createSubject(@RequestParam(name="name") String name,
                                @RequestParam(name="courseId") String course){
        System.out.println(name);
        System.out.println(course);
        Subject subject = new Subject();
        subject.setName(name);
        subject.setCourseId(Long.parseLong(course));
        System.out.println(subject);
        subjectService.saveSubject(subject);
        return "redirect:/subjects";
    }


}
