package client.main.java.view.FXML.edu.user.list.student;

import client.main.java.constants.Constants;
import client.main.java.view.FXML.edu.user.list.professor.ProfessorListFXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import shared.config.Config;

import java.io.IOException;
import java.nio.file.Paths;

public class StudentList {
    private Pane pane;
    private FXMLLoader loader;

    public StudentList() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "studentList");
            loader = new FXMLLoader(Paths.get(path).toUri().toURL());
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pane getPane() {
        return pane;
    }

    public StudentListFXML getFXML() {
        return loader.getController();
    }

}
