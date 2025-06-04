package server.main.java.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import server.main.java.constants.Constants;
import server.main.java.db.DB;
import shared.config.Config;
import shared.model.edu.Department;
import shared.model.edu.course.Course;
import shared.model.edu.course.Grade;
import shared.model.edu.user.Professor;
import shared.model.edu.user.Student;
import shared.model.edu.user.User;
import shared.response.edu.grade.educationalstate.RefreshEducationalStateResponse;
import shared.response.edu.grade.educationalstate.ViewEducationalStateResponse;
import shared.response.edu.grade.list.RefreshProfessorGradeListResponse;
import shared.response.edu.grade.list.RefreshStudentGradeListResponse;
import shared.response.edu.grade.list.RefreshViceChairGradeListResponse;
import shared.response.edu.grade.list.ViewGradeListResponse;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class GradeController {
    private static final Logger log = LogManager.getLogger(GradeController.class);

    private final DB db = DB.getDB();

    public ViewEducationalStateResponse viewEducationalState(int id) {
        ViewEducationalStateResponse response = new ViewEducationalStateResponse();
        try {
            User user = db.getUser(id);
            if (user instanceof Student) {
                Student student = new Student();
                student.setId(id);
                response.setViewer(student);
            } else if (user instanceof Professor) {
                response.setViewer(new Professor());
            }
            log.info("user: " + id + " view edu state page");
        } catch (Exception e) {
            log.error("while showing edu state page to user: " + id);
        }
        return response;
    }

    public RefreshEducationalStateResponse refreshEducationalState(String name, Integer id) {
        RefreshEducationalStateResponse response = new RefreshEducationalStateResponse();
        Student student;
        try {
            if (id == null) student = (Student) db.getByName(name);
            else student = (Student) db.getUser(id);

            if (student == null) return response;

            Student viewer = new Student();
            viewer.setPassedUnit(student.getPassedUnit());
            viewer.setGrade(student.getGrade());
            response.setStudent(viewer);

            LinkedList<Grade> output = new LinkedList<>();
            for (Grade grade : db.getGrade(student.getGradesId())) {
                Grade add = new Grade();
                add.setNumber(grade.getNumber());
                add.setUnit(grade.getUnit());
                add.setCourseId(grade.getId());
                add.setFinalizedNumber(grade.getFinalizedNumber());
                output.add(add);
            }
            response.setList(output);
        } catch (Exception e) {
            log.error("while refreshing edu state page user: " + id);
        }
        return response;
    }

    public ViewGradeListResponse viewGradeList(Integer id) {
        ViewGradeListResponse response = new ViewGradeListResponse();
        try {
            User user = db.getUser(id);
            if (user instanceof Student) {
                response.setUser(null);
            } else if (user instanceof Professor) {
                Professor professor = (Professor) user;
                LinkedList<Course> output = new LinkedList<>();
                if (professor.isViceChair()) {
                    Department department = db.getDepartment(professor.getDepartmentId());
                    for (Course course : db.getCourse(department.getCoursesId())) {
                        Course add = new Course();
                        add.setId(course.getId());
                        add.setName(course.getName());
                        output.add(add);
                    }
                } else {
                    LinkedList<Integer> courses = new LinkedList<>();
                    courses.addAll(professor.getPassedCourseId());
                    courses.addAll(professor.getTermCoursesId());
                    for (Course course : db.getCourse(courses)) {
                        Course add = new Course();
                        add.setId(course.getId());
                        add.setName(course.getName());
                        output.add(add);
                    }
                }
                response.setList(output);
            }
            log.info("user: " + id + " view grade list");
        } catch (Exception e) {
            log.error("while showing grade list to user: " + id);
        }
        return response;
    }

    public RefreshStudentGradeListResponse refreshStudentGradeList(Integer id) {
        RefreshStudentGradeListResponse response = new RefreshStudentGradeListResponse();
        try {
            Student student = (Student) db.getUser(id);
            LinkedList<Grade> output = new LinkedList<>();
            for (Grade grade : db.getGrade(student.getGradesId())) {
                if (grade.getFinalizedNumber() == null || grade.getFinalizedNumber()) continue;
                output.add(grade);
            }
            response.setList(output);
        } catch (Exception e) {
            log.error("while refreshing grade list user: " + id);
        }
        return response;
    }

    public void changeObjection(String text, int id, boolean assign) {
        try {
            Grade grade = db.getGrade(id);
            grade.setObjection(text);
            if (assign) {
                grade.setFinalizedObjection(true);
                log.info("user: " + grade.getStudentId() + " assign an objection");
            }
            db.updateGrade(grade);
        } catch (Exception e) {
            log.error("while changing objection: " + id);
        }
    }

    public RefreshProfessorGradeListResponse refreshProfessorGradeList(int id) {
        RefreshProfessorGradeListResponse response = new RefreshProfessorGradeListResponse();
        try {
            Course course = db.getCourse(id);
            response.setCourseId(id);
            response.setFinalize(course.getFinalize());
            response.setList(db.getGrade(course.getGradesId()));
        } catch (Exception e) {
            log.error("while refreshing grade list user: " + id);
        }
        return response;
    }

    public void refreshGrade(Integer id, Float number, String stringAnswer, boolean assignAnswer) {
        try {
            Grade grade = db.getGrade(id);
            grade.setNumber(number);
            grade.setAnswer(stringAnswer);
            grade.setFinalizedAnswer(assignAnswer);
            db.updateGrade(grade);
            log.info("grade: " + id + " answered");
        } catch (Exception e) {
            log.error("while assigning answer to grade: " + id);
        }
    }

    public void refreshCourse(Integer id, boolean finalized) {
        try {
            Course course = db.getCourse(id);
            course.setFinalize(finalized);
            for (Grade grade : db.getGrade(course.getGradesId())) {
                grade.setFinalizedNumber(finalized);
                db.updateGrade(grade);
            }
            // professor
            if (finalized) {
                for (Integer proId : course.getProfessorsId()) {
                    Professor professor = (Professor) db.getUser(proId);
                    professor.getTermCoursesId().remove(id);
                    professor.getPassedCourseId().add(id);
                    db.updateUser(professor);
                }
            }
            int min = new Config(Constants.CONFIG).getProperty(Integer.class, "gradeMin");
            // course
            if (finalized) {
                int pass = 0;
                int fail = 0;
                float passSum = 0;
                float sum = 0;

                for (Grade grade : db.getGrade(course.getGradesId())) {
                    if (grade.getNumber() >= min) {
                        pass++;
                        passSum += grade.getNumber();
                    } else {
                        fail++;
                    }
                    sum += grade.getNumber();
                }

                course.setPassStudentsCount(pass);
                course.setFailStudentsCount(fail);
                course.setWithoutFailedAverage(passSum / pass);
                course.setWithFailedAverage(sum / course.getStudentCount());

                db.updateCourse(course);
            }
            // student
            if (finalized) {
                for (Grade grade : db.getGrade(course.getGradesId())) {
                    Student student = (Student) db.getUser(grade.getStudentId());
                    student.getPassedCourseId().add(id);
                    student.getTermCoursesId().remove(id);
                    if (grade.getNumber() >= min) {
                        student.setPassedUnit(student.getPassedUnit() + course.getUnit().getNumber());
                        student.getPassedCourseNumber().add(course.getNumber());
                    }

                    float unit = 0;
                    float sum = 0;

                    for (Grade item : db.getGrade(student.getGradesId())) {
                        if (item.getFinalizedNumber() != null && item.getFinalizedNumber()) {
                            unit += grade.getUnit().getNumber();
                            sum += grade.getNumber() * grade.getUnit().getNumber();
                        }
                    }
                    student.setGrade(sum / unit);

                    db.updateUser(student);
                }


            }

            log.info("course: " + id + " finalized: " + finalized);
        } catch (Exception e) {
            log.error("while finalizing course: " + id);
        }
    }

    public RefreshViceChairGradeListResponse refreshViceChairGradeList(String stuName, String proName,
                                                                       Integer courseId, Integer stuId) {

        RefreshViceChairGradeListResponse response = new RefreshViceChairGradeListResponse();

        try {
            LinkedList<Grade> output = new LinkedList<>();
            if (stuName != null) {
                Student student = (Student) db.getByName(stuName);
                if (student != null) {
                    output.addAll(db.getGrade(student.getGradesId()));
                }
                response.setList(output);
                response.setStudentView(true);
                return response;
            }
            if (stuId != null) {
                Student student = (Student) db.getUser(stuId);
                if (student != null) {
                    output.addAll(db.getGrade(student.getGradesId()));
                }
                response.setList(output);
                response.setStudentView(true);
                return response;
            }
            if (courseId != null) {
                Course course = db.getCourse(courseId);
                if (course != null) {
                    output.addAll(db.getGrade(course.getGradesId()));
                }
                response.setList(output);
                response.setStudentView(false);
                return response;
            }

            if (proName != null) {
                HashMap<Course, LinkedList<Grade>> map = new HashMap<>();
                Professor professor = (Professor) db.getByName(proName);
                if (professor != null) {
                    LinkedList<Integer> courses = new LinkedList<>();
                    courses.addAll(professor.getPassedCourseId());
                    courses.addAll(professor.getTermCoursesId());
                    for (Course course : db.getCourse(courses)) {
                        LinkedList<Grade> list = new LinkedList<>();
                        if (course != null) {
                            output.addAll(db.getGrade(course.getGradesId()));
                        }
                        map.put(course, list);
                    }
                }
                response.setMap(map);
            }

            response.setList(output);
        }catch (Exception e) {
            log.error("while refreshing grade list viceChair");
        }
        return response;
    }

    public static int getId() {
        try {
            File file = new File(DB.GRADES_SOURCE + "/id.text");
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