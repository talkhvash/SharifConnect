package shared.response.edu.course.view;

import shared.response.Response;
import shared.response.ResponseHandler;

public class NewCourseResponse extends Response {
    private boolean result;
    private Integer courseId;
    private CourseViewError error = new CourseViewError();

    @Override
    public void handle(ResponseHandler handler) {
        handler.newCourse(this);
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public CourseViewError getError() {
        return error;
    }

    public void setError(CourseViewError error) {
        this.error = error;
    }
}
