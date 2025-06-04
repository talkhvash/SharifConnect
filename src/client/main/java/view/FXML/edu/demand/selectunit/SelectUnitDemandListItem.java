package client.main.java.view.FXML.edu.demand.selectunit;

import client.main.java.constants.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import shared.config.Config;

import java.io.IOException;
import java.nio.file.Paths;

public class SelectUnitDemandListItem {
    private HBox hBox;
    private FXMLLoader loader;

    public SelectUnitDemandListItem() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "SUDemandListItem");
            loader = new FXMLLoader(Paths.get(path).toUri().toURL());
            hBox = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HBox getHBox() {
        return hBox;
    }

    public SelectUnitDemandListItemFXML getFXML() {
        return loader.getController();
    }
}
