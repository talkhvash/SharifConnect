package client.main.java.view.FXML.edu.user.list.professor;


import client.main.java.constants.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import shared.config.Config;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class ProfessorListItem {
    private HBox hBox;
    private FXMLLoader loader;

    public ProfessorListItem() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "professorListItem");
            loader = new FXMLLoader(Paths.get(path).toUri().toURL());
            hBox = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HBox getHBox() {
        return hBox;
    }

    public ProfessorListItemFXML getFXML() {
        return loader.getController();
    }

}
