package client.main.java.view.FXML.messenger.chatroom;

import client.main.java.constants.Constants;
import client.main.java.view.FXML.messenger.chat.ChatViewFXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import shared.config.Config;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class Chatroom {
    private HBox pane;
    private FXMLLoader loader;

    public Chatroom() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "chatroom");
            loader = new FXMLLoader(Paths.get(path).toUri().toURL());
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scene getScene() {
        return new Scene(pane);
    }

    public ChatroomFXML getFXML() {
        return loader.getController();
    }


}
