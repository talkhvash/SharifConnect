package client.main.java.view.FXML.edu.demand.student.recommend;

import client.main.java.constants.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import shared.config.Config;

import java.io.IOException;
import java.nio.file.Paths;

public class RecommendView {
    private Pane pane;
    private FXMLLoader loader;

    public RecommendView() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "recommendView");
            loader = new FXMLLoader(Paths.get(path).toUri().toURL());
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pane getPane() {
        return pane;
    }

    public RecommendViewFXML getFXML() {
        return loader.getController();
    }
}
