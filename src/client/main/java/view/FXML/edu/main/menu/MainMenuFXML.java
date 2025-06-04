package client.main.java.view.FXML.edu.main.menu;

import client.main.java.constants.Constants;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import javafx.scene.layout.HBox;
import shared.config.Config;
import shared.model.edu.user.Admin;
import shared.model.edu.user.Professor;
import shared.model.edu.user.Student;
import shared.model.edu.user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class MainMenuFXML {
    private final MainMenuListener listener = new MainMenuListener();

    @FXML
    private MenuItem recommendMenuItem,
            recommendListMenuItem,
            withdrawMenuItem,
            withdrawListMenuItem,
            dissertationMenuItem,
            dissertationListMenuItem,
            dormMenuItem,
            minorMenuItem,
            minorListMenuItem,
            confirmationMenuItem,
            newStudentMenuItem,
            newProfessorMenuItem,
            stuSUDemandMenuItem,
            proSUDemandMenuItem;
    @FXML
    private HBox offlineBox;
    @FXML
    private Menu newUserMenu,
            selectUnitMenu,
            mainMenu,
            signupMenu,
            gradeMenu,
            profileMenu,
            stateMenu,
            demandsMenu,
            mrMohseniMenu;

    @FXML
    private Label lastInterTimeLabel,
            timeLabel;


    public void offlineView() {
        offlineBox.setVisible(true);
    }

    public void refresh(User user, boolean selectUnitMenuVisible, LocalDateTime time) {
        String pattern = new Config(Constants.CONFIG).getProperty(String.class, "dataTimePattern2");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        lastInterTimeLabel.setText(formatter.format(user.getLastInterTime()));
        timeLabel.setText(formatter.format(time));

        if (user instanceof Student) {
            Student student = (Student) user;

            selectUnitMenu.setVisible(selectUnitMenuVisible);

            newUserMenu.setVisible(false);

            recommendListMenuItem.setVisible(false);
            withdrawListMenuItem.setVisible(false);
            dissertationListMenuItem.setVisible(false);
            minorListMenuItem.setVisible(false);
            stuSUDemandMenuItem.setVisible(true);
            switch (student.getDegree()) {
                case UNDERGRADUATE:
                    dormMenuItem.setVisible(false);
                    dissertationMenuItem.setVisible(false);
                    break;
                case POSTGRADUATE:
                    dissertationMenuItem.setVisible(false);
                    minorMenuItem.setVisible(false);
                    break;
                case DOCTORATE:
                    dormMenuItem.setVisible(false);
                    minorMenuItem.setVisible(false);
                    recommendMenuItem.setVisible(false);
                    break;
            }

        } else if (user instanceof Professor){
            Professor professor = (Professor) user;

            dormMenuItem.setVisible(false);
            dissertationMenuItem.setVisible(false);
            minorMenuItem.setVisible(false);
            recommendMenuItem.setVisible(false);
            withdrawMenuItem.setVisible(false);
            confirmationMenuItem.setVisible(false);
            stuSUDemandMenuItem.setVisible(false);

            recommendListMenuItem.setVisible(false);
            withdrawListMenuItem.setVisible(false);
            dissertationListMenuItem.setVisible(false);
            minorListMenuItem.setVisible(false);
            newStudentMenuItem.setVisible(false);
            newProfessorMenuItem.setVisible(false);
            selectUnitMenu.setVisible(false);
            proSUDemandMenuItem.setVisible(false);

            if (professor.isViceChair()) {
                recommendListMenuItem.setVisible(true);
                withdrawListMenuItem.setVisible(true);
                dissertationListMenuItem.setVisible(true);
                minorListMenuItem.setVisible(true);
                newStudentMenuItem.setVisible(true);
                selectUnitMenu.setVisible(true);
                proSUDemandMenuItem.setVisible(true);
            } else if (professor.isChairman()) {
                newProfessorMenuItem.setVisible(true);
            }
        } else {
            mainMenu.setVisible(false);
            signupMenu.setVisible(false);
            stateMenu.setVisible(false);
            gradeMenu.setVisible(false);
            demandsMenu.setVisible(false);
            profileMenu.setVisible(false);
            newUserMenu.setVisible(false);
            selectUnitMenu.setVisible(false);
            if (user instanceof Admin) {
                mrMohseniMenu.setVisible(false);
            }
        }
    }

    //
    @FXML
    private void goFirstPane() {
        listener.listen("goFirstPane");
    }

    @FXML
    private void goCoursesListPane() {
        listener.listen("goCoursesListPane");
    }

    @FXML
    private void goProfessorsListPane() {
        listener.listen("goProfessorsListPane");
    }

    @FXML
    private void goExamsListPane() {
        listener.listen("goExamsListPane");
    }

    @FXML
    private void goSchedulePane() {
        listener.listen("goSchedulePane");
    }

    @FXML
    private void goRecommendPane() {
        listener.listen("goRecommendPane");
    }

    @FXML
    private void goRecommendListPane() {
        listener.listen("goRecommendListPane");
    }

    @FXML
    private void goMinorPane() {
        listener.listen("goMinorPane");
    }

    @FXML
    private void goMinorListPane() {
        listener.listen("goMinorListPane");
    }

    @FXML
    private void goDissertationPane() {
        listener.listen("goDissertationPane");
    }

    @FXML
    private void goDissertationListPane() {
        listener.listen("goDissertationListPane");
    }

    @FXML
    private void goWithdrawPane() {
        listener.listen("goWithdrawPane");
    }

    @FXML
    private void goWithdrawListPane() {
        listener.listen("goWithdrawListPane");
    }

    @FXML
    private void goDormPane() {
        listener.listen("goDormPane");
    }

    @FXML
    private void goGradesListPane() {
        listener.listen("goGradesListPane");
    }

    @FXML
    private void goEducationalStatePane() {
        listener.listen("goEducationalStatePane");
    }

    @FXML
    private void goProfilePane() {
        listener.listen("goProfilePane");
    }

    @FXML
    private void goNewStudentView() {
        listener.listen("goNewStudentView");
    }

    @FXML
    private void goNewProfessorView() {
        listener.listen("goNewProfessorView");
    }

    @FXML
    private void exit() {
        listener.listen("exit");
    }

    @FXML
    private void goConfirmationPane() {
        listener.listen("goConfirmationPane");
    }

    @FXML
    private void goSelectUnitPane() {
        listener.listen("goSelectUnitPane");
    }

    @FXML
    private void goChatroom() {
        listener.listen("goChatroom");
    }

    @FXML
    private void goStudentsList() {
        listener.listen("goStudentsList");
    }

    @FXML
    private void goSUDemandsList() {
        listener.listen("goSUDemandsList");
    }

    @FXML
    private void reconnect() {
        listener.listen("reconnect");
    }
}
