package client.main.java;

import client.main.java.constants.Constants;
import client.main.java.controller.OnlineController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import shared.config.Config;
import shared.util.ImageUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Inet4Address;

public class Main extends Application {
    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        int port = new Config(Constants.CONFIG).getProperty(Integer.class, "port");
        new OnlineController(primaryStage).connectToServer(Inet4Address.getLocalHost(), port);

//        Pane pane = new Pane();
//        Button button = new Button();
//        pane.getChildren().add(button);
//
//        FileChooser fileChooser = new FileChooser();
//        button.setOnAction(event -> {
//            File file = fileChooser.showOpenDialog(new Stage());
//            try {
//                System.out.println(ImageUtil.fileToString(file.getPath()));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        });
//
//        primaryStage.setScene(new Scene(pane));
//        primaryStage.show();
    }

}
