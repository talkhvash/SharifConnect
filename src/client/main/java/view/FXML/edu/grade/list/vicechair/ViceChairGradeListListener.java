package client.main.java.view.FXML.edu.grade.list.vicechair;

import client.main.java.view.GUIController;
import shared.request.edu.grade.vicechairgradelist.RefreshViceChairGradeListRequest;

public class ViceChairGradeListListener {

    public void listen(Integer courseId, String stuName, String proName, Integer userId) {
        GUIController.getController().startLoop(() ->
                GUIController.getController().getListener().listen(new RefreshViceChairGradeListRequest(
                stuName, proName, courseId, userId
        )));

    }


}
