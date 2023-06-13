package com.example.demo.services;

import com.example.demo.dao.CourseDao;
import com.example.demo.models.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseDao courseDao;

    public CourseService(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public List<Course> showAllCourses(){
        return courseDao.showCourses();
    }

    public Course findCourseById(Long id){
        return courseDao.getCourseById(id);
    }

    public void saveCourse(Course course){
        courseDao.save(course);
    }

    public void deleteCourse(Long id){
        courseDao.deleteCourse(id);
    }

    public Long findIdByName(String name){
        return courseDao.getIdCourse(name);
    }

}
