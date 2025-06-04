package client.main.java.view.FXML.edu.demand.selectunit;

import client.main.java.constants.Constants;
import client.main.java.view.FXML.edu.demand.professor.dissertation.DissertationListFXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import shared.config.Config;

import java.io.IOException;
import java.nio.file.Paths;

public class SelectUnitDemandList {
    private Pane pane;
    private FXMLLoader loader;

    public SelectUnitDemandList() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "SUDemandList");
            loader = new FXMLLoader(Paths.get(path).toUri().toURL());
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pane getPane() {
        return pane;
    }

    public SelectUnitDemandListFXML getFXML() {
        return loader.getController();
    }
}
