package server.main.java.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import server.main.java.constants.Constants;
import server.main.java.db.DB;
import shared.config.Config;
import shared.model.edu.Department;
import shared.model.edu.course.Course;
import shared.model.edu.course.Grade;
import shared.model.edu.demand.*;
import shared.model.edu.enums.StudentState;
import shared.model.edu.user.Professor;
import shared.model.edu.user.Student;
import shared.model.edu.user.User;
import shared.response.edu.demand.*;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Scanner;

public class DemandController {
    private final static Logger logger = LogManager.getLogger(DemandController.class);

    private final DB db = DB.getDB();

    public RefreshDormResponse getDorm(Integer stuId) {
        RefreshDormResponse response = new RefreshDormResponse();
        try {
            Student student = (Student) db.getUser(stuId);
            response.setDemand((DormDemand) db.getDemand(student.getDormDemandId()));
        } catch (Exception e) {
            logger.error("error while loading dorm demand user: " + stuId);
        }
        return response;
    }

    public RefreshConfirmationResponse getConfirmation(Integer stuId) {
        RefreshConfirmationResponse response = new RefreshConfirmationResponse();
        try {
            Student student = (Student) db.getUser(stuId);
            response.setDemand((ConfirmationDemand) db.getDemand(student.getConfirmationDemandId()));
        } catch (Exception e) {
            logger.error("error while loading confirmation demand user: " + stuId);
        }
        return response;
    }

    public RefreshMinorResponse getMinor(Integer stuId) {
        RefreshMinorResponse response = new RefreshMinorResponse();
        try {
            Student student = (Student) db.getUser(stuId);
            response.setDemand((MinorDemand) db.getDemand(student.getMinorDemandId()));
        } catch (Exception e) {
            logger.error("error while loading confirmation demand user: " + stuId);
        }
        return response;
    }

    public RefreshMinorListResponse getMinorList(Integer proId) {
        RefreshMinorListResponse response = new RefreshMinorListResponse();
        try {
            Professor professor = (Professor) db.getUser(proId);
            Department department = db.getDepartment(professor.getDepartmentId());
            LinkedList<MinorDemand> in = new LinkedList<>();
            for (Demand demand : db.getDemand(department.getInMinorId())) {
                in.add((MinorDemand) demand);
            }
            LinkedList<MinorDemand> out = new LinkedList<>();
            for (Demand demand : db.getDemand(department.getOutMinorId())) {
                in.add((MinorDemand) demand);
            }
            response.setListIn(in);
            response.setListOut(out);
        } catch (Exception e) {
            logger.error("error while loading minor demands list user: " + proId);
        }
        return response;

    }

    public RefreshRecommendResponse getRecommend(Integer stuId) {
        RefreshRecommendResponse response = new RefreshRecommendResponse();
        try {
            Student student = (Student) db.getUser(stuId);
            response.setDemand((RecommendDemand) db.getDemand(student.getRecommendDemandId()));
        } catch (Exception e) {
            logger.error("while loading recommend demand user: " + stuId);
        }
        return response;
    }

    public RefreshRecommendListResponse getRecommendList(Integer proId) {
        RefreshRecommendListResponse response = new RefreshRecommendListResponse();
        try {
            Professor professor = (Professor) db.getUser(proId);
            LinkedList<RecommendDemand> out = new LinkedList<>();
            for (Demand demand : db.getDemand(professor.getRecommendId())) {
                out.add((RecommendDemand) demand);
            }
            response.setList(out);
        } catch (Exception e) {
            logger.error("while loading recommend demand list user: " + proId);
        }
        return response;
    }

    public RefreshWithdrawResponse getWithdraw(Integer stuId) {
        RefreshWithdrawResponse response = new RefreshWithdrawResponse();
        try {
            Student student = (Student) db.getUser(stuId);
            response.setDemand((WithdrawDemand) db.getDemand(student.getWithdrawDemandId()));
        } catch (Exception e) {
            logger.error("while loading withdraw demand user: " + stuId);
        }
        return response;
    }

    public RefreshWithdrawListResponse getWithdrawList(Integer proId) {
        RefreshWithdrawListResponse response = new RefreshWithdrawListResponse();
        try {
            Professor professor = (Professor) db.getUser(proId);
            Department department = db.getDepartment(professor.getDepartmentId());
            LinkedList<WithdrawDemand> out = new LinkedList<>();
            for (Demand demand : db.getDemand(department.getWithdrawId())) {
                out.add((WithdrawDemand) demand);
            }
            response.setList(out);
        } catch (Exception e) {
            logger.error("while loading withdraw demand list user: " + proId);
        }
        return response;
    }

    public RefreshDissertationResponse getDissertation(Integer stuId) {
        RefreshDissertationResponse response = new RefreshDissertationResponse();
        try {
            Student student = (Student) db.getUser(stuId);
            response.setDemand((DissertationDemand) db.getDemand(student.getDissertationDemandId()));
        } catch (Exception e) {
            logger.error("while loading dissertation demand user: " + stuId);
        }
        return response;
    }

    public RefreshDissertationListResponse getDissertationList(Integer proId) {
        RefreshDissertationListResponse response = new RefreshDissertationListResponse();
        try {
            Professor professor = (Professor) db.getUser(proId);
            Department department = db.getDepartment(professor.getDepartmentId());
            LinkedList<DissertationDemand> out = new LinkedList<>();
            for (Demand demand : db.getDemand(department.getDissertationId())) {
                out.add((DissertationDemand) demand);
            }
            response.setList(out);
        } catch (Exception e) {
            logger.error("while loading dissertation demand list user: " + proId);
        }
        return response;
    }

    public void answerMinor(Integer id, boolean result, Integer proId) {
        try {
            MinorDemand demand = (MinorDemand) db.getDemand(id);
            Professor professor = (Professor) db.getUser(proId);
            if (demand.getInDepartmentId() == professor.getDepartmentId()) {
                demand.setInState(result);
            } else {
                demand.setOutState(result);
            }
            if (demand.getInState() == null) {

                if (demand.getOutState() == null) demand.setState(null);
                else if (demand.getOutState()) demand.setState(null);
                else demand.setState(true);

            } else if (!demand.getInState()) {

                demand.setState(false);

            } else {

                if (demand.getOutState() == null) demand.setState(null);
                else demand.setState(demand.getOutState());

            }
            db.updateDemand(demand);
            logger.info("user: " + proId + " answer " + result + " to minor demand: " + id);
        } catch (Exception e) {
            logger.error("while answering to minor: " + id + " user: " + proId);
        }
    }

    public void answerWithdraw(Integer id, boolean result, Integer proId) {
        try {
            WithdrawDemand demand = (WithdrawDemand) db.getDemand(id);
            demand.setState(result);
            db.updateDemand(demand);
            if (result) {
                Student student = (Student) db.getUser(demand.getDemanderId());
                student.setState(StudentState.WITHDRAW);
            }
            logger.info("user: " + proId + " answer " + result + " to withdraw demand: " + id);
        } catch (Exception e) {
            logger.error("while answering to withdraw: " + id + " user: " + proId);
        }

    }

    public void answerRecommend(Integer id, boolean result, Integer proId) {
        try {
            RecommendDemand demand = (RecommendDemand) db.getDemand(id);
            demand.setState(result);
            db.updateDemand(demand);
            logger.info("user: " + proId + " answer " + result + " to recommend demand: " + id);
        } catch (Exception e) {
            logger.error("while answering to recommend: " + id + " user: " + proId);
        }

    }

    public void answerDissertation(Integer id, LocalDateTime dt, Integer proId) {
        try {
            DissertationDemand demand = (DissertationDemand) db.getDemand(id);
            demand.setDateTime(dt);
            demand.setFinalized(true);
            db.updateDemand(demand);
            logger.info("user: " + proId + " set time " + dt + " to recommend demand: " + id);
        } catch (Exception e) {
            logger.error("while answering to recommend: " + id + " user: " + proId);
        }

    }

    public void demandDissertation(Integer stuId) {
        try {
            DissertationDemand demand = new DissertationDemand();
            demand.setDemanderId(stuId);
            demand.setId(getId());
            Student student = (Student) db.getUser(stuId);
            demand.setDepartmentId(student.getDepartmentId());
            student.setDissertationDemandId(demand.getId());
            Department department = db.getDepartment(student.getDepartmentId());
            department.getDissertationId().add(demand.getId());
            db.updateUser(student);
            db.addDemand(demand);
            db.updateDepartment(department);
            logger.info("user: " + stuId + " demand dissertation");
        } catch (Exception e) {
            logger.error("user: " + stuId + " try to demand dissertation unsuccessfully");
        }
    }

    public void demandWithdraw(Integer stuId) {
        try {
            WithdrawDemand demand = new WithdrawDemand();
            demand.setDemanderId(stuId);
            demand.setId(getId());
            Student student = (Student) db.getUser(stuId);
            student.setWithdrawDemandId(demand.getId());
            demand.setDepartmentId(student.getDepartmentId());
            Department department = db.getDepartment(student.getDepartmentId());
            department.getWithdrawId().add(demand.getId());
            db.updateUser(student);
            db.addDemand(demand);
            db.updateDepartment(department);
            logger.info("user: " + stuId + " demand withdraw");
        } catch (Exception e) {
            logger.error("user: " + stuId + " try to demand withdraw unsuccessfully");
        }
    }

    public void demandDorm(Integer stuId) {
        try {
            DormDemand demand = new DormDemand();
            demand.setDemanderId(stuId);
            demand.setId(getId());
            Student student = (Student) db.getUser(stuId);
            student.setWithdrawDemandId(demand.getId());
            db.updateUser(student);
            db.addDemand(demand);
            logger.info("user: " + stuId + " demand dorm");
        } catch (Exception e) {
            logger.error("user: " + stuId + " try to demand dorm unsuccessfully");
        }
    }

    public void demandConfirmation(Integer stuId) {
        try {
            ConfirmationDemand demand = new ConfirmationDemand();
            demand.setDemanderId(stuId);
            demand.setId(getId());
            Student student = (Student) db.getUser(stuId);
            student.setConfirmationDemandId(demand.getId());
            db.updateUser(student);
            db.addDemand(demand);
            logger.info("user: " + stuId + " demand confirmation");
        } catch (Exception e) {
            logger.error("user:" + stuId + "demand confirmation unsuccessfully");
        }

    }

    public void demandRecommend(String proName, Integer stuId) {
        try {
            Professor professor = null;
            for (User item : db.allUsers()) {
                if (item instanceof Professor && item.getName().equals(proName)) {
                    professor = (Professor) item;
                    break;
                }
            }
            if (professor == null) return;
            RecommendDemand demand = new RecommendDemand();
            demand.setId(getId());
            demand.setDemanderId(stuId);
            demand.setProfessorId(professor.getId());
            professor.getRecommendId().add(demand.getId());
            Student student = (Student) db.getUser(stuId);
            student.setRecommendDemandId(demand.getId());
            db.updateUser(student);
            db.updateUser(professor);
            db.addDemand(demand);
            logger.info("user: " + stuId + " demand recommend of user: " + professor.getId());
        } catch (Exception e) {
            logger.error("user: " + stuId + " try to demand recommend unsuccessfully");
        }
    }

    public void demandMinor(String depName, Integer stuId) {
        try {
            Department in = null;
            for (Department department : db.allDepartments()) {
                if (department.getName().equals(depName)) {
                    in = department;
                    break;
                }
            }
            if (in == null) return;
            MinorDemand demand = new MinorDemand();
            demand.setId(getId());
            demand.setDemanderId(stuId);
            Student student = (Student) db.getUser(stuId);
            student.setMinorDemandId(demand.getId());
            demand.setInDepartmentId(in.getId());
            in.getInMinorId().add(stuId);
            Department out = db.getDepartment(student.getDormDemandId());
            demand.setOutDepartmentId(out.getId());
            out.getOutMinorId().add(demand.getId());
            db.updateUser(student);
            db.updateDepartment(in);
            db.updateDepartment(out);
            db.addDemand(demand);
            logger.info("user: " + stuId + " demand withdraw");
        } catch (Exception e) {
            logger.error("user: " + stuId + " try to demand minor unsuccessfully");
        }

    }

    public void answerSelectUnit(Integer demandId, boolean result) {
        try {
            SelectUnitDemand demand = (SelectUnitDemand) db.getDemand(demandId);
            demand.setState(result);
            if (result) {
                Grade grade = new Grade();
                grade.setId(GradeController.getId());
                grade.setCourseId(demand.getCourseId());
                grade.setStudentId(demand.getDemanderId());
                Course course = db.getCourse(demand.getCourseId());
                course.getStudentsId().add(demand.getDemanderId());
                course.getGradesId().add(grade.getId());
                Student student = (Student) db.getUser(demand.getDemanderId());
                student.getGradesId().add(grade.getId());
                student.getTermCoursesId().add(demand.getCourseId());

                db.updateUser(student);
                db.updateCourse(course);
                db.updateDemand(demand);
                db.addGrade(grade);
            }

            db.updateDemand(demand);
        } catch (Exception e) {
            logger.info("");
        }
    }

    public void demandSelectUnit(Integer courseId, Integer stuId) {
        try {
            SelectUnitDemand demand = new SelectUnitDemand();
            demand.setId(getId());
            demand.setCourseId(courseId);
            User user = db.getUser(stuId);
            demand.setDemanderId(user.getDepartmentId());
            Department department = db.getDepartment(user.getDepartmentId());
            department.getSelectUnitDemandId().add(demand.getId());
            db.updateUser(user);
            db.updateDepartment(department);
            db.addDemand(demand);
            logger.info("user: " + stuId + " demand select unit course: " + courseId);
        } catch (Exception e) {
            logger.error("user: " + stuId + " try to demand select unit unsuccessfully");
        }
    }

    public RefreshSUDemandListResponse refreshSelectUnitList(Integer id) {
        try {
            LinkedList<SelectUnitDemand> demandsList = new LinkedList<>();
            boolean answerView;
            User user = db.getUser(id);
            if (user instanceof Student) {
                Student student = (Student) user;
                for (Demand demand : db.getDemand(student.getSelectUnitDemandId())) {
                    demandsList.add((SelectUnitDemand) demand);
                }
                answerView = false;
            } else {
                Professor professor = (Professor) user;
                Department department = db.getDepartment(professor.getDepartmentId());
                for (Demand demand : db.getDemand(department.getSelectUnitDemandId())) {
                    demandsList.add((SelectUnitDemand) demand);
                }
                answerView = true;
            }
            return new RefreshSUDemandListResponse(demandsList, answerView);
        } catch (Exception e) {
            logger.error("while refresh select unit demand list user: " + id);
        }
        return new RefreshSUDemandListResponse(new LinkedList<>(), false);
    }

    public static int getId() {
        try {
            File file = new File(DB.DEMANDS_SOURCE + "/id.text");
            Scanner scanner = new Scanner(file);
            int id = Integer.parseInt(scanner.nextLine());
            scanner.close();
            id++;
            FileWriter writer = new FileWriter(file);
            writer.write(id + "");
            writer.close();
            return id - 1;
        }catch (Exception e) {
            return -1;
        }
    }
}
