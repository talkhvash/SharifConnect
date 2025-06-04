package client.main.java.util;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FormValidation {

    public void isInteger(TextField field, Label error) throws NumberFormatException {
        try {
            Integer.parseInt(field.getText());
        } catch (NumberFormatException e) {
            error.setText("لطفا عدد وارد کنید.");
            throw new NumberFormatException();
        }
    }

    public void isLong(TextField field, Label error) throws NumberFormatException {
        try {
            Long.parseLong(field.getText());
        } catch (NumberFormatException e) {
            error.setText("لطفا عدد وارد کنید.");
            throw new NumberFormatException();
        }
    }

    public boolean isEmpty(TextField field, Label error) {
        if (field.getText().equals("")) {
            error.setText("خالی");
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty(ChoiceBox box, Label error) {
        if (box.getValue() == null) {
            error.setText("انتخاب نشده");
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty(DatePicker dp, Label error) {
        if (dp.getValue() == null) {
            error.setText("انتخاب نشده");
            return true;
        } else {
            return false;
        }
    }

    public boolean equal(TextField field1, TextField field2, Label error) {
        if (field1.getText().equals(field2.getText())) {
            return false;
        } else {
            error.setText("تکرار درست نیست.");
            return true;
        }
    }

}
