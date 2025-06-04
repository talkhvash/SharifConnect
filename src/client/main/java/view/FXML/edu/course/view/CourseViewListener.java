package client.main.java.view.FXML.edu.course.view;

import client.main.java.view.GUIController;
import shared.model.edu.course.Course;
import shared.request.edu.course.view.EditCourseRequest;
import shared.request.edu.course.view.NewCourseRequest;

public class CourseViewListener {

    public void listen(String type, Course course, Integer courseId) {
        switch (type) {
            case "new":
                GUIController.getController().getListener().listen(new NewCourseRequest(course));
                break;
            case "edit":
                GUIController.getController().getListener().listen(new EditCourseRequest(course, courseId));
                break;
        }
    }
}
