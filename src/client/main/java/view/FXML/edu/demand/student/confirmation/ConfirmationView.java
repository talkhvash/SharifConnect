package client.main.java.view.FXML.edu.demand.student.confirmation;

import client.main.java.constants.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import shared.config.Config;

import java.io.IOException;
import java.util.Objects;

public class ConfirmationView {
    private Pane pane;
    private FXMLLoader loader;

    public ConfirmationView() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "confirmationView");
            loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(path)));
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pane getPane() {
        return pane;
    }

    public ConfirmationViewFXML getFXML() {
        return loader.getController();
    }
}
