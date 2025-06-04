package client.main.java.view.FXML.edu.user.list.student;

import client.main.java.constants.Constants;
import client.main.java.view.FXML.edu.user.list.professor.ProfessorListItemFXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import shared.config.Config;
import shared.model.edu.user.Student;

import java.io.IOException;
import java.nio.file.Paths;

public class StudentListItem {
    private HBox hBox;
    private FXMLLoader loader;

    public StudentListItem() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "studentListItem");
            loader = new FXMLLoader(Paths.get(path).toUri().toURL());
            hBox = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HBox getHBox() {
        return hBox;
    }

    public StudentListItemFXML getFXML() {
        return loader.getController();
    }
}
