package client.main.java.view.FXML.edu.course.exam;

import client.main.java.constants.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import shared.config.Config;

import java.io.IOException;
import java.nio.file.Paths;

public class ExamList {
    private Pane pane;
    private FXMLLoader loader;

    public ExamList() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "examList");
            loader = new FXMLLoader(Paths.get(path).toUri().toURL());
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pane getPane() {
        return pane;
    }

    public ExamListFXML getFXML() {
        return loader.getController();
    }

}
