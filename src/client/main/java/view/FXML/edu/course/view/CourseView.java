package client.main.java.view.FXML.edu.course.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import server.main.java.constants.Constants;
import shared.config.Config;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class CourseView {
    private Pane pane;
    private FXMLLoader loader;

    public CourseView() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "courseView");
            loader = new FXMLLoader(Paths.get(path).toUri().toURL());
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pane getPane() {
        return pane;
    }

    public CourseViewFXML getFXML() {
        return loader.getController();
    }
}
