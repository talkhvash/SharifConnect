package client.main.java.view.FXML.messenger.message;

import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import server.main.java.constants.Constants;
import shared.config.Config;
import shared.model.messenger.Message;
import shared.util.ImageUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MessageViewFXML {

    @FXML
    private Text dateText,
            messageText;
    @FXML
    private Button downloadButton;
    @FXML
    private Pane box;

    private Message message;
    public void initialize(Message message, boolean viewer) {
        this.message = message;

        if (viewer) box.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        else box.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

        dateText.setText(message.getText());
        messageText.setText(message.getText());
        if (message.getFileBase64() == null) {
            downloadButton.setVisible(false);
        }
    }

    @FXML
    private void download() {
        try {
            String downloadPath = new Config(Constants.CONFIG).getProperty(String.class,"downloadPath");
            File file = new File(downloadPath + message.getId());
            FileOutputStream fos = new FileOutputStream(file, true);
            fos.write(ImageUtil.decodeFromBase64(message.getFileBase64()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
