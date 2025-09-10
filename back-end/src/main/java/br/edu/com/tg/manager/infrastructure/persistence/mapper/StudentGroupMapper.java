package br.edu.com.tg.manager.infrastructure.persistence.mapper;

import br.edu.com.tg.manager.core.entity.StudentGroup;
import br.edu.com.tg.manager.infrastructure.persistence.model.StudentGroupData;

public class StudentGroupMapper {

    private StudentGroupMapper(){}
    
    public static StudentGroup toDomain(StudentGroupData data) {

        if (data == null) {
            
            return null;
        }

        var studentGroup = new StudentGroup();
        studentGroup.setId(data.getId());
        studentGroup.setYear(data.getYear());
        studentGroup.setSemester(data.getSemester());
        studentGroup.setCourse(CourseMapper.toDomain(data.getCourse()));
        
        return studentGroup;
    }

    public static StudentGroupData toData(StudentGroup domain) {

        if (domain == null) {
        
            return null;
        }

        var studentGroupData = new StudentGroupData();
        studentGroupData.setId(domain.getId());
        studentGroupData.setYear(domain.getYear());
        studentGroupData.setSemester(domain.getSemester());
        studentGroupData.setCourse(CourseMapper.toData(domain.getCourse()));
        
        return studentGroupData;
    }
}