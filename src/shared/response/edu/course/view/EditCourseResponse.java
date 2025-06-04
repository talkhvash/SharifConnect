package shared.response.edu.course.view;

import shared.response.Response;
import shared.response.ResponseHandler;

public class EditCourseResponse extends Response {
    private CourseViewError error = new CourseViewError();

    @Override
    public void handle(ResponseHandler handler) {
        handler.editCourse(this);
    }

    public CourseViewError getError() {
        return error;
    }

    public void setError(CourseViewError error) {
        this.error = error;
    }
}
