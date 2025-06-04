package client.main.java.view.FXML.edu.demand.professor.withdraw;

import client.main.java.constants.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import shared.config.Config;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class WithdrawListItem {
    private HBox hBox;
    private FXMLLoader loader;

    public WithdrawListItem() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "withdrawListItem");
            loader = new FXMLLoader(Paths.get(path).toUri().toURL());
            hBox = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HBox getHBox() {
        return hBox;
    }

    public WithdrawListItemFXML getFXML() {
        return loader.getController();
    }
}
