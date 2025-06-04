package client.main.java.view.FXML.edu.main.menu;

import client.main.java.client.RequestListener;
import client.main.java.controller.Connection;
import client.main.java.view.GUIController;
import shared.request.edu.course.list.ViewCourseListRequest;
import shared.request.edu.grade.ViewGradeList;
import shared.request.edu.grade.educationalstate.ViewEducationalStateRequest;
import shared.request.edu.user.profile.ViewProfileRequest;
import shared.request.edu.user.view.neww.ViewNewProfessorRequest;
import shared.request.edu.user.view.neww.ViewNewStudentRequest;
import shared.request.myedu.ViewSUListRequest;

public class MainMenuListener {
    private final RequestListener listener = GUIController.getController().getListener();
    private final GUIController controller = GUIController.getController();

    public void listen(String type) {
        boolean online = Connection.getStatus().isOnline();
        switch (type) {
            case "goFirstPane":
                if (online) controller.showMainPage();
                else controller.offlineMainPage();
                break;
            case "goCoursesListPane":
                if (online) listener.listen(new ViewCourseListRequest());
                break;
            case "goProfessorsListPane":
                if (online) controller.showProfessorList();
                break;
            case "goExamsListPane":
                if (online) controller.showExamList();
                else controller.offlineExamList();
                break;
            case "goSchedulePane":
                if (online) controller.showSchedule();
                else controller.offlineSchedule();
                break;
            case "goConfirmationPane":
                if (online) controller.showConfirmationView();
                break;
            case "goRecommendPane":
                if (online) controller.showRecommendView();
                break;
            case "goRecommendListPane":
                if (online) controller.showRecommendList();
                break;
            case "goMinorPane":
                if (online) controller.showMinorView();
                break;
            case "goMinorListPane":
                if (online) controller.showMinorList();
                break;
            case "goDissertationPane":
                if (online) controller.showDissertationView();
                break;
            case "goDissertationListPane":
                if (online) controller.showDissertationList();
                break;
            case "goWithdrawPane":
                if (online) controller.showWithdrawView();
                break;
            case "goWithdrawListPane":
                if (online) controller.showWithdrawList();
                break;
            case "goDormPane":
                if (online) controller.showDormView();
                break;
            case "goGradesListPane":
                if (online) controller.getListener().listen(new ViewGradeList());
                break;
            case "goEducationalStatePane":
                if (online) controller.getListener().listen(new ViewEducationalStateRequest());
                else controller.offlineEducationalState();
                break;
            case "goProfilePane":
                if (online) listener.listen(new ViewProfileRequest());
                else controller.offlineProfile();
                break;
            case "goNewStudentView":
                if (online) listener.listen(new ViewNewStudentRequest());
                break;
            case "goNewProfessorView":
                if (online) listener.listen(new ViewNewProfessorRequest());
                break;
            case "goSelectUnitPane":
                if (online) listener.listen(new ViewSUListRequest());
                break;
            case "goChatroom":
                if (online) controller.showChatroom();
                else controller.offlineChatroom();
                break;
            case "goStudentsList":
                if (online) controller.showStudentList();
                break;
            case "goSUDemandsList":
                if (online) controller.showSUDemandList();
                break;
        }
    }
}
