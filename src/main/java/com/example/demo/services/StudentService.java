package com.example.demo.services;

import com.example.demo.dao.StudentDao;
import com.example.demo.models.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> showAllStudents(){
        return studentDao.showStudents();
    }

    public void deleteStudent (Long id) {
        studentDao.deleteStudent(id);
    }

    public void saveNewStudent(Student student){
        studentDao.save(student);
    }

    public Student findById(Long id){
        return studentDao.getStudentById(id);
    }


}
