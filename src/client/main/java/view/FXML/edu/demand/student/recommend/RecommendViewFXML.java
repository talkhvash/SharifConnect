package client.main.java.view.FXML.edu.demand.student.recommend;

import client.main.java.constants.Constants;
import client.main.java.view.FXML.edu.demand.student.DemandListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import client.main.java.db.ModelLoader;
import shared.config.Config;
import shared.model.edu.demand.RecommendDemand;
import shared.model.edu.user.User;
import shared.util.ImageUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class RecommendViewFXML {
    private final DemandListener listener = new DemandListener();
    @FXML
    private HBox demandBox,
            stateBox;
    @FXML
    private Label stateLabel,
            nameLabel;
    @FXML
    private TextField professorNameField;
    @FXML
    private Button downloadButton;


    public void refresh(RecommendDemand demand) {

        if (demand == null) {
            stateBox.setVisible(false);
            demandBox.setVisible(true);
        } else {
            demandBox.setVisible(false);
            stateBox.setVisible(true);

            User user = ModelLoader.getLoader().loadUser(demand.getProfessorId());
            if (user != null) nameLabel.setText(user.getName());

            if (demand.getState() == null) stateLabel.setText("ثبت شده");
            else if (demand.getState())  {
                stateLabel.setText("تایید شده");
                stateBox.getChildren().add(downloadButton);
            } else {
                stateLabel.setText("رد شده");
                stateBox.getChildren().remove(downloadButton);
            }
        }

        downloadButton.setOnAction(event -> {
            try {
                Config config = new Config(Constants.CONFIG);
                String path = config.getProperty(String.class, "downloadPath");
                Integer num = config.getProperty(Integer.class, "downloadNumber");
                config.setProperty("downloadNumber", (num + 1) + "");

                File file = new File(path + num);
                byte[] bytes = ImageUtil.decodeFromBase64(demand.getFileBase64());
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(bytes);
            } catch (IOException e) {
                System.err.println("file cant downloaded");
            }
        });
    }

    @FXML
    public void demand() {
        listener.listen("recommendation", professorNameField.getText());
    }
}
