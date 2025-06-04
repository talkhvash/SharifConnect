package client.main.java.view.FXML.edu.course.list;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import server.main.java.constants.Constants;
import shared.config.Config;

import java.io.IOException;
import java.util.Objects;

public class CourseList {
    private Pane pane;
    private FXMLLoader loader;

    public CourseList() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "courseList");
            loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(path)));
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pane getPane() {
        return pane;
    }

    public CourseListFXML getFXML() {
        return loader.getController();
    }
}
