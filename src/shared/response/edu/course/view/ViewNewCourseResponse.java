package shared.response.edu.course.view;

import shared.model.edu.user.Professor;
import shared.response.Response;
import shared.response.ResponseHandler;

public class ViewNewCourseResponse extends Response {
    private Professor viewer;

    @Override
    public void handle(ResponseHandler handler) {
        handler.viewNewCourse(this);
    }

    public Professor getViewer() {
        return viewer;
    }

    public void setViewer(Professor viewer) {
        this.viewer = viewer;
    }
}
