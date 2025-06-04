package client.main.java.view.FXML.edu.grade.list.student;

import client.main.java.constants.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import shared.config.Config;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class StudentGradeList {
    private Pane pane;
    private FXMLLoader loader;

    public StudentGradeList() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "studentGradeList");
            loader = new FXMLLoader(Paths.get(path).toUri().toURL());
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pane getPane() {
        return pane;
    }

    public StudentGradeListFXML getFXML() {
        return loader.getController();
    }
}
