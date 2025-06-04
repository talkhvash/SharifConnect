package client.main.java.view.FXML.myedu.professor;

import client.main.java.constants.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import shared.config.Config;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class ProfessorSelectUnitList {
    private Pane pane;
    private FXMLLoader loader;

    public ProfessorSelectUnitList() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "professorSelectUnitList");
            loader = new FXMLLoader(Paths.get(path).toUri().toURL());
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pane getPane() {
        return pane;
    }

    public ProfessorSelectUnitListFXML getFXML() {
        return loader.getController();
    }

}
