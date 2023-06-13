package com.example.demo.dao;


import com.example.demo.models.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentDao {
    private final NamedParameterJdbcTemplate template;

    private final String SHOW_ALL_Students = "SELECT * FROM students";
    private final String GET_STUDENTS_BY_ID = "SELECT * FROM students WHERE  id=:id";
    private final String INSERT_STUDENT = "INSERT INTO students(name, email, age, course_Id) VALUES(:name,:email,:age,:courseId)";
    private final String UPDATE_STUDENT = "UPDATE students SET :name :email :age WHERE id=:id";
    private final String DELETE_STUDENT = "DELETE FROM students WHERE id=:id";

    public StudentDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }


    public List<Student> showStudents(){
        return template.query(SHOW_ALL_Students, new BeanPropertyRowMapper<>(Student.class));
    }

    public Student getStudentById(Long id){
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        return template.queryForObject(GET_STUDENTS_BY_ID, sqlParameterSource, new BeanPropertyRowMapper<>(Student.class));

    }

    public void save(Student student){
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("name", student.getName())
                .addValue("email", student.getEmail())
                .addValue("age", student.getAge())
                .addValue("courseId", student.getCourseId());

//        Map<String, Object> studentMap = new HashMap<>();
//        studentMap.put("name", student.getName());
//        studentMap.put("email", student.getEmail());
//        studentMap.put("age", student.getAge());


       template.update(INSERT_STUDENT, sqlParameterSource);

    }

    public void updatePerson(Long id, Student updateStudent){
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("name", updateStudent.getName())
                .addValue("email", updateStudent.getEmail())
                .addValue("age", updateStudent.getAge())
                .addValue("id", id);
       template.update(UPDATE_STUDENT, sqlParameterSource);
    }

    public void deleteStudent(Long id){
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        template.update(DELETE_STUDENT,sqlParameterSource);
    }
}
