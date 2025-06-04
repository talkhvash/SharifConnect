package client.main.java.view.FXML.edu.demand.student.dorm;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import server.main.java.constants.Constants;
import shared.config.Config;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class DormView {
    private Pane pane;
    private FXMLLoader loader;

    public DormView() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "dormView");
            loader = new FXMLLoader(Paths.get(path).toUri().toURL());
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pane getPane() {
        return pane;
    }

    public DormViewFXML getFXML() {
        return loader.getController();
    }
}
