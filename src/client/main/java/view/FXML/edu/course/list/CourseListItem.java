package client.main.java.view.FXML.edu.course.list;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import server.main.java.constants.Constants;
import shared.config.Config;

import java.io.IOException;
import java.util.Objects;

public class CourseListItem {
    private HBox hBox;
    private FXMLLoader loader;

    public CourseListItem() {
        try {
            String path = new Config(Constants.CONFIG).getProperty(String.class, "courseListItem");
            loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(path)));
            hBox = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HBox getHBox() {
        return hBox;
    }

    public CourseListItemFXML getFXML() {
        return loader.getController();
    }

}
