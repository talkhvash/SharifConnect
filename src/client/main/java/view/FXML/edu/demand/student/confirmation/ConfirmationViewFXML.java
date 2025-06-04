package client.main.java.view.FXML.edu.demand.student.confirmation;

import client.main.java.view.FXML.edu.demand.student.DemandListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import server.main.java.constants.Constants;
import shared.config.Config;
import shared.model.edu.demand.ConfirmationDemand;

import shared.util.ImageUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

public class ConfirmationViewFXML {
    private final DemandListener listener = new DemandListener();
    @FXML
    private Button button;

    public void refresh(ConfirmationDemand demand) {
        if (demand == null) {
            button.setText("ثبت درخواست");
            button.setOnAction(event -> {
                listener.listen("confirmation", null);
            });
        } else {
            button.setText("نمایش فایل");
            button.setOnAction(event -> {
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
    }
}
