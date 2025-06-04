package client.main.java.view.FXML.edu.main.menu;

import client.main.java.constants.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import shared.config.Config;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class MainMenu {
    private Pane pane;
    private FXMLLoader loader;

    public MainMenu() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "mainMenu");
            loader = new FXMLLoader(Paths.get(path).toUri().toURL());
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pane getPane() {
        return pane;
    }

    public MainMenuFXML getFXML() {
        return loader.getController();
    }
}
