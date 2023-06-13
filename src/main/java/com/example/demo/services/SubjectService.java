package com.example.demo.services;

import com.example.demo.dao.SubjectDao;
import com.example.demo.models.Course;
import com.example.demo.models.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectDao subjectDao;

    public SubjectService(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    public List<Subject> showAllSubjects(){
        return subjectDao.showSubjects();
    }

    public Subject findSubjectById(Long id){
        return subjectDao.getSubjectById(id);
    }

    public void saveSubject(Subject subject){

        subjectDao.save(subject);
    }

    public void deleteSubject(Long id){
        subjectDao.deleteSubject(id);
    }

    public Long findIdByName(String name){
        return subjectDao.getIdSubject(name);
    }
}
