package server.main.java.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import server.main.java.constants.Constants;
import server.main.java.db.DB;
import shared.config.Config;
import shared.model.edu.Department;
import shared.model.edu.course.*;
import shared.model.edu.user.*;
import shared.request.FilterCourseForm;
import shared.response.edu.course.list.*;
import shared.response.edu.course.view.*;

import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class CourseController {
    private final DB db = DB.getDB();

    private final static Logger logger = LogManager.getLogger(CourseController.class);

    public ViewCourseListResponse viewCourseList(Integer userId) {
        try {
            ViewCourseListResponse response = new ViewCourseListResponse();

            LinkedList<Department> depList = new LinkedList<>();
            for (Department department : db.allDepartments()) {
                Department add = new Department();
                add.setId(department.getId());
                add.setName(department.getName());
                depList.add(add);
            }
            response.setDepList(depList);

            logger.info("user " + userId + " view courses list");
            return response;
        } catch (Exception e) {
            logger.error("error while user " + userId + " want to view courses list");
            return new ViewCourseListResponse();
        }
    }

    public RefreshCourseListResponse refreshCourseList(Integer userId, FilterCourseForm form) {
        try {
            RefreshCourseListResponse response = new RefreshCourseListResponse();
            User viewer = db.getUser(userId);
            if (viewer instanceof Student) {
                Student student = new Student();
                response.setViewer(student);
            } else {
                Professor professor = new Professor();
                professor.setDepartmentId(viewer.getDepartmentId());
                professor.setViceChair(((Professor) viewer).isViceChair());
                response.setViewer(professor);
            }
            form.setTerm(true);
            response.setList(db.filterCourse(form));
            return response;
        } catch (Exception e) {
            logger.error("while user " + userId + " refresh courses list");
            RefreshCourseListResponse response = new RefreshCourseListResponse();
            response.setViewer(new Student());
            response.setList(new LinkedList<>());
            return response;
        }

    }

    public void deleteCourse(Integer courseId) {
        try {
            Course course = db.getCourse(courseId);
            // student
            for (User user : db.getUser(course.getStudentsId())) {
                Student student = (Student) user;
                student.getPassedCourseId().remove(courseId);
                student.getTermCoursesId().remove(courseId);
                student.getSignedCourseId().remove(courseId);
                for (Grade grade : db.getGrade(student.getGradesId())) {
                    if (grade.getCourseId() == courseId) {
                        student.getGradesId().remove(grade.getId());
                        break;
                    }
                }
                db.updateUser(student);
            }
            // professor
            for (User user : db.getUser(course.getProfessorsId())) {
                Professor professor = (Professor) user;
                professor.getPassedCourseId().remove(courseId);
                professor.getTermCoursesId().remove(courseId);
                db.updateUser(professor);
            }
            // assistant
            for (User user : db.getUser(course.getAssistantsId())) {
                Student assistant = (Student) user;
                assistant.getAssistantCourseId().remove(courseId);
                db.updateUser(assistant);
            }
            // grade
            for (Grade grade : db.getGrade(course.getGradesId())) {
                db.removeGrade(grade);
            }
            // other course
            for (Course item : db.getCourse(course.getOtherGroupId())) {
                item.getOtherGroupId().remove(course.getId());
                db.updateCourse(item);
            }
            Department department = db.getDepartment(course.getDepartmentId());
            department.getCoursesId().remove(courseId);
            db.removeCourse(course);

            logger.info("course " + courseId + " removed successfully");
        } catch (Exception e) {
            logger.error("while removing course " + courseId);
        }
    }

    public NewCourseResponse newCourse(Course course, Integer userId) {
        try {
            NewCourseResponse response = new NewCourseResponse();
            course.setTerm(true);
            course.setId(getCourseId());
            for (User user : db.getUser(course.getProfessorsId())) {
                user.getTermCoursesId().add(course.getId());
                db.updateUser(user);
            }
            for (User user : db.getUser(course.getStudentsId())) {
                Student student = (Student) user;
                student.getAssistantCourseId().add(course.getId());
                db.updateUser(student);
            }
            Department department = db.getDepartment(course.getDepartmentId());
            department.getCoursesId().add(course.getId());
            db.updateDepartment(department);
            course.setMaaref(department.isMaaref());

            // other group
            FilterCourseForm form = new FilterCourseForm();
            form.setTerm(true);
            form.setNumber(course.getNumber());
            for (Course item : db.filterCourse(form)) {
                item.getOtherGroupId().add(course.getId());
                course.getOtherGroupId().add(item.getId());
                db.updateCourse(item);
            }
            db.addCourse(course);
            response.setCourseId(course.getId());
            response.setResult(true);

            logger.info("course " + course.getId() + " added successfully by user " + userId);
            return response;
        } catch (Exception e) {
            logger.error("while adding course by user " + userId);
            return new NewCourseResponse();
        }
    }

    public ViewNewCourseResponse viewNewCourse(Integer id) {
        try {
            ViewNewCourseResponse response = new ViewNewCourseResponse();
            Professor professor = (Professor) db.getUser(id);
            Professor viewer = new Professor();
            viewer.setDepartmentId(professor.getDepartmentId());
            response.setViewer(viewer);
            return response;
        } catch (Exception e) {
            logger.error("while view new course by user: " + id);
        }
        return new ViewNewCourseResponse();
    }

    public EditCourseResponse editCourse(Integer id, Course input) {
        EditCourseResponse response = new EditCourseResponse();
        try {
            Course course = db.getCourse(id);
            course.setName(input.getName());
            course.setNumber(input.getNumber());
            course.setUnit(input.getUnit());
            course.setDegree(input.getDegree());
            course.setPishniazNumber(input.getPishniazNumber());
            course.setHamniazNumber(input.getHamniazNumber());
            course.setCapacityCount(input.getCapacityCount());
            course.setExamDateTime(input.getExamDateTime());
            course.setScheduleTime(input.getScheduleTime());
            course.setProfessorsId(input.getProfessorsId());
            course.setAssistantsId(input.getAssistantsId());
            db.updateCourse(course);
            logger.info("course " + id + " information loaded successfully");
            return response;
        } catch (Exception e) {
            logger.error("error while loading course " + id + " information");
        }
        return new EditCourseResponse();
    }

    public ViewEditCourseResponse viewEditCourse(Integer id, Integer user) {
        ViewEditCourseResponse response = new ViewEditCourseResponse();
        Course course = null;
        try {
            course = db.getCourse(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setCourse(course);
        logger.info("course has viewed by user " + user);
        return response;
    }

    public RefreshExamListResponse refreshExamList(Integer userId) {
        RefreshExamListResponse response = new RefreshExamListResponse();
        try {
            User user = db.getUser(userId);
            LinkedList<Course> list = db.getCourse(user.getTermCoursesId());
            list.sort((o1, o2) -> {
                if (o1.getExamDateTime() == null) return -1;
                if (o2.getExamDateTime() == null) return 1;
                return o1.getExamDateTime().compareTo(o2.getExamDateTime());
            });
            LinkedList<Course> output = new LinkedList<>();
            for (Course item : list) {
                Course add = new Course();
                add.setName(item.getName());
                add.setExamDateTime(item.getExamDateTime());
                output.add(add);
            }
            response.setList(output);
        } catch (Exception e) {
            logger.error("while refresh exam list by user " + userId);
        }
        return response;
    }

    public RefreshScheduleResponse refreshSchedule(Integer userId) {
        RefreshScheduleResponse response = new RefreshScheduleResponse();
        try {
            User user = db.getUser(userId);
            LinkedList<Course> list = db.getCourse(user.getTermCoursesId());
            LinkedList<Course> output = new LinkedList<>();
            for (Course item : list) {
                Course add = new Course();
                add.setName(item.getName());
                add.setScheduleTime(item.getScheduleTime());
                output.add(add);
            }
            response.setList(output);
        } catch (Exception e) {
            logger.error("while refresh schedule list by user " + userId);
        }
        return response;
    }


    public static int getCourseId() {
        try {
            File file = new File(DB.COURSES_SOURCE + "/id.text");
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
