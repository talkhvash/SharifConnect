package client.main.java.view.FXML.edu.demand.student.minor;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import server.main.java.constants.Constants;
import shared.config.Config;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class MinorView {
    private Pane pane;
    private FXMLLoader loader;

    public MinorView() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "minorView");
            loader = new FXMLLoader(Paths.get(path).toUri().toURL());
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pane getPane() {
        return pane;
    }

    public MinorViewFXML getFXML() {
        return loader.getController();
    }
}
