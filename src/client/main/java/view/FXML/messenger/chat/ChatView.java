package client.main.java.view.FXML.messenger.chat;

import client.main.java.constants.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import shared.config.Config;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class ChatView {
    private HBox pane;
    private FXMLLoader loader;

    public ChatView() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "chatView");
            loader = new FXMLLoader(Paths.get(path).toUri().toURL());
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pane getPane() {
        return pane;
    }

    public ChatViewFXML getFXML() {
        return loader.getController();
    }
}
