package client.main.java.view.FXML.edu.course.list;

import client.main.java.client.RequestListener;
import client.main.java.view.GUIController;
import shared.request.FilterCourseForm;
import shared.request.edu.course.list.DeleteCourseRequest;
import shared.request.edu.course.list.RefreshCourseListRequest;
import shared.request.edu.course.view.ViewEditCourseRequest;
import shared.request.edu.course.view.ViewNewCourseRequest;

public class CourseListListener {
    private final RequestListener listener = GUIController.getController().getListener();
    public void listen(String type, Integer courseId) {
        switch (type) {
            case "new":
                listener.listen(new ViewNewCourseRequest());
                break;
            case "edit":
                listener.listen(new ViewEditCourseRequest(courseId));
                break;
            case "delete":
                listener.listen(new DeleteCourseRequest(courseId));
                break;
        }
    }

    public void listen(FilterCourseForm form) {
        GUIController.getController().startLoop(
                () -> GUIController.getController().getListener().listen(
                        new RefreshCourseListRequest(form)
                ));
    }
}
