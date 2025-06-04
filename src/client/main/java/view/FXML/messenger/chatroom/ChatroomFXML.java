package client.main.java.view.FXML.messenger.chatroom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import client.main.java.db.ModelLoader;
import client.main.java.view.FXML.messenger.chat.ChatView;
import client.main.java.view.FXML.messenger.message.MessageView;
import client.main.java.constants.Constants;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import shared.config.Config;
import shared.model.edu.enums.Degree;
import shared.model.edu.enums.Rand;
import shared.model.edu.enums.Year;
import shared.model.edu.user.Admin;
import shared.model.edu.user.MrMohseni;
import shared.model.edu.user.User;
import shared.model.messenger.Chat;
import shared.model.messenger.Message;
import shared.util.ImageUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public class ChatroomFXML {
    private final ChatroomListener listener = new ChatroomListener();

    @FXML
    private Label nameLabel;
    @FXML
    private Circle imageCircle;
    @FXML
    private Pane chatPane;
    @FXML
    private TextArea messageArea1,
            messageArea2,
            messageArea3;
    @FXML
    private String fileBase1,
            fileBase2,
            fileBase3;
    @FXML
    private VBox chatsBox,
            messagesBox,
            usersBox;
    @FXML
    private TextField userIdField;
    @FXML
    private ChoiceBox<Rand> randBox;
    @FXML
    private ChoiceBox<Year> yearBox;
    @FXML
    private ChoiceBox<Degree> degreeBox;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab mrMohseniTab,
            chatsTab,
            newChatTab;
    @FXML
    private Button mainButton;

    private final FileChooser fileChooser = new FileChooser();

    private Integer chatId;

    private LinkedList<Chat> chatsList;
    private HashMap<Integer, LinkedList<Message>> messagesList;
    private LinkedList<User> usersList;
    private User viewer;

    private final LinkedList<Integer> selectedUsersId = new LinkedList<>();

    private void offlineView() {
        tabPane.getTabs().remove(mrMohseniTab);
        tabPane.getTabs().remove(newChatTab);
        chatPane.setVisible(false);
    }

    public void initialize() {
        randBox.setItems(FXCollections.observableList(Rand.all()));
        randBox.getItems().add(null);
        degreeBox.setItems(FXCollections.observableList(Degree.all()));
        degreeBox.getItems().add(null);
        yearBox.setItems(FXCollections.observableList(Year.all()));
        yearBox.getItems().add(null);
    }

    public void refresh(LinkedList<Chat> chatsList,
                        HashMap<Integer, LinkedList<Message>> messagesList,
                        LinkedList<User> usersList,
                        User viewer) {

        this.chatsList = chatsList;
        this.messagesList = messagesList;
        this.usersList = usersList;
        this.viewer = viewer;

        if (chatId == null) {
            chatPane.setVisible(false);
        } else {
            chatPane.setVisible(true);
            refreshMessages();
            refreshHeader();
        }

        refreshChats();
        refreshUsers();

        if (!(viewer instanceof MrMohseni)) {
            tabPane.getTabs().remove(mrMohseniTab);
        }
        if (viewer instanceof Admin) {
            mainButton.setVisible(false);
            tabPane.getTabs().remove(newChatTab);
        }
    }

    private void refreshChats() {
        chatsBox.getChildren().clear();
        for (Chat item : chatsList) {
            ChatView chatView = new ChatView();
            chatView.getFXML().refresh(item, viewer.getId());
            chatView.getFXML().setListener(chatId -> this.chatId = chatId);
            refreshMessages();
            refreshHeader();
            chatsBox.getChildren().add(chatView.getPane());
        }
    }

    private void refreshHeader() {
        Chat chat = null;
        for (Chat item : chatsList) if (Objects.equals(item.getId(), chatId)) chat = item;

        User contact;
        assert chat != null;
        if (viewer.getId() == chat.getUser1Id()) {
            contact = ModelLoader.getLoader().loadUser(chat.getUser2Id());
        } else {
            contact = ModelLoader.getLoader().loadUser(chat.getUser1Id());
        }
        nameLabel.setText(contact.getName());
        String imageSting = contact.getImageBase64();
        setImage(imageSting);
    }

    private void refreshMessages() {
        messagesBox.getChildren().clear();
        LinkedList<Message> list = messagesList.get(chatId);
        for (Message message : list) {
            MessageView messageView = new MessageView();
            messageView.getFXML().initialize(message, message.getSenderId().equals(viewer.getId()));
            messagesBox.getChildren().add(messageView.getPane());
        }
    }

    private void refreshUsers() {
        usersBox.getChildren().clear();
        for (User user : usersList) {
            ChatroomUserItem item = new ChatroomUserItem();
            item.getFXML().initialize(user, selectedUsersId.contains(user.getId()));
            item.getFXML().setListener((id, add) -> {
                if (add) selectedUsersId.add(id);
                else selectedUsersId.remove(id);
            });
            usersBox.getChildren().add(item.getHBox());
        }
    }

    private void setImage(String imageBase64) {
        try {
            byte[] bytes = ImageUtil.decodeFromBase64(imageBase64);
            imageCircle.setFill(new ImagePattern(new Image(new ByteArrayInputStream(bytes))));
        } catch (Exception e) {
            String defaultPicture = new Config(Constants.CONFIG).getProperty(String.class, "defaultPicture");
            byte[] bytes = ImageUtil.decodeFromBase64(defaultPicture);
            imageCircle.setFill(new ImagePattern(new Image(new ByteArrayInputStream(bytes))));
        }
    }


    @FXML
    private void sendInChat() {
        listener.listen(null,
                chatId,
                null,
                null,
                null,
                null,
                fileBase1,
                messageArea1.getText());
        messageArea1.setText("");
        fileBase1 = null;
    }

    @FXML
    private void sendToUser() {
        try {
            listener.listen(Integer.parseInt(userIdField.getText()),
                    null,
                    null,
                    null,
                    null,
                    null,
                    fileBase2,
                    messageArea2.getText());
            messageArea2.setText("");
            fileBase2 = null;
        } catch (Exception ignored) {
        }
    }

    @FXML
    private void sendToList() {
        try {
            listener.listen(null,
                    null,
                    selectedUsersId,
                    null,
                    null,
                    null,
                    fileBase2,
                    messageArea2.getText());
            messageArea2.setText("");
            fileBase2 = null;
        } catch (Exception ignored) {
        }
    }

    @FXML
    private void sendByFilter() {
        try {
            listener.listen(null,
                    null,
                    null,
                    randBox.getValue(),
                    yearBox.getValue(),
                    degreeBox.getValue(),
                    fileBase3,
                    messageArea3.getText());
            messageArea3.setText("");
            fileBase3 = null;
        } catch (Exception ignored) {
        }
    }

    @FXML
    private void upload1() {
        try {
            File file = fileChooser.showOpenDialog(new Stage());
            fileBase1 = ImageUtil.fileToString(file.getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void upload2() {
        try {
            File file = fileChooser.showOpenDialog(new Stage());
            fileBase2 = ImageUtil.fileToString(file.getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void upload3() {
        try {
            File file = fileChooser.showOpenDialog(new Stage());
            fileBase3 = ImageUtil.fileToString(file.getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goMainPane() {

    }
}

