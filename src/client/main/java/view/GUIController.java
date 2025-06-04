package client.main.java.view;

import client.main.java.constants.Constants;
import client.main.java.controller.Connection;
import client.main.java.view.FXML.edu.demand.selectunit.SelectUnitDemandList;
import client.main.java.view.FXML.edu.user.list.student.StudentList;
import client.main.java.view.FXML.messenger.chatroom.Chatroom;
import javafx.application.Platform;
import javafx.stage.Stage;
import client.main.java.client.RequestListener;
import client.main.java.util.Loop;
import client.main.java.view.FXML.edu.authentication.changePassword.ChangePasswordPage;
import client.main.java.view.FXML.edu.authentication.login.LoginPage;
import client.main.java.view.FXML.edu.course.exam.ExamList;
import client.main.java.view.FXML.edu.course.list.CourseList;
import client.main.java.view.FXML.edu.course.schedule.Schedule;
import client.main.java.view.FXML.edu.course.view.CourseView;
import client.main.java.view.FXML.edu.demand.professor.dissertation.DissertationList;
import client.main.java.view.FXML.edu.demand.professor.minor.MinorList;
import client.main.java.view.FXML.edu.demand.professor.recommend.RecommendList;
import client.main.java.view.FXML.edu.demand.professor.withdraw.WithdrawList;
import client.main.java.view.FXML.edu.demand.student.confirmation.ConfirmationView;
import client.main.java.view.FXML.edu.demand.student.dissertation.DissertationView;
import client.main.java.view.FXML.edu.demand.student.dorm.DormView;
import client.main.java.view.FXML.edu.demand.student.minor.MinorView;
import client.main.java.view.FXML.edu.demand.student.recommend.RecommendView;
import client.main.java.view.FXML.edu.demand.student.withdraw.WithdrawView;
import client.main.java.view.FXML.edu.grade.educationalstate.EducationalState;
import client.main.java.view.FXML.edu.grade.list.professor.ProfessorGradeList;
import client.main.java.view.FXML.edu.grade.list.student.StudentGradeList;
import client.main.java.view.FXML.edu.grade.list.vicechair.ViceChairGradeList;
import client.main.java.view.FXML.edu.main.menu.MainMenu;
import client.main.java.view.FXML.edu.main.MainPage;
import client.main.java.view.FXML.edu.user.list.professor.ProfessorList;
import client.main.java.view.FXML.edu.main.pane.MainPane;
import client.main.java.view.FXML.edu.user.profile.professor.ProfessorProfilePane;
import client.main.java.view.FXML.edu.user.profile.student.StudentProfilePane;
import client.main.java.view.FXML.edu.user.view.professor.ProfessorView;
import client.main.java.view.FXML.edu.user.view.student.StudentView;
import client.main.java.view.FXML.myedu.professor.ProfessorSelectUnitList;
import client.main.java.view.FXML.myedu.student.StudentSelectUnitList;
import shared.config.Config;
import shared.model.edu.Captcha;
import shared.model.edu.Department;
import shared.model.edu.course.Course;
import shared.model.edu.course.Grade;
import shared.model.edu.demand.*;
import shared.model.edu.user.*;
import shared.request.edu.course.list.RefreshExamListRequest;
import shared.request.edu.course.list.RefreshScheduleRequest;
import shared.request.edu.demand.refresh.*;
import shared.request.edu.grade.studentgradelist.RefreshStudentGradeListRequest;
import shared.request.edu.main.RefreshMainMenuRequest;
import shared.request.edu.main.RefreshMainPaneRequest;
import shared.request.edu.user.list.RefreshProfessorListRequest;
import shared.request.messenger.RefreshChatroomRequest;
import shared.response.edu.authentication.ChangePasswordErrorForm;
import shared.response.edu.authentication.LoginFormError;
import shared.response.edu.course.view.CourseViewError;
import shared.response.edu.user.profile.EditProfileErrorForm;
import shared.response.edu.user.view.ViewProfessorErrorForm;
import shared.response.edu.user.view.ViewStudentErrorForm;
import shared.response.messenger.RefreshChatroomResponse;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;

public class GUIController {
    private static GUIController guiController;

    public static GUIController getController() {
        if (guiController == null) guiController = new GUIController();
        return guiController;
    }

    private GUIController() {
        fps = new Config(Constants.CONFIG).getProperty(Float.class, "refreshLoop");
    }

    private Stage stage;

    private RequestListener listener;
    private Loop refreshLoop;
    private Loop menuRefresher;
    private final float fps;

    // authentication
    private LoginPage loginPage;
    private ChangePasswordPage changePasswordPage;
    // main
    private MainPage mainPage;
    private MainMenu mainMenu;
    private MainPane mainPane;
    // user
    private StudentProfilePane studentProfilePane;
    private ProfessorProfilePane professorProfilePane;
    private StudentView studentView;
    private ProfessorView professorView;
    private ProfessorList professorList;
    // course
    private CourseView courseView;
    private CourseList courseList;
    private Schedule schedule;
    private ExamList examList;
    // grade
    private EducationalState educationalState;
    private ProfessorGradeList professorGradeList;
    private StudentGradeList studentGradeList;
    private ViceChairGradeList viceChairGradeList;
    // demand
    private MinorView minorView;
    private MinorList minorList;
    private WithdrawList withdrawList;
    private WithdrawView withdrawView;
    private DissertationView dissertationView;
    private DissertationList dissertationList;
    private RecommendView recommendView;
    private RecommendList recommendList;
    private ConfirmationView confirmationView;
    private DormView dormView;
    //
    private StudentSelectUnitList studentSelectUnitList;
    private ProfessorSelectUnitList professorSelectUnitList;
    //
    private StudentList studentList;
    //
    private Chatroom chatroom;
    //
    private SelectUnitDemandList selectUnitDemandList;


    public void initialize() {
        Platform.runLater(() -> {
            stage.setTitle(new Config(Constants.CONFIG).getProperty(String.class, "name"));
            stage.setResizable(false);
            showLoginPage();
            stage.show();
        });
    }

    // loop
    public void stopLoops() {
        if (refreshLoop != null) refreshLoop.stop();
        if (menuRefresher != null) menuRefresher.stop();
    }

    // login
    public void showLoginPage() {
        Platform.runLater(() -> {
            loginPage = new LoginPage();
            loginPage.getFXML().refresh();
            stage.setScene(loginPage.getScene());
        });
    }

    public void setLoginPageErr(LoginFormError formError) {
        Platform.runLater(() -> loginPage.getFXML().setError(formError));
    }

    public void changeLoginPageCaptcha(Captcha captcha) {
        Platform.runLater(() -> loginPage.getFXML().setCaptcha(captcha));
    }

    // change password
    public void showChangePasswordPage() {
        Platform.runLater(() -> {
            changePasswordPage = new ChangePasswordPage();
            stage.setScene(changePasswordPage.getScene());
        });
    }

    public void setChangePasswordPageErr(ChangePasswordErrorForm errorForm) {
        Platform.runLater(() -> {
            changePasswordPage.getFXML().setError(errorForm);
            stage.setScene(changePasswordPage.getScene());
        });
    }

    // main page
    public void showMainPage() {
        stopLoop();

        Platform.runLater(() -> {
            mainPage = MainPage.getInstance();
            mainMenu = new MainMenu();
            mainPage.setMenuPane(mainMenu.getPane());
            mainPane = new MainPane();
            mainPage.setMainPane(mainPane.getPane());
        });

        menuRefresher = new Loop(fps, () -> listener.listen(new RefreshMainMenuRequest()));
        menuRefresher.start();
        startLoop(() -> listener.listen(new RefreshMainPaneRequest()));
    }

    public void refreshMainMenu(User viewer, LocalDateTime time, boolean selectUnitMenuItem) {
        Platform.runLater(() -> mainMenu.getFXML().refresh(viewer, selectUnitMenuItem, time));
    }

    public void refreshMainPane(User user) {
        Platform.runLater(() -> mainPane.getFXML().refresh(user));
    }

    // course list
    public void showCourseList(LinkedList<Department> list) {
        stopLoop();
        Platform.runLater(() -> {
            courseList = new CourseList();
            courseList.getFXML().initialize(list);
            mainPage.setMainPane(courseList.getPane());
        });
    }

    public void refreshCourseList(User viewer, LinkedList<Course> list) {
        Platform.runLater(() -> courseList.getFXML().refresh(viewer, list));
    }

    // new and edit course
    public void showEditCoursePane(Course course) {
        stopLoop();
        Platform.runLater(() -> {
            courseView = new CourseView();
            courseView.getFXML().initializeEditView(course);
            mainPage.setMainPane(courseView.getPane());
        });
    }

    public void showNewCoursePane(Professor viewer) {
        stopLoop();
        Platform.runLater(() -> {
            courseView = new CourseView();
            courseView.getFXML().initializeNewView(viewer);
            mainPage.setMainPane(courseView.getPane());
        });
    }

    public void setCourseViewError(CourseViewError error) {
        Platform.runLater(() -> courseView.getFXML().setError(error));
    }

    // professor list
    public void showProfessorList() {
        stopLoop();
        Platform.runLater(() -> {
            professorList = new ProfessorList();
            mainPage.setMainPane(professorList.getPane());
        });
        startLoop(() -> listener.listen(new RefreshProfessorListRequest()));
    }

    public void refreshProfessorList(User viewer, LinkedList<Professor> list) {
        Platform.runLater(() -> professorList.getFXML().refresh(viewer, list));
    }

    public void showExamList() {
        stopLoop();
        Platform.runLater(() -> {
            examList = new ExamList();
            mainPage.setMainPane(examList.getPane());
        });
        startLoop(() -> listener.listen(new RefreshExamListRequest()));
    }

    public void refreshExamList(LinkedList<Course> list) {
        Platform.runLater(() -> examList.getFXML().refresh(list));
    }

    public void showSchedule() {
        stopLoop();
        Platform.runLater(() -> {
            schedule = new Schedule();
            mainPage.setMainPane(schedule.getPane());
        });
        startLoop(() -> listener.listen(new RefreshScheduleRequest()));
    }

    public void refreshSchedule(LinkedList<Course> list) {
        Platform.runLater(() -> schedule.getFXML().refresh(list));
    }

    // edit and new user
    public void showNewStudentPane(Professor viewer) {
        stopLoop();
        Platform.runLater(() -> {
            studentView = new StudentView();
            studentView.getFXML().initializeNewView(viewer);
            mainPage.setMainPane(studentView.getPane());
        });
    }

    public void showEditStudentPane(Student student) {
        stopLoop();
        Platform.runLater(() -> {
            studentView = new StudentView();
            studentView.getFXML().initializeEditView(student);
            mainPage.setMainPane(studentView.getPane());
        });
    }

    public void setViewStudentError(ViewStudentErrorForm form) {
        Platform.runLater(() -> studentView.getFXML().setError(form));
    }

    public void showNewProfessorPane(Professor viewer) {
        stopLoop();
        Platform.runLater(() -> {
            professorView = new ProfessorView();
            professorView.getFXML().initializeNewView(viewer);
            mainPage.setMainPane(professorView.getPane());
        });
    }

    public void showEditProfessorPane(Professor professor) {
        stopLoop();
        Platform.runLater(() -> {
            professorView = new ProfessorView();
            professorView.getFXML().initializeEditView(professor);
            mainPage.setMainPane(professorView.getPane());
        });
    }

    public void setViewProfessorError(ViewProfessorErrorForm form) {
        Platform.runLater(() -> professorView.getFXML().setError(form));
    }

    // profile
    public void showStudentProfile(Student student) {
        stopLoop();
        Platform.runLater(() -> {
            studentProfilePane = new StudentProfilePane();
            studentProfilePane.getFXML().initialize(student);
            mainPage.setMainPane(studentProfilePane.getPane());
        });
    }

    public void setStudentProfileError(EditProfileErrorForm errorForm) {
        Platform.runLater(() -> studentProfilePane.getFXML().setError(errorForm));
    }

    public void showProfessorProfile(Professor professor) {
        stopLoop();
        Platform.runLater(() -> {
            professorProfilePane = new ProfessorProfilePane();
            professorProfilePane.getFXML().initialize(professor);
            mainPage.setMainPane(professorProfilePane.getPane());
        });
    }

    public void setProfessorProfileError(EditProfileErrorForm errorForm) {
        Platform.runLater(() -> professorProfilePane.getFXML().setError(errorForm));
    }

    // educational state
    public void showEducationalList(User viewer) {
        stopLoop();
        Platform.runLater(() -> {
            educationalState = new EducationalState();
            educationalState.getFXML().initialize(viewer);
            mainPage.setMainPane(educationalState.getPane());
        });
    }

    public void refreshEducationalState(LinkedList<Grade> list, Student student) {
        Platform.runLater(() -> educationalState.getFXML().refresh(list, student));
    }

    // grade list
    public void showProfessorGradeList(LinkedList<Course> courses) {
        stopLoop();
        Platform.runLater(() -> {
            professorGradeList = new ProfessorGradeList();
            professorGradeList.getFXML().initialize(courses);
            mainPage.setMainPane(professorGradeList.getPane());
        });
    }

    public void refreshProfessorGradeList(LinkedList<Grade> grades, boolean isTemporary, Integer courseId) {
        Platform.runLater(() -> professorGradeList.getFXML().refresh(grades, isTemporary, courseId));
    }

    public void showStudentGradeList() {
        stopLoop();
        Platform.runLater(() -> {
            studentGradeList = new StudentGradeList();
            mainPage.setMainPane(studentGradeList.getPane());
        });
        startLoop(() -> listener.listen(new RefreshStudentGradeListRequest()));
    }

    public void refreshStudentGradeList(LinkedList<Grade> grades) {
        Platform.runLater(() -> studentGradeList.getFXML().initialize(grades));
    }

    public void showViceChairGradeList(LinkedList<Course> courses) {
        stopLoop();
        Platform.runLater(() -> {
            viceChairGradeList = new ViceChairGradeList();
            viceChairGradeList.getFXML().initialize(courses);
            mainPage.setMainPane(viceChairGradeList.getPane());
        });
    }

    public void refreshViceChairGradeListStudent(LinkedList<Grade> grades, boolean studentView) {
        Platform.runLater(() -> viceChairGradeList.getFXML().refresh(grades, studentView));
    }

    public void refreshViceChairGradeListProfessor(HashMap<Course, LinkedList<Grade>> map) {
        Platform.runLater(() -> viceChairGradeList.getFXML().refresh(map));
    }

    // demand **********************************************************************************************************
    public void showDormView() {
        stopLoop();
        Platform.runLater(() -> {
            dormView = new DormView();
            mainPage.setMainPane(dormView.getPane());
        });
        startLoop(() -> listener.listen(new RefreshDormRequest()));
    }

    public void refreshDormView(DormDemand demand) {
        Platform.runLater(() -> dormView.getFXML().refresh(demand));
    }

    public void showConfirmationView() {
        stopLoop();
        Platform.runLater(() -> {
            confirmationView = new ConfirmationView();
            mainPage.setMainPane(confirmationView.getPane());
        });
        startLoop(() -> listener.listen(new RefreshConfirmationRequest()));
    }

    public void refreshConfirmationView(ConfirmationDemand demand) {
        Platform.runLater(() -> confirmationView.getFXML().refresh(demand));
    }

    public void showDissertationView() {
        stopLoop();
        Platform.runLater(() -> {
            dissertationView = new DissertationView();
            mainPage.setMainPane(dissertationView.getPane());
        });
        startLoop(() -> listener.listen(new RefreshDissertationRequest()));
    }

    public void refreshDissertationView(DissertationDemand demand) {
        Platform.runLater(() -> dissertationView.getFXML().refresh(demand));
    }

    public void showDissertationList() {
        stopLoop();
        Platform.runLater(() -> {
            dissertationList = new DissertationList();
            mainPage.setMainPane(dissertationList.getPane());
        });
        startLoop(() -> listener.listen(new RefreshDissertationListRequest()));
    }

    public void refreshDissertationList(LinkedList<DissertationDemand> list) {
        Platform.runLater(() -> dissertationList.getFXML().refresh(list));
    }

    public void showWithdrawView() {
        stopLoop();
        Platform.runLater(() -> {
            withdrawView = new WithdrawView();
            mainPage.setMainPane(withdrawView.getPane());
        });
        startLoop(() -> listener.listen(new RefreshWithdrawRequest()));
    }

    public void refreshWithdrawView(WithdrawDemand demand) {
        Platform.runLater(() -> withdrawView.getFXML().refresh(demand));
    }

    public void showWithdrawList() {
        stopLoop();
        Platform.runLater(() -> {
            withdrawList = new WithdrawList();
            mainPage.setMainPane(withdrawList.getPane());
        });
        startLoop(() -> listener.listen(new RefreshWithdrawListRequest()));
    }

    public void refreshWithdrawList(LinkedList<WithdrawDemand> list) {
        Platform.runLater(() -> withdrawList.getFXML().refresh(list));
    }

    public void showRecommendView() {
        stopLoop();
        Platform.runLater(() -> {
            recommendView = new RecommendView();
            mainPage.setMainPane(recommendView.getPane());
        });
        startLoop(() -> listener.listen(new RefreshRecommendRequest()));
    }

    public void refreshRecommendView(RecommendDemand demand) {
        Platform.runLater(() -> recommendView.getFXML().refresh(demand));
    }

    public void showRecommendList() {
        stopLoop();
        Platform.runLater(() -> {
            recommendList = new RecommendList();
            mainPage.setMainPane(recommendList.getPane());
        });
        startLoop(() -> listener.listen(new RefreshRecommendListRequest()));
    }

    public void refreshRecommendList(LinkedList<RecommendDemand> list) {
        Platform.runLater(() -> recommendList.getFXML().refresh(list));
    }

    public void showMinorView() {
        stopLoop();
        Platform.runLater(() -> {
            minorView = new MinorView();
            mainPage.setMainPane(minorView.getPane());
        });
        startLoop(() -> listener.listen(new RefreshMinorRequest()));
    }

    public void refreshMinorView(MinorDemand demand) {
        Platform.runLater(() -> minorView.getFXML().refresh(demand));
    }

    public void showMinorList() {
        stopLoop();
        Platform.runLater(() -> {
            minorList = new MinorList();
            mainPage.setMainPane(minorList.getPane());
        });
        startLoop(() -> listener.listen(new RefreshMinorListRequest()));
    }

    public void refreshMinorList(LinkedList<MinorDemand> listIn, LinkedList<MinorDemand> listOut) {
        Platform.runLater(() -> minorList.getFXML().refresh(listIn, listOut));
    }

    // student su
    public void showStudentSUList(LinkedList<Department> list) {
        stopLoop();
        Platform.runLater(() -> {
            studentSelectUnitList = new StudentSelectUnitList();
            studentSelectUnitList.getFXML().initialize(list);
            mainPage.setMainPane(studentSelectUnitList.getPane());
        });
    }

    public void refreshStudentSUList(LinkedList<Course> list, Student viewer) {
        Platform.runLater(() -> studentSelectUnitList.getFXML().refresh(list, viewer));
    }

    public void setStudentSUListError(String error) {
        Platform.runLater(() -> studentSelectUnitList.getFXML().setError(error));
    }

    // professor su
    public void showProfessorSUList() {
        stopLoop();
        Platform.runLater(() -> {
            professorSelectUnitList = new ProfessorSelectUnitList();
            professorSelectUnitList.getFXML().initialize();
            mainPage.setMainPane(professorSelectUnitList.getPane());
        });
    }

    public void refreshProfessorSUList(LinkedList<Student> list) {
        Platform.runLater(() -> professorSelectUnitList.getFXML().refresh(list));
    }

    // student list
    public void showStudentList() {
        stopLoop();
        Platform.runLater(() -> {
            studentList = new StudentList();
            mainPage.setMainPane(studentList.getPane());
        });
    }

    public void refreshStudentList(LinkedList<Student> studentsList) {
        Platform.runLater(() -> studentList.getFXML().refresh(studentsList));
    }

    // messenger
    public void showChatroom() {
        stopLoop();
        Platform.runLater(() -> {
            chatroom = new Chatroom();
            chatroom.getFXML().initialize();
            stage.setScene(chatroom.getScene());
        });
        startLoop(() -> listener.listen(new RefreshChatroomRequest()));
    }

    public void refreshChatroom(RefreshChatroomResponse response) {
        Platform.runLater(() -> chatroom.getFXML().refresh(
                response.getChats(),
                response.getMessages(),
                response.getUsers(),
                response.getViewer()));
    }

    public void showSUDemandList() {
        stopLoop();
        Platform.runLater(() -> {
            selectUnitDemandList = new SelectUnitDemandList();
            mainPage.setMainPane(selectUnitDemandList.getPane());
        });
        startLoop(() -> listener.listen(new RefreshSUDemandListRequest()));
    }

    public void refreshSUDemandList(LinkedList<SelectUnitDemand> demandsList, boolean answerView) {
        Platform.runLater(() -> selectUnitDemandList.getFXML().refresh(demandsList, answerView));
    }

    // getters and setters
    public void startLoop(Runnable runnable) {
        if (refreshLoop != null) refreshLoop.stop();
        refreshLoop = new Loop(fps, runnable);
        refreshLoop.start();
    }

    private void stopLoop() {
        if (refreshLoop != null) refreshLoop.stop();
    }

    public RequestListener getListener() {
        return listener;
    }

    public void setListener(RequestListener listener) {
        this.listener = listener;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    // offline
    public void getOfflineMode() {
        User user = Connection.getStatus().getUser();
        if (user instanceof Admin || user instanceof MrMohseni) {
            offlineChatroom();
        } else {
            offlineMainPage();
        }
    }

    public void offlineProfile() {
        User user = Connection.getStatus().getUser();
        if (user instanceof Professor) {
            if (professorProfilePane == null) return;
            Platform.runLater(() -> {
                professorProfilePane.getFXML().offlineView();
                mainPage.setMainPane(professorProfilePane.getPane());
            });
        } else if (user instanceof Student) {
            if (studentProfilePane == null) return;
            Platform.runLater(() -> {
                studentProfilePane.getFXML().offlineView();
                mainPage.setMainPane(studentProfilePane.getPane());
            });
        }
    }

    public void offlineEducationalState() {
        User user = Connection.getStatus().getUser();
        if (user instanceof Student) {
            if (educationalState == null) return;
            Platform.runLater(() -> mainPage.setMainPane(educationalState.getPane()));
        }
    }

    public void offlineMainPage() {
        User user = Connection.getStatus().getUser();
        if (user instanceof Professor || user instanceof Student) {
            Platform.runLater(() -> {
                mainPage.setMainPane(mainPane.getPane());
                stage.setScene(mainPage.getScene());
            });
        }
    }

    public void offlineSchedule() {
        User user = Connection.getStatus().getUser();
        if (user instanceof Student || user instanceof Professor) {
            if (schedule == null) return;
            Platform.runLater(() -> mainPage.setMainPane(schedule.getPane()));
        }
    }

    public void offlineExamList() {
        User user = Connection.getStatus().getUser();
        if (user instanceof Student || user instanceof Professor) {
            if (examList == null) return;
            Platform.runLater(() -> mainPage.setMainPane(examList.getPane()));
        }
    }

    public void offlineChatroom() {
        if (chatroom == null) return;
        else Platform.runLater(() -> stage.setScene(chatroom.getScene()));
    }

}
