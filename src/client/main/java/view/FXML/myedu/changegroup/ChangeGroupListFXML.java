package client.main.java.view.FXML.myedu.changegroup;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import client.main.java.db.ModelLoader;
import client.main.java.view.FXML.myedu.Listener;
import shared.model.edu.course.Course;

import java.util.LinkedList;

public class ChangeGroupListFXML {

    private Listener listener;
    @FXML
    private VBox box;

    public void refresh(LinkedList<Integer> list) {
        box.getChildren().clear();
        for (Integer item : list) {
            Course course = ModelLoader.getLoader().loadCourse(item);
            ChangeGroupListItem temp = new ChangeGroupListItem();
            temp.getFXML().initialize(course);
            temp.getFXML().setListener(listener);
            box.getChildren().add(temp.getHBox());
        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}
