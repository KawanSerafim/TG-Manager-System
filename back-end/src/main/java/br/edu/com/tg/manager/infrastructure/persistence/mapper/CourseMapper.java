package br.edu.com.tg.manager.infrastructure.persistence.mapper;

import br.edu.com.tg.manager.core.entity.Course;
import br.edu.com.tg.manager.infrastructure.persistence.model.CourseData;

public class CourseMapper {

    private CourseMapper(){}

    public static Course toDomain(CourseData data) {
        
        if (data == null) {
        
            return null;
        }
        
        var course = new Course(data.getName());
        course.setId(data.getId());
        
        return course;
    }

    public static CourseData toData(Course domain) {
        
        if (domain == null) {
        
            return null;
        }
        
        var courseData = new CourseData();
        courseData.setId(domain.getId());
        courseData.setName(domain.getName());
        
        return courseData;
    }
}