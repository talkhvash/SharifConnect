package client.main.java.view.FXML.messenger.chat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import client.main.java.db.ModelLoader;
import client.main.java.view.FXML.myedu.Listener;
import server.main.java.constants.Constants;
import shared.config.Config;
import shared.model.edu.user.User;
import shared.model.messenger.Chat;
import shared.util.ImageUtil;

import java.io.ByteArrayInputStream;

public class ChatViewFXML {
    private Listener listener;

    @FXML
    private Label nameText,
        messageText;
    @FXML
    private Circle imageCircle;

    private Integer id;
    public void refresh(Chat chat, Integer viewerId) {
        id = chat.getId();
        User user;
        if (chat.getUser1Id() == viewerId) {
            user = ModelLoader.getLoader().loadUser(chat.getUser2Id());
        } else {
            user = ModelLoader.getLoader().loadUser(chat.getUser1Id());
        }
        nameText.setText(user.getName());
        messageText.setText(chat.getLastMessageText());

        String imageSting = user.getImageBase64();
        if (imageSting == null) imageSting = new Config(Constants.CONFIG).getProperty(String.class, "defaultImage");
        Image image = new Image(new ByteArrayInputStream(ImageUtil.decodeFromBase64(imageSting)));
        imageCircle.setFill(new ImagePattern(image));
    }

    @FXML
    public void show() {
        listener.listen(id);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}
