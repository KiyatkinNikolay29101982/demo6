package com.example.demo.dao;

import com.example.demo.models.Course;
import com.example.demo.models.Subject;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubjectDao {
    private final NamedParameterJdbcTemplate template;

    private final String SHOW_ALL_SUBJECTS = "SELECT * FROM subjects";
    private final String GET_SUBJECT_BY_ID = "SELECT * FROM subjects WHERE  :id";
    private final String INSERT_SUBJECT = "INSERT INTO subjects (name, course_id) VALUES(:name, :courseId);";
    private final String UPDATE_SUBJECT = "UPDATE subjects SET :name :course_id WHERE :id";
    private final String DELETE_SUBJECT = "DELETE FROM subjects WHERE :id";
    private final String ID_FIND_BY_SUBJECT_NAME = "SELECT * FROM subjects WHERE name LIKE :name";

    private final String COURSE_NAME_OF_THIS_SUBJECT = "SELECT c.name\n" +
            "    FROM subjects s JOIN courses c on s.course_id = c.id\n" +
            "WHERE s.name LIKE :name";



    public SubjectDao(NamedParameterJdbcTemplate template) {

        this.template = template;
    }


    public List<Subject> showSubjects(){
        return template.query(SHOW_ALL_SUBJECTS, new BeanPropertyRowMapper<>(Subject.class));
    }

    public Subject getSubjectById(Long id){
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        return template.queryForObject(GET_SUBJECT_BY_ID, sqlParameterSource, new BeanPropertyRowMapper<>(Subject.class));

    }

    public void save(Subject subject){
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("name", subject.getName())
                .addValue("courseId", subject.getCourseId());

//        Map<String, Object> studentMap = new HashMap<>();
//        studentMap.put("name", student.getName());
//        studentMap.put("email", student.getEmail());
//        studentMap.put("age", student.getAge());


        template.update(INSERT_SUBJECT, sqlParameterSource);

    }

    public void updateSubject(Long id, Subject updateSubject){
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("name", updateSubject.getName())
                .addValue("id", id);
        template.update(UPDATE_SUBJECT, sqlParameterSource);
    }

    public void deleteSubject(Long id){
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        template.update(DELETE_SUBJECT,sqlParameterSource);
    }

    public Long getIdSubject(String subjectName){
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("name", subjectName);

        Subject subject = template.query(ID_FIND_BY_SUBJECT_NAME, sqlParameterSource,new BeanPropertyRowMapper<>(Subject.class))
                .stream()
                .findAny()
                .orElse(null);

        if (subject != null){
            return subject.getId();
        }

        return 0L;
    }

    public String getCourseNameForSubject(String subjectName){
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("name", subjectName);
        Course course =
                template.query(COURSE_NAME_OF_THIS_SUBJECT, sqlParameterSource,
                        new BeanPropertyRowMapper<>(Course.class))
                        .stream()
                        .findAny()
                        .orElse(null);
        if(course != null){
            return course.getName();
        }
        return "";
    }

}
