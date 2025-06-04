package server.main.java.controller;

import client.main.java.Setter;
import shared.model.edu.Department;
import shared.model.edu.course.Course;
import shared.model.edu.user.Professor;
import shared.model.edu.user.Student;
import shared.request.FilterCourseForm;
import shared.request.FilterStudentForm;
import shared.request.RequestHandler;
import shared.request.edu.authentication.ChangePasswordForm;
import shared.request.edu.authentication.LoginForm;
import shared.request.edu.user.profile.EditProfileForm;
import shared.request.messenger.SendMessageRequest;
import shared.request.myedu.student.SelectUnitListSort;
import shared.request.myedu.student.StudentSUActionType;
import shared.response.edu.authentication.ChangeCaptchaResponse;
import shared.response.edu.authentication.ChangePasswordResponse;
import shared.response.edu.authentication.LoginResponse;
import shared.response.edu.course.list.*;
import shared.response.edu.course.view.*;
import shared.response.edu.demand.*;
import shared.response.edu.grade.educationalstate.RefreshEducationalStateResponse;
import shared.response.edu.grade.educationalstate.ViewEducationalStateResponse;
import shared.response.edu.grade.list.RefreshProfessorGradeListResponse;
import shared.response.edu.grade.list.RefreshStudentGradeListResponse;
import shared.response.edu.grade.list.RefreshViceChairGradeListResponse;
import shared.response.edu.grade.list.ViewGradeListResponse;
import shared.response.edu.main.RefreshMainMenuResponse;
import shared.response.edu.main.RefreshMainPaneResponse;
import shared.response.edu.user.list.RefreshProfessorListResponse;
import shared.response.edu.user.list.RefreshStudentListResponse;
import shared.response.edu.user.profile.*;
import shared.response.edu.user.view.edit.*;
import shared.response.edu.user.view.newuser.*;
import shared.response.messenger.RefreshChatroomResponse;
import shared.response.myedu.ViewSUListResponse;
import shared.response.myedu.professor.RefreshProfessorSUListResponse;
import shared.response.myedu.student.RefreshStudentSUListResponse;
import shared.response.myedu.student.StudentSUActionResponse;

import java.time.LocalDateTime;
import java.util.LinkedList;


public class ClientHandler implements RequestHandler {
    private Integer loggedInUserId;
    private final AuthController authController;
    private final UserController userController;
    private final CourseController courseController;
    private final DemandController demandController;
    private final GradeController gradeController;
    private final SelectUnitController SUController;
    private final MessengerController messengerController;

    public ClientHandler(Setter<String> authTokenSetter) {
        authController = new AuthController();
        authController.setAuthTokenSetter(authTokenSetter);
        authController.setLoggedInUserIdSetter(field -> loggedInUserId = field);
        demandController = new DemandController();
        userController = new UserController();
        courseController = new CourseController();
        gradeController = new GradeController();
        SUController = new SelectUnitController();
        messengerController = new MessengerController();
    }

    // login ***********************************************************************************************************
    @Override
    public LoginResponse login(LoginForm form) {
        return authController.login(form);
    }

    @Override
    public ChangeCaptchaResponse changeCaptcha(int captchaId) {
        System.out.println("****");
        return authController.changeCaptcha(captchaId);
    }

    @Override
    public ChangePasswordResponse changePassword(ChangePasswordForm form) {
        return authController.changePassword(form, loggedInUserId);
    }

    // main ************************************************************************************************************
    @Override
    public RefreshMainPaneResponse refreshMainPane() {
        return userController.getMainPane(loggedInUserId);
    }

    @Override
    public RefreshMainMenuResponse refreshMainMenu() {
        return userController.getMainMenu(loggedInUserId);
    }

    // profile *********************************************************************************************************
    @Override
    public ViewProfileResponse viewProfile() {
        return userController.getProfile(loggedInUserId);
    }

    @Override
    public EditProfileResponse editProfile(EditProfileForm form) {
        return userController.editProfile(form, loggedInUserId);
    }

    // educational state ***********************************************************************************************

    @Override
    public ViewEducationalStateResponse viewEducationalState() {
        return gradeController.viewEducationalState(loggedInUserId);
    }

    @Override
    public RefreshEducationalStateResponse refreshEducationalState(String name, Integer id) {
        return gradeController.refreshEducationalState(name, id);
    }

    @Override
    public ViewGradeListResponse viewGradeList() {
        return gradeController.viewGradeList(loggedInUserId);
    }

    @Override
    public RefreshStudentGradeListResponse refreshStudentGradeList() {
        return gradeController.refreshStudentGradeList(loggedInUserId);
    }

    @Override
    public void changeObjection(String text, int id, boolean assign) {
        gradeController.changeObjection(text, id,assign);
    }

    @Override
    public RefreshProfessorGradeListResponse refreshProfessorGradeList(Course course) {
        return gradeController.refreshProfessorGradeList(course.getId());
    }

    @Override
    public void refreshGrade(Integer id, Float number, String stringAnswer, boolean assignAnswer) {
        gradeController.refreshGrade(id, number, stringAnswer, assignAnswer);
    }

    @Override
    public void refreshCourseGrade(Integer id, boolean finalized) {
        gradeController.refreshCourse(id, finalized);
    }

    @Override
    public RefreshViceChairGradeListResponse refreshViceChairGradeList(String stuName, String proName, Integer courseId, Integer stuId) {
        return gradeController.refreshViceChairGradeList(stuName,proName,courseId,stuId);
    }

    // course list *****************************************************************************************************
    @Override
    public ViewCourseListResponse viewCourseList() {
        return courseController.viewCourseList(loggedInUserId);
    }

    @Override
    public RefreshCourseListResponse refreshCourseList(FilterCourseForm form) {
        return courseController.refreshCourseList(loggedInUserId, form);
    }

    @Override
    public RefreshExamListResponse refreshExamList() {
        return courseController.refreshExamList(loggedInUserId);
    }

    @Override
    public RefreshScheduleResponse refreshSchedule() {
        return courseController.refreshSchedule(loggedInUserId);
    }

    @Override
    public void deleteCourse(Integer courseId) {
        courseController.deleteCourse(courseId);
    }

    // view and edit course ********************************************************************************************
    @Override
    public ViewEditCourseResponse viewEditCourse(Integer id) {
        return courseController.viewEditCourse(id, loggedInUserId);
    }

    @Override
    public ViewNewCourseResponse viewNewCourse() {
        return courseController.viewNewCourse(loggedInUserId);
    }

    @Override
    public NewCourseResponse newCourse(Course course) {
        return courseController.newCourse(course, loggedInUserId);
    }

    @Override
    public EditCourseResponse editCourse(Integer id, Course course) {
        return courseController.editCourse(id, course);
    }

    // professor list **************************************************************************************************
    @Override
    public RefreshProfessorListResponse refreshProfessorList() {
        return userController.refreshProfessorList(loggedInUserId);
    }

    @Override
    public void setViceChair(Integer id) {
        userController.setViceChair(id);
    }

    @Override
    public void deleteProfessor(Integer id) {
        userController.deleteProfessor(id);
    }

    // view and edit user **********************************************************************************************
    @Override
    public ViewProfessorResponse viewProfessor(int professorId) {
        return userController.viewProfessor(professorId);
    }

    @Override
    public EditProfessorResponse editProfessor(Professor professor, int professorId) {
        return userController.editProfessor(professor, professorId);
    }

    @Override
    public ViewStudentResponse viewStudent(int studentId) {
        return userController.viewStudent(studentId);
    }

    @Override
    public EditStudentResponse editStudent(Student student, int studentId) {
        return userController.editStudent(student, studentId);
    }

    @Override
    public ViewNewProfessorResponse viewNewProfessor() {
        return userController.viewNewProfessor(loggedInUserId);
    }

    @Override
    public NewProfessorResponse newProfessor(Professor professor) {
        return userController.newProfessor(professor);
    }

    @Override
    public ViewNewStudentResponse viewNewStudent() {
        return userController.viewNewStudent(loggedInUserId);
    }

    @Override
    public NewStudentResponse newStudent(Student student) {
        return userController.newStudent(student);
    }

    // demand **********************************************************************************************************
    @Override
    public RefreshWithdrawResponse refreshWithdrawView() {
        return demandController.getWithdraw(loggedInUserId);
    }

    @Override
    public RefreshWithdrawListResponse refreshWithdrawList() {
        return demandController.getWithdrawList(loggedInUserId);
    }

    @Override
    public RefreshMinorResponse refreshMinorView() {
        return demandController.getMinor(loggedInUserId);
    }

    @Override
    public RefreshMinorListResponse refreshMinorList() {
        return demandController.getMinorList(loggedInUserId);
    }

    @Override
    public RefreshDissertationResponse refreshDissertationView() {
        return demandController.getDissertation(loggedInUserId);
    }

    @Override
    public RefreshDissertationListResponse refreshDissertationList() {
        return demandController.getDissertationList(loggedInUserId);
    }

    @Override
    public RefreshRecommendResponse refreshRecommendView() {
        return demandController.getRecommend(loggedInUserId);
    }

    @Override
    public RefreshRecommendListResponse refreshRecommendList() {
        return demandController.getRecommendList(loggedInUserId);
    }

    @Override
    public RefreshDormResponse refreshDormView() {
        return demandController.getDorm(loggedInUserId);
    }

    @Override
    public RefreshConfirmationResponse refreshConfirmationView() {
        return demandController.getConfirmation(loggedInUserId);
    }

    @Override
    public RefreshSUDemandListResponse refreshSUDemandList() {
        return demandController.refreshSelectUnitList(loggedInUserId);
    }

    @Override
    public void answerMinor(Integer id, boolean result) {
        demandController.answerMinor(id, result, loggedInUserId);
    }

    @Override
    public void answerWithdraw(Integer id, boolean result) {
        demandController.answerWithdraw(id, result, loggedInUserId);
    }

    @Override
    public void answerRecommend(Integer id, boolean result) {
        demandController.answerRecommend(id, result, loggedInUserId);
    }

    @Override
    public void answerDissertation(Integer id, LocalDateTime dt) {
        demandController.answerDissertation(id, dt, loggedInUserId);
    }

    @Override
    public void answerSelectUnit(Integer id, boolean result) {
        demandController.answerSelectUnit(id, result);
    }

    @Override
    public void demandDissertation() {
        demandController.demandDissertation(loggedInUserId);
    }

    @Override
    public void demandWithdraw() {
        demandController.demandWithdraw(loggedInUserId);
    }

    @Override
    public void demandDorm() {
        demandController.demandDorm(loggedInUserId);
    }

    @Override
    public void demandConfirmation() {
        demandController.demandConfirmation(loggedInUserId);
    }

    @Override
    public void demandRecommend(String proName) {
        demandController.demandRecommend(proName, loggedInUserId);
    }

    @Override
    public void demandMinor(String depName) {
        demandController.demandMinor(depName, loggedInUserId);
    }

    @Override
    public void demandSelectUnit(Integer courseId) {
        demandController.demandSelectUnit(courseId, loggedInUserId);
    }

    // student select unit *********************************************************************************************
    @Override
    public ViewSUListResponse viewSUList() {
        return SUController.viewList(loggedInUserId);
    }

    @Override
    public RefreshStudentSUListResponse refreshStudentSUList(Department department, SelectUnitListSort sort, boolean tab) {
        return SUController.refreshStudentList(sort, tab, department, loggedInUserId);
    }

    @Override
    public StudentSUActionResponse studentSUActionResponse(StudentSUActionType type, Integer courseId, Integer groupId) {
        return SUController.studentSelectUnit(type, loggedInUserId, courseId, groupId);
    }

    @Override
    public RefreshProfessorSUListResponse refreshProfessorSUList(FilterStudentForm form) {
        return SUController.refreshProfessorList(form, loggedInUserId);
    }

    @Override
    public void setStudentSUTime(LinkedList<Integer> studentSUTime, LocalDateTime time) {
        SUController.setStudentTime(studentSUTime, time, loggedInUserId);
    }

    // mr mohseni
    @Override
    public RefreshStudentListResponse refreshStudentList(Integer idPart) {
        return userController.refreshStudentList();
    }

    // messenger +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    public void sendMessage(SendMessageRequest request) {
        messengerController.sendMessage(request, loggedInUserId);
    }

    @Override
    public RefreshChatroomResponse refreshChatroom() {
        return messengerController.refreshChatroom(loggedInUserId);
    }

}
