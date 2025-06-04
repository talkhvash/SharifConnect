package shared.response.edu.course.view;

import shared.model.edu.course.Course;
import shared.response.Response;
import shared.response.ResponseHandler;

public class ViewEditCourseResponse extends Response {
    private Course course;

    @Override
    public void handle(ResponseHandler handler) {
        handler.viewEditCourse(this);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
