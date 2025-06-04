package shared.response;

import shared.model.edu.demand.*;
import shared.model.edu.user.Student;
import shared.response.edu.authentication.*;
import shared.response.edu.course.list.RefreshExamListResponse;
import shared.response.edu.course.list.RefreshScheduleResponse;
import shared.response.edu.course.list.*;
import shared.response.edu.course.view.*;
import shared.response.edu.demand.RefreshSUDemandListResponse;
import shared.response.edu.grade.educationalstate.RefreshEducationalStateResponse;
import shared.response.edu.grade.list.RefreshProfessorGradeListResponse;
import shared.response.edu.grade.list.RefreshStudentGradeListResponse;
import shared.response.edu.grade.educationalstate.ViewEducationalStateResponse;
import shared.response.edu.grade.list.RefreshViceChairGradeListResponse;
import shared.response.edu.grade.list.ViewGradeListResponse;
import shared.response.edu.main.RefreshMainMenuResponse;
import shared.response.edu.main.RefreshMainPaneResponse;
import shared.response.edu.user.profile.*;
import shared.response.edu.user.list.*;
import shared.response.edu.user.view.edit.*;
import shared.response.edu.user.view.newuser.*;
import shared.response.messenger.RefreshChatroomResponse;
import shared.response.myedu.ViewSUListResponse;
import shared.response.myedu.professor.RefreshProfessorSUListResponse;
import shared.response.myedu.student.RefreshStudentSUListResponse;
import shared.response.myedu.student.StudentSUActionResponse;

import java.util.LinkedList;

public interface ResponseHandler {
    // authentication
    void login(LoginResponse response);
    void changeCaptcha(ChangeCaptchaResponse response);
    void changePassword(ChangePasswordResponse response);

    // main
    void refreshMainPane(RefreshMainPaneResponse response);
    void refreshMainMenu(RefreshMainMenuResponse response);

    // course list
    void viewCourseList(ViewCourseListResponse response);
    void refreshCourseList(RefreshCourseListResponse response);
    void refreshSchedule(RefreshScheduleResponse response);
    void refreshExamList(RefreshExamListResponse response);

    // profile
    void viewProfile(ViewProfileResponse response);
    void editProfile(EditProfileResponse response);

    // view and edit user
    void viewStudent(ViewStudentResponse response);
    void editStudent(EditStudentResponse response);
    void viewProfessor(ViewProfessorResponse response);
    void editProfessor(EditProfessorResponse response);

    // new user
    void newStudent(NewStudentResponse response);
    void viewNewStudent(ViewNewStudentResponse response);
    void newProfessor(NewProfessorResponse response);
    void viewNewProfessor(ViewNewProfessorResponse response);

    // professor list
    void refreshProfessorList(RefreshProfessorListResponse response);

    // view course
    void viewEditCourse(ViewEditCourseResponse response);
    void editCourse(EditCourseResponse response);
    void viewNewCourse(ViewNewCourseResponse response);
    void newCourse(NewCourseResponse response);

    // educational state
    void viewEducationalState(ViewEducationalStateResponse response);
    void refreshEducationalState(RefreshEducationalStateResponse response);

    // grade list
    void viewGradeList(ViewGradeListResponse response);
    void refreshStudentGradeList(RefreshStudentGradeListResponse response);
    void refreshProfessorGradeList(RefreshProfessorGradeListResponse response);
    void refreshViceChairGradeList(RefreshViceChairGradeListResponse response);

    // demand
    void refreshMinorView(MinorDemand demand);
    void refreshDormView(DormDemand demand);
    void refreshRecommendView(RecommendDemand demand);
    void refreshWithdrawView(WithdrawDemand demand);
    void refreshDissertationView(DissertationDemand demand);
    void refreshConfirmationView(ConfirmationDemand demand);
    void refreshMinorList(LinkedList<MinorDemand> in, LinkedList<MinorDemand> out);
    void refreshRecommendList(LinkedList<RecommendDemand> list);
    void refreshWithdrawList(LinkedList<WithdrawDemand> list);
    void refreshDissertationList(LinkedList<DissertationDemand> list);
    void refreshSUDemandList(LinkedList<SelectUnitDemand> list, boolean answerView);

    //
    void viewSUList(ViewSUListResponse response);
    void refreshStudentSUList(RefreshStudentSUListResponse response);
    void studentSUAction(StudentSUActionResponse response);
    void refreshProfessorSUList(RefreshProfessorSUListResponse response);

    //
    void refreshStudentList(LinkedList<Student> studentsList);
    void refreshChatroom(RefreshChatroomResponse response);
}


