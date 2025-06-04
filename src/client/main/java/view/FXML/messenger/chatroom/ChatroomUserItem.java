package client.main.java.view.FXML.messenger.chatroom;

import client.main.java.constants.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import shared.config.Config;

import java.io.IOException;
import java.util.Objects;

public class ChatroomUserItem {
    private HBox hBox;
    private FXMLLoader loader;

    public ChatroomUserItem() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "chatroomUserItem");
            loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(path)));
            hBox = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HBox getHBox() {
        return hBox;
    }

    public ChatroomUserItemFXML getFXML() {
        return loader.getController();
    }

}
