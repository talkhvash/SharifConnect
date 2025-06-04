package client.main.java.view.FXML.edu.course.exam;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import server.main.java.constants.Constants;
import shared.config.Config;
import shared.model.edu.course.Course;

import java.time.format.DateTimeFormatter;

public class ExamListItemFXML {
    @FXML
    private Label nameLabel,
            timeLabel;

    public void initialize(Course course) {
        nameLabel.setText(course.getName());
        String pattern = new Config(Constants.CONFIG).getProperty(String.class, "dateTimePattern1");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        timeLabel.setText(formatter.format(course.getExamDateTime()));
    }

}
