package client.main.java.view.FXML.edu.demand.professor.withdraw;

import client.main.java.constants.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import shared.config.Config;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class WithdrawList {
    private Pane pane;
    private FXMLLoader loader;

    public WithdrawList() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "withdrawList");
            loader = new FXMLLoader(Paths.get(path).toUri().toURL());
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pane getPane() {
        return pane;
    }

    public WithdrawListFXML getFXML() {
        return loader.getController();
    }
}
