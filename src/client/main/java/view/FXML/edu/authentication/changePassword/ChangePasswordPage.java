package client.main.java.view.FXML.edu.authentication.changePassword;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import server.main.java.constants.Constants;
import shared.config.Config;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class ChangePasswordPage {
    private Pane pane;
    private FXMLLoader loader;

    public ChangePasswordPage() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "changePasswordPage");
            loader = new FXMLLoader(Paths.get(path).toUri().toURL());
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scene getScene() {
        return new Scene(pane);
    }

    public ChangePasswordPageFXML getFXML() {
        return loader.getController();
    }

}
