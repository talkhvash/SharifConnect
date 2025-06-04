package client.main.java.view.FXML.edu.grade.list.professor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import client.main.java.db.ModelLoader;
import shared.model.edu.course.Grade;
import shared.model.edu.user.User;

public class ProfessorGradeListItemFXML {
    private final ProfessorGradeListListener listener = new ProfessorGradeListListener();
    @FXML
    private Label nameLabel,
            errorLabel,
            objectionLabel,
            answerLabel,
            numberLabel;
    @FXML
    private HBox box;
    @FXML
    private Button assignButton;
    @FXML
    private TextField answerField,
            numberField;


    public void initialize(Grade grade) {
        User user = ModelLoader.getLoader().loadUser(grade.getId());
        if (user != null) nameLabel.setText(user.getName());

        if (grade.isFinalizedObjection()) {
            objectionLabel.setText(grade.getObjection());
            if (grade.isFinalizedAnswer()) {
                box.getChildren().remove(assignButton);
                box.getChildren().remove(answerField);
                answerLabel.setText(grade.getAnswer());
            } else {
                answerField.setText(grade.getAnswer());
                box.getChildren().remove(answerLabel);
            }
        } else {
            objectionLabel.setText("");
            answerLabel.setText("");
            box.getChildren().remove(assignButton);
            box.getChildren().remove(answerField);
        }

        if (grade.getFinalizedNumber() != null && grade.getFinalizedNumber()) {
            box.getChildren().remove(numberField);
            numberLabel.setText(grade.getNumber() + "");
        } else {
            numberField.setText(grade.getNumber() + "");
            box.getChildren().remove(numberLabel);
        }

        if (!numberField.getText().equals("")) {
            try {
                float number = Float.parseFloat(numberField.getText());
                if (number > 20 || number < 0) {
                    errorLabel.setText("0-20");
                } else {
                    listener.listen(grade.getId(), number, grade.getAnswer(), false);
                }
            } catch (NumberFormatException e) {
                errorLabel.setText("عدد");
            }
        }

        numberField.setOnKeyTyped(event -> {
            if (grade.getFinalizedNumber() == null) {
                if (!numberField.getText().equals("")) {
                    try {
                        float number = Float.parseFloat(numberField.getText());
                        if (number > 20 || number < 0) {
                            errorLabel.setText("0-20");
                        } else {
                            errorLabel.setText("");
                            listener.listen(grade.getId(), number, grade.getAnswer(), false);
                        }
                    } catch (NumberFormatException e) {
                        errorLabel.setText("عدد");
                    }
                }
            } else {
                if (!numberField.getText().equals("")) {
                    try {
                        float number = Float.parseFloat(numberField.getText());
                        if (number > 20 || number < 0) {
                            errorLabel.setText("0-20");
                        } else {
                            errorLabel.setText("");
                            listener.listen(grade.getId(), number, grade.getAnswer(), false);
                        }
                    } catch (NumberFormatException e) {
                        errorLabel.setText("عدد");
                    }
                } else {
                    errorLabel.setText("خالی");
                }
            }
        });

    }

    public boolean isValid() {
        if (!numberField.getText().equals("")) {
            try {
                float number = Float.parseFloat(numberField.getText());
                if (number > 20 || number < 0) {
                    errorLabel.setText("0-20");
                    return false;
                } else {
                    errorLabel.setText("");
                    return true;
                }
            } catch (NumberFormatException e) {
                errorLabel.setText("عدد");
                return false;
            }
        } else {
            errorLabel.setText("خالی");
            return false;
        }
    }

}
