package client.main.java.view.FXML.edu.grade.list.professor;

import client.main.java.view.GUIController;
import shared.model.edu.course.Course;
import shared.request.edu.grade.professorgradelist.RefreshCourseGradeRequest;
import shared.request.edu.grade.professorgradelist.RefreshGradeRequest;
import shared.request.edu.grade.professorgradelist.RefreshProfessorGradeListRequest;

public class ProfessorGradeListListener {

    public void listen(Integer courseId, boolean finalize){
        GUIController.getController().getListener().listen(new RefreshCourseGradeRequest(courseId, finalize));
    }

    public void listen(Integer id, Float number, String answer, boolean assignAnswer) {
        GUIController.getController().getListener().listen(new RefreshGradeRequest(id,
                number, answer, assignAnswer));
    }

    public void listen(Course course) {
        GUIController.getController().startLoop(
                () -> GUIController.getController().getListener().listen(
                        new RefreshProfessorGradeListRequest(course)
                ));
    }
}
