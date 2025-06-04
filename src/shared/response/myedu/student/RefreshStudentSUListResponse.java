package shared.response.myedu.student;

import shared.model.edu.course.Course;
import shared.model.edu.user.Student;
import shared.response.Response;
import shared.response.ResponseHandler;

import java.util.LinkedList;

public class RefreshStudentSUListResponse extends Response {
    private boolean result;
    private LinkedList<Course> coursesList;
    private Student viewer;

    @Override
    public void handle(ResponseHandler handler) {
        handler.refreshStudentSUList(this);
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public LinkedList<Course> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(LinkedList<Course> coursesList) {
        this.coursesList = coursesList;
    }

    public Student getViewer() {
        return viewer;
    }

    public void setViewer(Student viewer) {
        this.viewer = viewer;
    }
}
