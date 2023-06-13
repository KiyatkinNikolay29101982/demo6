package com.example.demo.dao;

import com.example.demo.models.Course;
import com.example.demo.models.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDao {
    private final NamedParameterJdbcTemplate template;

    private final String SHOW_ALL_Courses = "SELECT * FROM courses";
    private final String GET_COURSE_BY_ID = "SELECT * FROM courses WHERE  :id";
    private final String INSERT_COURSE = "INSERT INTO courses(name) VALUES(:name);";
    private final String UPDATE_COURSE = "UPDATE courses SET :name WHERE :id";
    private final String DELETE_COURSE = "DELETE FROM courses WHERE :id";

    private final String ID_FIND_BY_COURSE_NAME = "SELECT * FROM courses WHERE name LIKE :name";

    public CourseDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }


    public List<Course> showCourses(){
        return template.query(SHOW_ALL_Courses, new BeanPropertyRowMapper<>(Course.class));
    }

    public Course getCourseById(Long id){
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
      return template.query(GET_COURSE_BY_ID, sqlParameterSource, new BeanPropertyRowMapper<>(Course.class))
              .stream().findAny().orElse(null);

    }

    public void save(Course course){
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("name", course.getName());
//        Map<String, Object> studentMap = new HashMap<>();
//        studentMap.put("name", student.getName());
//        studentMap.put("email", student.getEmail());
//        studentMap.put("age", student.getAge());


        template.update(INSERT_COURSE, sqlParameterSource);

    }

    public void updatePerson(Long id, Course updateCourse){
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("name", updateCourse.getName())
                .addValue("id", id);
        template.update(UPDATE_COURSE, sqlParameterSource);
    }

    public void deleteCourse(Long id){
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        template.update(DELETE_COURSE,sqlParameterSource);
    }

    public Long getIdCourse(String courseName){
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("name", courseName);

        Course course = template.query(ID_FIND_BY_COURSE_NAME, sqlParameterSource, new BeanPropertyRowMapper<>(Course.class))
               .stream().findAny().orElse(null);
        if (course != null){
            return course.getId();
        }

        return 0L;
    }
}
