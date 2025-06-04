package client.main.java.view.FXML.edu.authentication.login;

import client.main.java.constants.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import shared.config.Config;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.Objects;

public class LoginPage {
    private Pane pane;
    private FXMLLoader loader;

    public LoginPage() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "loginPage");
            loader = new FXMLLoader(Paths.get(path).toUri().toURL());
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scene getScene() {
        return new Scene(pane);
    }

    public LoginPageFXML getFXML() {
        return loader.getController();
    }

}
