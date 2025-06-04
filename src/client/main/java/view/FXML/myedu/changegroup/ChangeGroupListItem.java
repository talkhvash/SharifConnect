package client.main.java.view.FXML.myedu.changegroup;

import client.main.java.constants.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import shared.config.Config;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class ChangeGroupListItem {
    private HBox hBox;
    private FXMLLoader loader;

    public ChangeGroupListItem() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "changeGroupListItem");
            loader = new FXMLLoader(Paths.get(path).toUri().toURL());
            hBox = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HBox getHBox() {
        return hBox;
    }

    public ChangeGroupListItemFXML getFXML() {
        return loader.getController();
    }

}
