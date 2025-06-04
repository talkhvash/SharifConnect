package server.main.java.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import server.main.java.constants.Constants;
import server.main.java.db.DB;
import shared.config.Config;
import shared.model.edu.Department;
import shared.model.edu.course.Course;
import shared.model.edu.demand.Demand;
import shared.model.edu.demand.RecommendDemand;
import shared.request.edu.user.profile.EditProfileForm;
import shared.response.edu.main.RefreshMainMenuResponse;
import shared.response.edu.main.RefreshMainPaneResponse;
import shared.response.edu.user.list.RefreshProfessorListResponse;
import shared.response.edu.user.list.RefreshStudentListResponse;
import shared.response.edu.user.profile.EditProfileResponse;
import shared.model.edu.user.Professor;
import shared.model.edu.user.Student;
import shared.model.edu.user.User;
import shared.response.edu.user.profile.ViewProfileResponse;
import shared.response.edu.user.view.edit.EditProfessorResponse;
import shared.response.edu.user.view.edit.EditStudentResponse;
import shared.response.edu.user.view.edit.ViewProfessorResponse;
import shared.response.edu.user.view.edit.ViewStudentResponse;
import shared.response.edu.user.view.newuser.NewProfessorResponse;
import shared.response.edu.user.view.newuser.NewStudentResponse;
import shared.response.edu.user.view.newuser.ViewNewProfessorResponse;
import shared.response.edu.user.view.newuser.ViewNewStudentResponse;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Scanner;

public class UserController {
    private static final Logger log = LogManager.getLogger(UserController.class);

    private final DB db = DB.getDB();

    public RefreshMainMenuResponse getMainMenu(Integer userId) {
        RefreshMainMenuResponse response = new RefreshMainMenuResponse();
        try {
            response.setTime(LocalDateTime.now());

            User viewer = new User();
            User user = db.getUser(userId);
            viewer.setLastInterTime(user.getLastInterTime());
            response.setViewer(viewer);

            if (user instanceof Student) {
                Student student = (Student) user;
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime dt = student.getSelectUnitTime();
                response.setSelectUnitMenuItem(now.isAfter(dt) && now.minusHours(1).isBefore(dt));
            } else if (user instanceof Professor) {
                Professor professor = (Professor) user;
                response.setSelectUnitMenuItem(professor.isViceChair());
            }

        } catch (Exception e) {
            log.error("while refreshing main menu user: " + userId);
        }

        return response;
    }

    public RefreshMainPaneResponse getMainPane(Integer userId) {
        RefreshMainPaneResponse response = new RefreshMainPaneResponse();
        try {
            User user = db.getUser(userId);
            if (user instanceof Student) {
                Student student = new Student();
                Student viewer = new Student();
                viewer.setImageBase64(user.getImageBase64());
                viewer.setFirstName(student.getFirstName());
                viewer.setLastName(student.getLastName());
                viewer.setEmail(student.getEmail());
                viewer.setSupervisorId(student.getSupervisorId());
                viewer.setSelectUnitPermission(student.isSelectUnitPermission());
                viewer.setSelectUnitTime(student.getSelectUnitTime());
                viewer.setState(student.getState());
                response.setUser(viewer);
            } else if (user instanceof Professor) {
                Professor professor = (Professor) db.getUser(userId);
                Professor viewer = new Professor();
                professor.setFirstName(viewer.getFirstName());
                professor.setLastName(viewer.getLastName());
                professor.setEmail(viewer.getEmail());
                professor.setImageBase64(professor.getImageBase64());
                response.setUser(viewer);
            }
        } catch (Exception e) {
            log.error("while refreshing main menu: " + userId);
        }
        return response;
    }

    public ViewProfileResponse getProfile(Integer userId) {
        ViewProfileResponse response = new ViewProfileResponse();
        try {
            User user = db.getUser(userId);
            if (user instanceof Student) {
                Student student = (Student) user;
                Student output = new Student();
                output.setImageBase64(student.getImageBase64());
                output.setFirstName(student.getFirstName());
                output.setLastName(student.getLastName());
                output.setNationalNumber(student.getNationalNumber());
                output.setId(userId);
                output.setPhoneNumber(student.getPhoneNumber());
                output.setEmail(student.getEmail());
                output.setGrade(student.getGrade());
                output.setDepartmentId(student.getDepartmentId());
                output.setSupervisorId(student.getSupervisorId());
                output.setYear(student.getYear());
                output.setDegree(student.getDegree());
                output.setState(student.getState());
                response.setUser(output);
            } else if (user instanceof Professor) {
                Professor professor = (Professor) user;
                Professor output = new Professor();
                output.setImageBase64(professor.getImageBase64());
                output.setFirstName(professor.getFirstName());
                output.setLastName(professor.getLastName());
                output.setNationalNumber(professor.getNationalNumber());
                output.setId(userId);
                output.setPhoneNumber(professor.getPhoneNumber());
                output.setEmail(professor.getEmail());
                output.setDepartmentId(professor.getDepartmentId());
                output.setRoomNumber(professor.getRoomNumber());
                output.setLevel(professor.getLevel());
                response.setUser(output);
            }
            log.info("user: " + userId + "see his profile");
        } catch (Exception e) {
            log.error("while showing profile user: " + userId);
        }
        return response;
    }

    public EditProfileResponse editProfile(EditProfileForm form, int userId) {
        EditProfileResponse response = new EditProfileResponse();
        try {
            User user = db.getUser(userId);
            user.setPhoneNumber(Long.parseLong(form.getPhoneNumber()));
            user.setEmail(form.getEmail());
            user.setImageBase64(form.getImageBase64());
            db.updateUser(user);
            log.info("user: " + userId + " edit his profile successfully");
        } catch (Exception e) {
            log.error("user: " + userId + " edit his profile unsuccessfully");
        }
        return response;
    }

    public RefreshProfessorListResponse refreshProfessorList(Integer id) {
        RefreshProfessorListResponse response = new RefreshProfessorListResponse();
        try {
            User user = db.getUser(id);
            if (user instanceof Student) {
                response.setViewer(new Student());
            } else {
                Professor professor = (Professor) user;
                Professor viewer = new Professor();
                viewer.setChairman(professor.isChairman());
                response.setViewer(viewer);
            }
            LinkedList<Professor> output = new LinkedList<>();
            for (User u : db.allUsers()) {
                Professor item = (Professor) u;
                Professor add = new Professor();
                add.setId(item.getId());
                add.setFirstName(item.getFirstName());
                add.setLastName(item.getLastName());
                add.setDepartmentId(item.getDepartmentId());
                add.setLevel(item.getLevel());
                add.setRoomNumber(item.getRoomNumber());
                add.setViceChair(item.isViceChair());
                add.setChairman(item.isChairman());
                output.add(add);
            }
            response.setList(output);
        } catch (Exception e) {
            log.error("while refreshing professor list");
        }
        return response;
    }

    public void deleteProfessor(Integer id) {
        try {
            Professor professor = (Professor) db.getUser(id);

            // department
            Department department = db.getDepartment(professor.getDepartmentId());
            department.getProfessorsId().remove(id);
            if (professor.isViceChair()) department.setViceChairId(null);
            db.updateDepartment(department);
            // course
            for (Course course : db.getCourse(professor.getPassedCourseId())) {
                course.getProfessorsId().remove(id);
                db.updateCourse(course);
            }
            for (Course course : db.getCourse(professor.getTermCoursesId())) {
                course.getProfessorsId().remove(id);
                db.updateCourse(course);
            }
            // demand
            for (Demand item : db.getDemand(professor.getRecommendId())) {
                RecommendDemand demand = (RecommendDemand) item;
                Student student = (Student) db.getUser(demand.getDemanderId());
                student.setRecommendDemandId(null);
                db.removeDemand(demand);
            }

            db.removeUser(professor);

            log.info("user: " + id + " deleted successfully");
        } catch (Exception e) {
            log.error("user: " + id + " deleted unsuccessfully");
        }
    }

    public void setViceChair(Integer id) {
        try {
            Professor professor = (Professor) db.getUser(id);
            if (professor.isViceChair()) return;
            professor.setViceChair(true);
            db.updateUser(professor);

            Department department = db.getDepartment(professor.getDepartmentId());
            if (department.getViceChairId() != null) {
                Professor last = (Professor) db.getUser(department.getViceChairId());
                last.setViceChair(false);
                db.updateUser(last);
            }
            department.setViceChairId(id);
            db.updateDepartment(department);
            log.info("set user: " + id + " viceChair");
        } catch (Exception e) {
            log.error("while setting user: " + " to viceChair");
        }
    }

    public ViewNewProfessorResponse viewNewProfessor(Integer id) {
        ViewNewProfessorResponse response = new ViewNewProfessorResponse();
        try {
            Professor professor = (Professor) db.getUser(id);
            Professor viewer = new Professor();
            viewer.setDepartmentId(professor.getDepartmentId());
            response.setViewer(viewer);
            log.info("user: " + id + " view new professor");
        } catch (Exception e) {
            log.error("while showing view new professor to user: " + id);
        }
        return response;
    }

    public ViewNewStudentResponse viewNewStudent(Integer id) {
        ViewNewStudentResponse response = new ViewNewStudentResponse();
        try {
            Professor professor = (Professor) db.getUser(id);
            Professor viewer = new Professor();
            viewer.setDepartmentId(professor.getDepartmentId());
            response.setViewer(viewer);
            log.info("user: " + id + " view new student");
        } catch (Exception e) {
            log.error("while showing view new student to user: " + id);
        }
        return response;
    }

    public ViewProfessorResponse viewProfessor(int id) {
        ViewProfessorResponse response = new ViewProfessorResponse();
        try {
            Professor professor = (Professor) db.getUser(id);
            professor.setPassword(null);
            professor.setRecommendId(null);
            professor.setLastInterTime(null);
            professor.setPassedCourseId(null);
            professor.setTermCoursesId(null);
            response.setProfessor(professor);
            log.info("showing view edit professor: " + id + "page");
        } catch (Exception e) {
            log.error("while showing view edit professor page to user: " + id);
        }
        return response;
    }

    public ViewStudentResponse viewStudent(int id) {
        ViewStudentResponse response = new ViewStudentResponse();
        try {
            Student student = (Student) db.getUser(id);
            student.setConfirmationDemandId(null);
            student.setRecommendDemandId(null);
            student.setDormDemandId(null);
            student.setMinorDemandId(null);
            student.setMinorDemandId(null);
            student.setWithdrawDemandId(null);
            student.setSelectUnitTime(null);
            student.setSignedCourseId(null);
            student.setGradesId(null);
            student.setTermCoursesId(null);
            student.setPassword(null);
            student.setAssistantCourseId(null);
            student.setPassedCourseNumber(null);
            student.setPassedCourseId(null);
            response.setStudent(student);
            log.info("showing view edit student: " + id + "page");
        } catch (Exception e) {
            log.error("while showing view edit student page to user: " + id);
        }

        return response;
    }

    public NewStudentResponse newStudent(Student input) {
        NewStudentResponse response = new NewStudentResponse();
        try {
            input.setId(getUserId());
            db.addUser(input);
            log.info("user: " + input.getId() + " added");
        } catch (Exception e) {
            log.error("while adding user: " + input.getId());
        }
        response.setResult(true);
        return response;
    }

    public NewProfessorResponse newProfessor(Professor input) {
        NewProfessorResponse response = new NewProfessorResponse();
        try {
            input.setId(getUserId());
            db.addUser(input);
            log.info("user: " + input.getId() + " added");
        } catch (Exception e) {
            log.error("while adding user: " + input.getId());
        }
        response.setResult(true);
        return response;
    }

    public EditProfessorResponse editProfessor(Professor input, int id) {
        try {
            Professor professor = (Professor) db.getUser(id);
            professor.setImageBase64(input.getImageBase64());
            professor.setFirstName(input.getFirstName());
            professor.setLastName(input.getLastName());
            professor.setNationalNumber(input.getNationalNumber());
            professor.setPhoneNumber(input.getPhoneNumber());
            professor.setEmail(input.getEmail());
            professor.setRoomNumber(input.getRoomNumber());
            professor.setLevel(input.getLevel());
            db.updateUser(professor);
            log.info("user: " + id + " edited");
        } catch (Exception e) {
            log.error("while editing user: ");
        }
        return new EditProfessorResponse();
    }

    public EditStudentResponse editStudent(Student input, int id) {
        try {
            Student student = (Student) db.getUser(id);
            student.setImageBase64(input.getImageBase64());
            student.setLastName(input.getLastName());
            student.setFirstName(input.getFirstName());
            student.setNationalNumber(input.getNationalNumber());
            student.setPhoneNumber(input.getPhoneNumber());
            student.setEmail(input.getEmail());
            student.setYear(input.getYear());
            student.setDegree(input.getDegree());
            student.setSupervisorId(input.getSupervisorId());
            student.setRand(input.getRand());
            db.updateUser(student);
            log.info("user: " + id + " edited");
        } catch (Exception e) {
            log.error("while editing user: ");
        }
        return new EditStudentResponse();
    }

    public RefreshStudentListResponse refreshStudentList() {
        try {
            LinkedList<Student> output = new LinkedList<>();
            for (User user : db.allUsers()) {
                if (user instanceof Student) {
                    user.setPassword(null);
                    output.add((Student) user);
                }
            }
            return new RefreshStudentListResponse(output);
        } catch (Exception e) {
            log.error("while refreshing student list to Mr Mohseni");
            return new RefreshStudentListResponse(new LinkedList<>());
        }
    }

    public static int getUserId() {
        try {
            File file = new File(DB.USERS_SOURCE + "/id");
            Scanner scanner = new Scanner(file);
            int id = Integer.parseInt(scanner.nextLine());
            scanner.close();
            FileWriter writer = new FileWriter(file);
            id++;
            writer.write(id + "");
            writer.close();
            return id - 1;
        }catch (Exception e) {
            return -1;
        }
    }

}
