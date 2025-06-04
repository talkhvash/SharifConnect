package client.main.java.view.FXML.edu.user.view.student;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class StudentView {
    private Pane pane;
    private final FXMLLoader loader;

    public StudentView() {
        String path = "";
        loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(path)));
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pane getPane() {
        return pane;
    }

    public StudentViewFXML getFXML() {
        return loader.getController();
    }

}
