package client.main.java.view.FXML.edu.demand.professor.dissertation;

import client.main.java.constants.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import shared.config.Config;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class DissertationListItem {
    private HBox hBox;
    private FXMLLoader loader;

    public DissertationListItem() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "dissertationListItem");
            loader = new FXMLLoader(Paths.get(path).toUri().toURL());
            hBox = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HBox getHBox() {
        return hBox;
    }

    public DissertationListItemFXML getFXML() {
        return loader.getController();
    }
}
