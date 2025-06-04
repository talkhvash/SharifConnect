package shared.request;

import shared.model.edu.Department;
import shared.model.edu.course.Course;
import shared.model.edu.user.Professor;
import shared.model.edu.user.Student;
import shared.request.edu.authentication.ChangePasswordForm;
import shared.request.edu.authentication.LoginForm;
import shared.request.edu.user.list.RefreshStudentListRequest;
import shared.request.edu.user.profile.*;
import shared.request.messenger.RefreshChatroomRequest;
import shared.request.messenger.SendMessageRequest;
import shared.request.myedu.student.SelectUnitListSort;
import shared.request.myedu.student.StudentSUActionType;
import shared.response.edu.authentication.ChangeCaptchaResponse;
import shared.response.edu.authentication.ChangePasswordResponse;
import shared.response.edu.authentication.LoginResponse;
import shared.response.edu.course.list.RefreshExamListResponse;
import shared.response.edu.course.list.RefreshCourseListResponse;
import shared.response.edu.course.list.ViewCourseListResponse;
import shared.response.edu.course.list.RefreshScheduleResponse;
import shared.response.edu.course.view.EditCourseResponse;
import shared.response.edu.course.view.NewCourseResponse;
import shared.response.edu.course.view.ViewEditCourseResponse;
import shared.response.edu.course.view.ViewNewCourseResponse;
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
import shared.response.edu.user.view.edit.EditProfessorResponse;
import shared.response.edu.user.view.edit.EditStudentResponse;
import shared.response.edu.user.view.edit.ViewProfessorResponse;
import shared.response.edu.user.view.edit.ViewStudentResponse;
import shared.response.edu.user.view.newuser.NewProfessorResponse;
import shared.response.edu.user.view.newuser.NewStudentResponse;
import shared.response.edu.user.view.newuser.ViewNewProfessorResponse;
import shared.response.edu.user.view.newuser.ViewNewStudentResponse;
import shared.response.messenger.RefreshChatroomResponse;
import shared.response.myedu.ViewSUListResponse;
import shared.response.myedu.professor.RefreshProfessorSUListResponse;
import shared.response.myedu.student.RefreshStudentSUListResponse;
import shared.response.myedu.student.StudentSUActionResponse;

import java.time.LocalDateTime;
import java.util.LinkedList;

public interface RequestHandler {
    // authentication
    LoginResponse login(LoginForm form);
    ChangeCaptchaResponse changeCaptcha(int captchaId);
    ChangePasswordResponse changePassword(ChangePasswordForm form);

    // main
    RefreshMainPaneResponse refreshMainPane();
    RefreshMainMenuResponse refreshMainMenu();

    // course list
    ViewCourseListResponse viewCourseList();
    RefreshCourseListResponse refreshCourseList(FilterCourseForm form);
    void deleteCourse(Integer courseId);
    RefreshExamListResponse refreshExamList();
    RefreshScheduleResponse refreshSchedule();
    // view course
    ViewEditCourseResponse viewEditCourse(Integer id);
    ViewNewCourseResponse viewNewCourse();
    NewCourseResponse newCourse(Course course);
    EditCourseResponse editCourse(Integer id, Course course);

    // professor list
    RefreshProfessorListResponse refreshProfessorList();
    void deleteProfessor(Integer id);
    void setViceChair(Integer id);
    // view professor
    ViewProfessorResponse viewProfessor(int professorId);
    EditProfessorResponse editProfessor(Professor professor, int professorId);
    ViewNewProfessorResponse viewNewProfessor();
    NewProfessorResponse newProfessor(Professor professor);
    // view student
    ViewStudentResponse viewStudent(int studentId);
    EditStudentResponse editStudent(Student student, int studentId);
    ViewNewStudentResponse viewNewStudent();
    NewStudentResponse newStudent(Student student);

    // profile
    ViewProfileResponse viewProfile();
    EditProfileResponse editProfile(EditProfileForm form);

    // grade
    ViewEducationalStateResponse viewEducationalState();
    RefreshEducationalStateResponse refreshEducationalState(String name, Integer id);
    ViewGradeListResponse viewGradeList();
    // student
    RefreshStudentGradeListResponse refreshStudentGradeList();
    void changeObjection(String text, int id, boolean assign);
    // professor
    RefreshProfessorGradeListResponse refreshProfessorGradeList(Course course);
    void refreshGrade(Integer id, Float number, String stringAnswer, boolean assignAnswer);
    void refreshCourseGrade(Integer id, boolean finalized);
    // viceChair
    RefreshViceChairGradeListResponse refreshViceChairGradeList(String studentName,
                                                                String professorName,
                                                                Integer courseId,
                                                                Integer studentId);

    // demand
    RefreshWithdrawResponse refreshWithdrawView();
    RefreshWithdrawListResponse refreshWithdrawList();
    RefreshMinorResponse refreshMinorView();
    RefreshMinorListResponse refreshMinorList();
    RefreshDissertationResponse refreshDissertationView();
    RefreshDissertationListResponse refreshDissertationList();
    RefreshRecommendResponse refreshRecommendView();
    RefreshRecommendListResponse refreshRecommendList();
    RefreshDormResponse refreshDormView();
    RefreshConfirmationResponse refreshConfirmationView();
    RefreshSUDemandListResponse refreshSUDemandList();
    void answerMinor(Integer id, boolean result);
    void answerWithdraw(Integer id, boolean result);
    void answerRecommend(Integer id, boolean result);
    void answerDissertation(Integer id, LocalDateTime dt);
    void answerSelectUnit(Integer id, boolean result);
    void demandDissertation();
    void demandWithdraw();
    void demandDorm();
    void demandConfirmation();
    void demandRecommend(String proName);
    void demandMinor(String depName);
    void demandSelectUnit(Integer courseId);

    // student su
    ViewSUListResponse viewSUList();
    RefreshStudentSUListResponse refreshStudentSUList(Department department, SelectUnitListSort sort, boolean tab);
    StudentSUActionResponse studentSUActionResponse(StudentSUActionType type, Integer courseId, Integer groupId);
    RefreshProfessorSUListResponse refreshProfessorSUList(FilterStudentForm form);
    void setStudentSUTime(LinkedList<Integer> studentSUTime, LocalDateTime time);

    //
    RefreshStudentListResponse refreshStudentList(Integer idPart);

    // messenger
    void sendMessage(SendMessageRequest request);
    RefreshChatroomResponse refreshChatroom();
}
