package pl.school.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import pl.school.entity.Student;

@Projection(name="teacherProjection", types = {Student.class})
public interface TeacherProjection {
    Integer getIdTeacher();
    String getFirstName();
    @Value("#{target.students.![name]}")
    String getStudentList();


}
