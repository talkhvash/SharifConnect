package client.main.java.view.FXML.edu.grade.educationalstate;

import client.main.java.constants.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import shared.config.Config;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class EducationalState {
    private Pane pane;
    private FXMLLoader loader;

    public EducationalState() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "educationalState");
            loader = new FXMLLoader(Paths.get(path).toUri().toURL());
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pane getPane() {
        return pane;
    }

    public EducationalStateFXML getFXML() {
        return loader.getController();
    }
}
