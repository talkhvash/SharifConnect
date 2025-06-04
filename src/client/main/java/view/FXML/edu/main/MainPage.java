package client.main.java.view.FXML.edu.main;

import javafx.scene.layout.Pane;

public class MainPage extends Pane {
    private static MainPage instance;

    public static MainPage getInstance() {
        if (instance == null) instance = new MainPage();
        return instance;
    }

    private final Pane mainPane;
    private final Pane menuPane;

    public MainPage() {
        this.setPrefSize(900, 600);
        this.setMaxSize(900, 600);
        this.setMinSize(900, 600);
        menuPane = new Pane();
        menuPane.setLayoutX(0);
        menuPane.setLayoutY(0);
        menuPane.setPrefSize(900, 50);
        menuPane.setMaxSize(900, 50);
        menuPane.setMinSize(900, 50);
        mainPane = new Pane();
        mainPane.setLayoutX(0);
        mainPane.setLayoutY(50);
        mainPane.setPrefSize(900, 550);
        mainPane.setMaxSize(900, 550);
        mainPane.setMinSize(900, 550);

        this.getChildren().addAll(menuPane, mainPane);
    }

    public void setMenuPane(Pane menu) {
        menuPane.getChildren().clear();
        menuPane.getChildren().add(menu);
    }

    public void setMainPane(Pane pane) {
        mainPane.getChildren().clear();
        mainPane.getChildren().add(pane);
    }

}
