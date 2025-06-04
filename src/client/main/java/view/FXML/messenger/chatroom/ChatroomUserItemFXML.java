package client.main.java.view.FXML.messenger.chatroom;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import shared.model.edu.user.Professor;
import shared.model.edu.user.Student;
import shared.model.edu.user.User;

public class ChatroomUserItemFXML {
    private ChatroomToUserListener listener;

    @FXML
    private Label nameLabel,
            positionLabel;
    @FXML
    private CheckBox checkBox;

    public void initialize(User user, boolean check) {
        nameLabel.setText(user.getName());
        if (user instanceof Student) {
            positionLabel.setText("دانشجو");
        } else if (user instanceof Professor) {
            positionLabel.setText("استاد");
        }
        checkBox.setSelected(check);

        checkBox.setOnAction(event -> listener.listen(user.getId(), checkBox.isSelected()));

    }

    public void setListener(ChatroomToUserListener listener) {
        this.listener = listener;
    }

}
