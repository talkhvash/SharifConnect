package client.main.java.view.FXML.edu.course.schedule;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import shared.model.edu.course.ScheduleTime;
import shared.model.edu.course.Course;

import java.util.LinkedList;

public class ScheduleFXML {
    @FXML
    private Pane pane;

    public void refresh(LinkedList<Course> list) {
        pane.getChildren().clear();
        for (Course course : list) {
            addLabel(course);
        }
    }

    private void addLabel(Course course) {
        ScheduleTime time = course.getScheduleTime();
        for (int day = 0; day < 6; day++) {
            if (time.getDays()[day]) {
                Label courseLabel = new Label(course.getName());

                // view
                BackgroundFill backgroundFill = new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY);
                courseLabel.setBackground(new Background(backgroundFill));
                courseLabel.setTextFill(Color.BLACK);
                courseLabel.setAlignment(Pos.CENTER);

                // size
                int width = (time.getEnd().getHour() - time.getStart().getHour()) * 60 +
                        (time.getEnd().getMinute() - time.getStart().getMinute());
                courseLabel.setPrefWidth(width);
                courseLabel.setPrefHeight(30);

                // location
                int cornerX = 1200 - (time.getEnd().getHour() * 60 + time.getEnd().getMinute());
                courseLabel.setLayoutX(cornerX);
                courseLabel.setLayoutY(day * 30);

                pane.getChildren().add(courseLabel);
            }
        }
    }

}
