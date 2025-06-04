package client.main.java.view.FXML.messenger.message;

import client.main.java.constants.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import shared.config.Config;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class MessageView {
    private HBox pane;
    private FXMLLoader loader;

    public MessageView() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "messageView");
            loader = new FXMLLoader(Paths.get(path).toUri().toURL());
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pane getPane() {
        return pane;
    }

    public MessageViewFXML getFXML() {
        return loader.getController();
    }
}
