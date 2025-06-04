package server.main.java.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import server.main.java.constants.Constants;
import server.main.java.db.DB;
import shared.config.Config;
import shared.model.edu.Department;
import shared.model.edu.course.Course;
import shared.model.edu.course.Grade;
import shared.model.edu.enums.Degree;
import shared.model.edu.user.Professor;
import shared.model.edu.user.Student;
import shared.model.edu.user.User;
import shared.request.FilterCourseForm;
import shared.request.FilterStudentForm;
import shared.request.myedu.student.SelectUnitListSort;
import shared.request.myedu.student.StudentSUActionType;
import shared.response.myedu.ViewSUListResponse;
import shared.response.myedu.professor.RefreshProfessorSUListResponse;
import shared.response.myedu.student.RefreshStudentSUListResponse;
import shared.response.myedu.student.StudentSUActionResponse;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

public class SelectUnitController {
    private final static Logger log = LogManager.getLogger(SelectUnitController.class);

    private final DB db = DB.getDB();

    public ViewSUListResponse viewList(Integer id) {
        ViewSUListResponse response = new ViewSUListResponse();
        try {
            User user = db.getUser(id);
            if (user instanceof Student) {
                Student student = (Student) user;
                if (checkTime(student)) {
                    response.setResult(false);
                    return response;
                }
                response.setResult(true);
                LinkedList<Department> output = new LinkedList<>();
                for (Department item : db.allDepartments()) {
                    Department add = new Department();
                    add.setName(item.getName());
                    add.setId(item.getId());
                    output.add(add);
                }
                response.setDepartmentList(output);
                response.setResult(true);
                return response;
            } else if (user instanceof Professor) {
                response.setResult(null);
                return response;
            }
        } catch (Exception e) {
            log.error("while showing select unit list user: ");
        }

        return response;
    }

    public RefreshStudentSUListResponse refreshStudentList(
            SelectUnitListSort sort, boolean tab, Department department, Integer studentId) {
        RefreshStudentSUListResponse response = new RefreshStudentSUListResponse();
        response.setResult(false);

        try {
            Student student = (Student) db.getUser(studentId);

            if (checkTime(student)) return response;

            // student
            Student viewer = new Student();
            viewer.setSignedCourseId(student.getSignedCourseId());
            viewer.setTermCoursesId(student.getTermCoursesId());
            response.setViewer(viewer);

            // list
            FilterCourseForm filter = new FilterCourseForm();

            if (tab) {
                filter.setDepartment(department);
                filter.setTerm(true);
                response.setCoursesList(sort(db.filterCourse(filter), sort));
            } else {
                LinkedList<Course> output = new LinkedList<>();
                LinkedList<Course> list = db.allCourses();
                for (Course course : list) {
                    if (!course.isTerm()) continue;
                    if (course.getDegree() != student.getDegree()) continue;
                    if (course.getPishniazNumber() != null) {
                        if (!student.getPassedCourseNumber().contains(course.getPishniazNumber())) continue;
                    }
                    if (course.getCapacityCount() <= course.getStudentCount()) continue;
                    output.add(course);
                }
                for (Integer item : student.getSignedCourseId()) {
                    output.add(db.getCourse(item));
                }
                response.setCoursesList(output);
            }
            response.setResult(true);
        } catch (Exception e) {
            log.error("while refresh student select unit list user: " + studentId);
        }

        return response;
    }

    public StudentSUActionResponse studentSelectUnit(StudentSUActionType type,
                                                     Integer stuId,
                                                     Integer courseId,
                                                     Integer groupId) {
        StudentSUActionResponse response = new StudentSUActionResponse();
        try {
            Student student = (Student) db.getUser(stuId);
            Course course = db.getCourse(courseId);
            switch (type) {
                case ADD_COURSE:
                    return addCourse(student, course);
                case DELETE_COURSE:
                    deleteCourse(student, course);
                    break;
                case DEMAND_COURSE:
                    new DemandController().demandSelectUnit(courseId, stuId);
                    break;
                case CHANGE_GROUP:
                    deleteCourse(student, course);
                    Course newGroup = db.getCourse(groupId);
                    addCourse(student, newGroup);
                    break;
                case CHANGE_SIGNED:
                    if (!student.getSignedCourseId().remove(courseId))
                        student.getSignedCourseId().add(courseId);
                    db.updateUser(student);
                    break;
            }
        } catch (Exception e) {
            log.error("while user: " + stuId + " select course: " + courseId);
        }
        return response;
    }

    private StudentSUActionResponse addCourse(Student student, Course course) {
        StudentSUActionResponse response = new StudentSUActionResponse();

        try {
            if (course.getCapacityCount() <= course.getStudentCount()) {
                response.setError("ظرفیت تکمیل است.");
                return response;
            }
            if (course.getPishniazNumber() != null
                    && !student.getPassedCourseNumber().contains(course.getPishniazNumber())) {
                response.setError("پیشنیازی رعایت نشده است.");
                return response;
            }
            if (course.isMaaref()) {
                for (Course item : db.getCourse(student.getTermCoursesId())) {
                    if (item.isMaaref()) {
                        response.setError("از دروس معارف تنها یک درس می توان برداشت.");
                        return response;
                    }
                }
            }
            if (!checkScheduleTimeCourse(student, course)) {
                response.setError("نباید با زمان دیگر دروس تداخل داشته باشد.");
            }
            for (Course item : db.getCourse(student.getTermCoursesId())) {
                if (item.getExamDateTime().compareTo(course.getExamDateTime()) == 0) {
                    response.setError("نباید زمان امتحان آن با دیگر دروس تداخل داشته باشد.");
                }
            }


            course.setStudentCount(course.getStudentCount() + 1);
            course.getStudentsId().add(student.getId());
            student.getTermCoursesId().add(course.getId());
            Grade grade = new Grade();
            grade.setUnit(course.getUnit());
            grade.setId(GradeController.getId());
            grade.setCourseId(course.getId());
            grade.setStudentId(student.getId());
            student.getGradesId().add(grade.getId());
            course.getGradesId().add(grade.getId());

            db.updateUser(student);
            db.addGrade(grade);
            db.updateCourse(course);

            log.info("course: " + course.getId() + " added to user: " + student.getId());
        } catch (Exception e) {
            log.error("while adding course: " + course.getId() + " to user: " + student.getId());
        }
        return response;
    }

    private boolean checkScheduleTimeCourse(Student student, Course course) throws Exception {
        for (Course item : db.getCourse(student.getTermCoursesId())) {
            LocalTime iStart = item.getScheduleTime().getStart();
            LocalTime iEnd = item.getScheduleTime().getEnd();
            LocalTime cStart = course.getScheduleTime().getStart();
            LocalTime cEnd = course.getScheduleTime().getEnd();
            if ((cStart.isAfter(iStart) && cStart.isBefore(iEnd)) || (iStart.isAfter(cStart) && iStart.isBefore(cEnd))) {
                boolean[] iDays = item.getScheduleTime().getDays();
                boolean[] cDays = item.getScheduleTime().getDays();
                for (int i = 0; i < 6; i++) {
                    if (iDays[i] && cDays[i]) return false;
                }
            }
        }
        return true;
    }

    private void deleteCourse(Student student, Course course) {
        try {
            Grade delete = new Grade();
            for (Grade grade : db.getGrade(course.getGradesId())) {
                if (grade.getStudentId() == student.getId()) {
                    delete = grade;
                    student.getGradesId().remove(grade.getId());
                    break;
                }
            }
            course.getGradesId().remove(delete.getId());
            student.getTermCoursesId().remove(course.getId());
            db.updateUser(student);
            db.updateCourse(course);
            db.removeGrade(delete);
            log.info("course: " + course.getId() + " deleted of user: " + student.getId());
        } catch (Exception e) {
            log.error("while deleting course: " + course.getId() + " of user: " + student.getId());
        }
    }

    private LinkedList<Course> sort(LinkedList<Course> list, SelectUnitListSort sortBy) {
        list.sort((o1, o2) -> {
            switch (sortBy) {
                case SORT_BY_NAME:
                    return o1.getName().compareTo(o2.getName());
                case SORT_BY_DEGREE:
                    if (o1.getDegree() == Degree.UNDERGRADUATE) {
                        if (o2.getDegree() == Degree.UNDERGRADUATE) return 0;
                        else return 1;
                    }
                    if (o1.getDegree() == Degree.POSTGRADUATE) {
                        if (o2.getDegree() == Degree.UNDERGRADUATE) return -1;
                        else if (o2.getDegree() == Degree.POSTGRADUATE) return 0;
                        else return 1;
                    }
                    if (o1.getDegree() == Degree.DOCTORATE) {
                        if (o2.getDegree() == Degree.DOCTORATE) return 0;
                        else return 1;
                    }
                case SORT_BY_EXAM_DATE_TIME:
                    return o1.getExamDateTime().compareTo(o2.getExamDateTime());
            }
            return 0;
        });
        return list;
    }

    private boolean checkTime(Student student) {
        int length = new Config(Constants.CONFIG).getProperty(Integer.class, "selectUnitLength");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dt = student.getSelectUnitTime();
        return now.isBefore(dt) || now.minusHours(length).isAfter(dt);
    }

    public RefreshProfessorSUListResponse refreshProfessorList(FilterStudentForm form, Integer viceChairId) {
        RefreshProfessorSUListResponse response = new RefreshProfessorSUListResponse();
        try {
            Professor viceChair = (Professor) db.getUser(viceChairId);

            form.setDepartmentId(viceChair.getId());
            LinkedList<Student> output = new LinkedList<>();
            for (Student student : db.filterStudent(form)) {
                Student add = new Student();
                add.setId(student.getId());
                add.setFirstName(student.getFirstName());
                add.setLastName(student.getLastName());
                add.setDegree(student.getDegree());
                add.setYear(student.getYear());
                add.setRand(student.getRand());
                add.setSelectUnitTime(student.getSelectUnitTime());
            }
            response.setStudentsList(output);
        } catch (Exception e) {
            log.error("while refresh professor su list: " + viceChairId);
        }
        return response;
    }

    public void setStudentTime(
            LinkedList<Integer> studentIdList, LocalDateTime dt, Integer userId) {
        for (Integer id : studentIdList) {
            try {
                Student student = (Student) db.getUser(id);
                student.setSelectUnitTime(dt);
                db.updateUser(student);
                log.info("user: " + userId + " set su time for student: " + id);
            } catch (Exception e) {
                log.error("while set select unit time to student: " + id + " by user: " + userId);
            }
        }

    }

}
