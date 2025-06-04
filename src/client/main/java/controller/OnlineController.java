package client.main.java.controller;

import client.main.java.constants.Constants;
import javafx.stage.Stage;
import client.main.java.client.SocketController;
import client.main.java.util.Loop;
import client.main.java.view.GUIController;
import shared.config.Config;
import shared.model.edu.demand.*;
import shared.model.edu.user.*;
import shared.request.Request;
import shared.request.edu.authentication.ChangeCaptchaRequest;
import shared.request.edu.course.view.ViewEditCourseRequest;
import shared.request.edu.user.view.edit.*;
import shared.request.messenger.SendMessageRequest;
import shared.response.Response;
import shared.response.ResponseHandler;
import shared.response.edu.authentication.*;
import shared.response.edu.course.list.*;
import shared.response.edu.course.view.*;
import shared.response.edu.grade.educationalstate.RefreshEducationalStateResponse;
import shared.response.edu.grade.educationalstate.ViewEducationalStateResponse;
import shared.response.edu.grade.list.*;
import shared.response.edu.main.*;
import shared.response.edu.user.profile.*;
import shared.response.edu.user.list.RefreshProfessorListResponse;
import shared.response.edu.user.view.edit.*;
import shared.response.edu.user.view.newuser.*;
import shared.response.messenger.RefreshChatroomResponse;
import shared.response.myedu.ViewSUListResponse;
import shared.response.myedu.professor.RefreshProfessorSUListResponse;
import shared.response.myedu.student.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class OnlineController implements ResponseHandler {
    private final List<Request> requestsList = new LinkedList<>();
    private final GUIController guiController;
    private final Loop requestLoop;

    private SocketController socketController;

    public OnlineController(Stage primaryStage) {
        int fps = new Config(Constants.CONFIG).getProperty(Integer.class, "requestLoop");
        requestLoop = new Loop(fps, this::sendRequests);

        guiController = GUIController.getController();
        guiController.setStage(primaryStage);
        guiController.initialize();
        guiController.setListener(this::addRequest);
    }

    // connection
    public boolean connectToServer(InetAddress host, Integer port) throws IOException {
        try {
            Socket socket = new Socket(host, port);
            socketController = new SocketController(socket);
            requestLoop.start();
            Connection.getStatus().setOnline(true);

            // admin message
            for (SendMessageRequest request : Archive.getInstance().getList()) addRequest(request);

            return true;
        } catch (Exception e) {
            Connection.getStatus().setOnline(false);
            return false;
        }
    }

    // request
    private void addRequest(Request request) {
        synchronized (requestsList) {
            if (request instanceof ChangeCaptchaRequest) System.out.println("55555555555555");
            requestsList.add(request);
        }
    }

    private void sendRequests() {
        List<Request> list;
        synchronized (requestsList) {
            list = new LinkedList<>(requestsList);
            requestsList.clear();
        }
        for (Request item : list) {
            try {
                Response response = socketController.sendRequest(item);
                System.out.println(response);
                System.out.println(577777);
                response.handle(this);
            } catch (Exception e) {
                Connection.getStatus().setOnline(false);

                // admin message
                if (item instanceof SendMessageRequest) {
                    SendMessageRequest request = (SendMessageRequest) item;
                    if (request.getContactId() == 1) {
                        Archive.getInstance().add(request);
                    }
                }

            }
        }
    }

    // authentication **************************************************************************************************
    @Override
    public void login(LoginResponse response) {
        if (response.isResult()) {
            socketController.setAuthToken(response.getAuthToken());
            if (response.isChangePassword()) {
                guiController.showChangePasswordPage();
            } else {
                User user = response.getLoggedInUser();
                if (user instanceof Admin) {
                    guiController.showChatroom();
                } else if (user instanceof MrMohseni){
                    guiController.showStudentList();
                } else {
                    guiController.showMainPage();
                }
            }
        } else {
            guiController.setLoginPageErr(response.getFormError());
        }
    }

    @Override
    public void changeCaptcha(ChangeCaptchaResponse response) {
        guiController.changeLoginPageCaptcha(response.getCaptcha());
    }

    @Override
    public void changePassword(ChangePasswordResponse response) {
        if (response.isResult()) {
            guiController.showMainPage();
        } else {
            guiController.setChangePasswordPageErr(response.getForm());
        }
    }

    // main ************************************************************************************************************
    @Override
    public void refreshMainPane(RefreshMainPaneResponse response) {
        guiController.refreshMainPane(response.getUser());
    }

    @Override
    public void refreshMainMenu(RefreshMainMenuResponse response) {
        guiController.refreshMainMenu(response.getViewer(), response.getTime(), response.isSelectUnitMenuItem());
    }

    // profile *********************************************************************************************************
    @Override
    public void viewProfile(ViewProfileResponse response) {
        User user = response.getUser();
        if (user instanceof Student) {
            guiController.showStudentProfile((Student) user);
        } else if (user instanceof Professor) {
            guiController.showProfessorProfile((Professor) user);
        }
    }

    @Override
    public void editProfile(EditProfileResponse response) {
        User user = response.getUser();
        if (user instanceof Student) {
            guiController.setStudentProfileError(response.getErrorForm());
        } else if (user instanceof Professor) {
            guiController.setProfessorProfileError(response.getErrorForm());
        }
    }

    // view and edit user **********************************************************************************************
    @Override
    public void viewStudent(ViewStudentResponse response) {
        guiController.showEditStudentPane(response.getStudent());
    }

    @Override
    public void editStudent(EditStudentResponse response) {
        guiController.setViewStudentError(response.getErrorForm());
    }

    @Override
    public void viewProfessor(ViewProfessorResponse response) {
        guiController.showEditProfessorPane(response.getProfessor());
    }

    @Override
    public void editProfessor(EditProfessorResponse response) {
        guiController.setViewProfessorError(response.getErrorForm());
    }

    // new user ********************************************************************************************************
    @Override
    public void newStudent(NewStudentResponse response) {
        if (response.isResult()) {
            addRequest(new ViewStudentRequest(response.getStudentId()));
        } else {
            guiController.setViewStudentError(response.getErrorForm());
        }
    }

    @Override
    public void viewNewStudent(ViewNewStudentResponse response) {
        guiController.showNewStudentPane(response.getViewer());
    }

    @Override
    public void newProfessor(NewProfessorResponse response) {
        if (response.isResult()) {
            addRequest(new ViewProfessorRequest(response.getProfessorId()));
        } else {
            guiController.setViewProfessorError(response.getErrorForm());
        }
    }

    @Override
    public void viewNewProfessor(ViewNewProfessorResponse response) {
        guiController.showNewProfessorPane(response.getViewer());
    }

    // professor list
    @Override
    public void refreshProfessorList(RefreshProfessorListResponse response) {
        guiController.refreshProfessorList(response.getViewer(), response.getList());
    }

    // view course
    @Override
    public void viewEditCourse(ViewEditCourseResponse response) {
        guiController.showEditCoursePane(response.getCourse());
    }

    @Override
    public void editCourse(EditCourseResponse response) {
        guiController.setCourseViewError(response.getError());
    }

    @Override
    public void viewNewCourse(ViewNewCourseResponse response) {
        guiController.showNewCoursePane(response.getViewer());
    }

    @Override
    public void newCourse(NewCourseResponse response) {
        if (response.isResult()) {
            addRequest(new ViewEditCourseRequest(response.getCourseId()));
        } else {
            guiController.setCourseViewError(response.getError());
        }
    }

    // course list
    @Override
    public void viewCourseList(ViewCourseListResponse response) {
        guiController.showCourseList(response.getDepList());
    }

    @Override
    public void refreshCourseList(RefreshCourseListResponse response) {
        guiController.refreshCourseList(response.getViewer(), response.getList());
    }

    @Override
    public void refreshSchedule(RefreshScheduleResponse response) {
        guiController.refreshSchedule(response.getList());
    }

    @Override
    public void refreshExamList(RefreshExamListResponse response) {
        guiController.refreshExamList(response.getList());
    }

    // educational state
    @Override
    public void viewEducationalState(ViewEducationalStateResponse response) {
        guiController.showEducationalList(response.getViewer());
    }

    @Override
    public void refreshEducationalState(RefreshEducationalStateResponse response) {
        guiController.refreshEducationalState(
                response.getList(),
                response.getStudent()
        );
    }

    // grade list
    @Override
    public void viewGradeList(ViewGradeListResponse response) {
        if (response.getUser() == null) guiController.showStudentGradeList();
        else if (response.getUser()) guiController.showProfessorGradeList(response.getList());
        else guiController.showViceChairGradeList(response.getList());
    }

    @Override
    public void refreshStudentGradeList(RefreshStudentGradeListResponse response) {
        guiController.refreshStudentGradeList(response.getList());
    }

    @Override
    public void refreshProfessorGradeList(RefreshProfessorGradeListResponse response) {
        guiController.refreshProfessorGradeList(response.getList(), response.getFinalize(), response.getCourseId());
    }

    @Override
    public void refreshViceChairGradeList(RefreshViceChairGradeListResponse response) {
        if (response.getMap() == null) {
            guiController.refreshViceChairGradeListStudent(
                    response.getList(), response.isStudentView());
        } else {
            guiController.refreshViceChairGradeListProfessor(response.getMap());
        }
    }

    // demand
    @Override
    public void refreshMinorView(MinorDemand demand) {
        guiController.refreshMinorView(demand);
    }

    @Override
    public void refreshDormView(DormDemand demand) {
        guiController.refreshDormView(demand);
    }

    @Override
    public void refreshRecommendView(RecommendDemand demand) {
        guiController.refreshRecommendView(demand);
    }

    @Override
    public void refreshWithdrawView(WithdrawDemand demand) {
        guiController.refreshWithdrawView(demand);
    }

    @Override
    public void refreshDissertationView(DissertationDemand demand) {
        guiController.refreshDissertationView(demand);
    }

    @Override
    public void refreshConfirmationView(ConfirmationDemand demand) {
        guiController.refreshConfirmationView(demand);
    }

    @Override
    public void refreshMinorList(LinkedList<MinorDemand> in, LinkedList<MinorDemand> out) {
        guiController.refreshMinorList(in, out);
    }

    @Override
    public void refreshRecommendList(LinkedList<RecommendDemand> list) {
        guiController.refreshRecommendList(list);
    }

    @Override
    public void refreshWithdrawList(LinkedList<WithdrawDemand> list) {
        guiController.refreshWithdrawList(list);
    }

    @Override
    public void refreshDissertationList(LinkedList<DissertationDemand> list) {
        guiController.refreshDissertationList(list);
    }

    @Override
    public void refreshSUDemandList(LinkedList<SelectUnitDemand> list, boolean answerView) {
        guiController.refreshSUDemandList(list, answerView);
    }

    @Override
    public void viewSUList(ViewSUListResponse response) {
        if (response.isResult() == null) guiController.showProfessorSUList();
        else if (response.isResult()) guiController.showStudentSUList(response.getDepartmentList());
        else guiController.showMainPage();
    }

    @Override
    public void refreshStudentSUList(RefreshStudentSUListResponse response) {
        if (response.isResult())
            guiController.refreshStudentSUList(response.getCoursesList(), response.getViewer());
        else
            guiController.showMainPage();
    }

    @Override
    public void studentSUAction(StudentSUActionResponse response) {
        guiController.setStudentSUListError(response.getError());
    }

    @Override
    public void refreshProfessorSUList(RefreshProfessorSUListResponse response) {
        guiController.refreshProfessorSUList(response.getStudentsList());
    }

    @Override
    public void refreshStudentList(LinkedList<Student> studentsList) {
        guiController.refreshStudentList(studentsList);
    }

    @Override
    public void refreshChatroom(RefreshChatroomResponse response) {
        guiController.refreshChatroom(response);
    }


}
