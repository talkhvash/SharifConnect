package server.main.java.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import server.main.java.constants.Constants;
import shared.config.Config;
import shared.json.Deserializer;
import shared.json.Serializer;
import shared.model.edu.Captcha;
import shared.model.edu.Department;
import shared.model.edu.course.Course;
import shared.model.edu.course.Grade;
import shared.model.edu.demand.Demand;
import shared.model.edu.user.Student;
import shared.model.edu.user.User;
import shared.model.messenger.Chat;
import shared.model.messenger.Message;
import shared.request.FilterCourseForm;
import shared.request.FilterStudentForm;
import shared.request.Request;
import shared.response.Response;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class DB {
    private static DB db;

    public static DB getDB() {
        if (db == null) db = new DB();
        return db;
    }

    private final Object usersLock = new Object();
    private final Object departmentsLock = new Object();
    private final Object coursesLock = new Object();
    private final Object chatsLock = new Object();
    private final Object demandsLock = new Object();
    private final Object gradesLock = new Object();
    private final Object messagesLock = new Object();
    private final Object captchasLock = new Object();

    public final static String CAPTCHAS_SOURCE =
            new Config(Constants.CONFIG).getProperty(String.class, "captchas_source");
    public final static String COURSES_SOURCE =
            new Config(Constants.CONFIG).getProperty(String.class, "courses_source");
    public final static String DEPARTMENTS_SOURCE =
            new Config(Constants.CONFIG).getProperty(String.class, "departments_source");
    public final static String DEMANDS_SOURCE =
            new Config(Constants.CONFIG).getProperty(String.class, "demands_source");
    public final static String CHATS_SOURCE =
            new Config(Constants.CONFIG).getProperty(String.class, "chats_source");
    public final static String MESSAGES_SOURCE =
            new Config(Constants.CONFIG).getProperty(String.class, "messages_source");
    public final static String GRADES_SOURCE =
            new Config(Constants.CONFIG).getProperty(String.class, "grades_source");
    public final static String USERS_SOURCE =
            new Config(Constants.CONFIG).getProperty(String.class, "users_source");

    private final Gson json = new GsonBuilder()
            .registerTypeAdapter(Request.class, new Deserializer<>())
            .registerTypeAdapter(Response.class, new Serializer<>())
            .setPrettyPrinting()
            .serializeNulls()
            .create();

    public Captcha getCaptcha(Integer id) throws Exception {
        synchronized (captchasLock) {
            File file = new File(CAPTCHAS_SOURCE + "/" + id + ".json");
            FileReader reader = new FileReader(file);
            Captcha captcha = json.fromJson(reader, Captcha.class);
            reader.close();
            return captcha;
        }
    }

    public void addCaptcha(Captcha captcha) throws Exception {
        synchronized (captchasLock) {
            File file = new File(CAPTCHAS_SOURCE + "/" + captcha.getId() + ".json");
            if (!file.exists()) file.createNewFile();
            FileWriter writer = new FileWriter(file);
            json.toJson(captcha, writer);
            writer.close();
        }
    }

    // user ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void addUser(User user) throws Exception {
        synchronized (usersLock) {
            File file = new File(USERS_SOURCE + "/" + user.getId() + ".json");
            if (!file.exists()) file.createNewFile();
            FileWriter writer = new FileWriter(file);
            json.toJson(user, writer);
            writer.close();
        }
    }

    public User getUser(Integer id) throws Exception {
        synchronized (usersLock) {
            File file = new File(USERS_SOURCE + "/" + id + ".json");
            if (!file.exists()) return null;
            FileReader reader = new FileReader(file);
            User user = json.fromJson(reader, User.class);
            reader.close();
            return user;
        }
    }

    public void removeUser(User user) throws Exception {
        synchronized (usersLock) {
            File file = new File(USERS_SOURCE + "/" + user.getId() + ".json");
            file.delete();
        }
    }

    public void updateUser(User user) throws Exception {
        synchronized (usersLock) {
            File file = new File(USERS_SOURCE + "/" + user.getId() + ".json");
            if (!file.exists()) file.createNewFile();
            FileWriter writer = new FileWriter(file);
            json.toJson(user, writer);
            writer.close();
        }
    }

    public LinkedList<User> allUsers() throws Exception {
        synchronized (usersLock) {
            LinkedList<User> output = new LinkedList<>();
            File allFile = new File(USERS_SOURCE);
            for (File file : Objects.requireNonNull(allFile.listFiles())) {
                FileReader reader = new FileReader(file);
                User user = json.fromJson(reader, User.class);
                reader.close();
                output.add(user);
            }
            return output;
        }
    }

    public LinkedList<User> getUser(LinkedList<Integer> ids) throws Exception {
        LinkedList<User> output = new LinkedList<>();
        for (Integer id : ids) output.add(getUser(id));
        return output;
    }

    // department ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void addDepartment(Department department) throws Exception {
        synchronized (departmentsLock) {
            File file = new File(DEPARTMENTS_SOURCE + "/" + department.getId() + ".json");
            if (!file.exists()) file.createNewFile();
            FileWriter writer = new FileWriter(file);
            json.toJson(department, writer);
            writer.close();
        }
    }

    public Department getDepartment(Integer id) throws Exception {
        synchronized (departmentsLock) {
            File file = new File(DEPARTMENTS_SOURCE + "/" + id + ".json");
            if (!file.exists()) return null;
            FileReader reader = new FileReader(file);
            Department department = json.fromJson(reader, Department.class);
            reader.close();
            return department;
        }
    }

    public void updateDepartment(Department department) throws Exception {
        synchronized (departmentsLock) {
            File file = new File(DEPARTMENTS_SOURCE + "/" + department.getId() + ".json");
            if (!file.exists()) file.createNewFile();
            FileWriter writer = new FileWriter(file);
            json.toJson(department, writer);
            writer.close();
        }
    }

    public LinkedList<Department> allDepartments() throws Exception {
        synchronized (departmentsLock) {
            LinkedList<Department> output = new LinkedList<>();
            File allFile = new File(DEPARTMENTS_SOURCE);
            for (File file : Objects.requireNonNull(allFile.listFiles())) {
                FileReader reader = new FileReader(file);
                Department department = json.fromJson(reader, Department.class);
                reader.close();
                output.add(department);
            }
            return output;
        }
    }

    // course ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void addCourse(Course course) throws Exception {
        synchronized (coursesLock) {
            File file = new File(COURSES_SOURCE + "/" + course.getId() + ".json");
            if (!file.exists()) file.createNewFile();
            FileWriter writer = new FileWriter(file);
            json.toJson(course, writer);
            writer.close();
        }
    }

    public Course getCourse(Integer id) throws Exception {
        synchronized (coursesLock) {
            File file = new File(COURSES_SOURCE + "/" + id + ".json");
            if (!file.exists()) return null;
            FileReader reader = new FileReader(file);
            Course course = json.fromJson(reader, Course.class);
            reader.close();
            return course;
        }
    }

    public void removeCourse(Course course) throws Exception {
        synchronized (coursesLock) {
            File file = new File(COURSES_SOURCE + "/" + course.getId() + ".json");
            file.delete();
        }
    }

    public void updateCourse(Course course) throws Exception {
        synchronized (coursesLock) {
            File file = new File(USERS_SOURCE + "/" + course.getId() + ".json");
            if (!file.exists()) file.createNewFile();
            FileWriter writer = new FileWriter(file);
            json.toJson(course, writer);
            writer.close();
        }
    }

    public LinkedList<Course> allCourses() throws Exception {
        synchronized (coursesLock) {
            LinkedList<Course> output = new LinkedList<>();
            File allFile = new File(COURSES_SOURCE);
            for (File file : Objects.requireNonNull(allFile.listFiles())) {
                FileReader reader = new FileReader(file);
                Course course = json.fromJson(reader, Course.class);
                reader.close();
                output.add(course);
            }
            return output;
        }
    }

    public LinkedList<Course> filterCourse(FilterCourseForm filter) throws Exception {
        synchronized (coursesLock) {
            LinkedList<Course> output = new LinkedList<>();
            File allFile = new File(USERS_SOURCE);
            for (File file : Objects.requireNonNull(allFile.listFiles())) {
                FileReader reader = new FileReader(file);
                Course course = json.fromJson(reader, Course.class);
                reader.close();
                if (filter.getDegree() != null && !filter.getDegree().equals(course.getDegree())) continue;
                if (filter.getNumber() != null && !filter.getNumber().equals(course.getNumber())) continue;
                if (filter.getUnit() != null && !filter.getUnit().equals(course.getUnit())) continue;
                if (filter.getDepartment() != null && filter.getDepartment().getId() != course.getDepartmentId())
                    continue;
                output.add(course);
            }
            return output;
        }
    }

    public LinkedList<Course> getCourse(LinkedList<Integer> ids) throws Exception {
        LinkedList<Course> output = new LinkedList<>();
        for (Integer id : ids) output.add(getCourse(id));
        return output;
    }

    // grade +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void addGrade(Grade grade) throws Exception {
        synchronized (gradesLock) {
            File file = new File(GRADES_SOURCE + "/" + grade.getId() + ".json");
            if (!file.exists()) file.createNewFile();
            FileWriter writer = new FileWriter(file);
            json.toJson(grade, writer);
            writer.close();
        }
    }

    public Grade getGrade(Integer id) throws Exception {
        synchronized (gradesLock) {
            File file = new File(GRADES_SOURCE + "/" + id + ".json");
            if (!file.exists()) return null;
            FileReader reader = new FileReader(file);
            Grade grade = json.fromJson(reader, Grade.class);
            reader.close();
            return grade;
        }
    }

    public void removeGrade(Grade grade) throws Exception {
        synchronized (gradesLock) {
            File file = new File(GRADES_SOURCE + "/" + grade.getId() + ".json");
            file.delete();
        }
    }

    public void updateGrade(Grade grade) throws Exception {
        synchronized (gradesLock) {
            File file = new File(GRADES_SOURCE + "/" + grade.getId() + ".json");
            if (!file.exists()) file.createNewFile();
            FileWriter writer = new FileWriter(file);
            json.toJson(grade, writer);
            writer.close();
        }
    }

    public LinkedList<Grade> allGrades() throws Exception {
        synchronized (gradesLock) {
            LinkedList<Grade> output = new LinkedList<>();
            File allFile = new File(GRADES_SOURCE);
            for (File file : Objects.requireNonNull(allFile.listFiles())) {
                FileReader reader = new FileReader(file);
                Grade grade = json.fromJson(reader, Grade.class);
                reader.close();
                output.add(grade);
            }
            return output;
        }
    }

    public LinkedList<Grade> getGrade(LinkedList<Integer> ids) throws Exception {
        LinkedList<Grade> output = new LinkedList<>();
        for (Integer id : ids) output.add(getGrade(id));
        return output;
    }

    // demand ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void addDemand(Demand demand) throws Exception {
        synchronized (demandsLock) {
            File file = new File(DEMANDS_SOURCE + "/" + demand.getId() + ".json");
            if (!file.exists()) file.createNewFile();
            FileWriter writer = new FileWriter(file);
            json.toJson(demand, writer);
            writer.close();
        }
    }

    public Demand getDemand(Integer id) throws Exception {
        synchronized (demandsLock) {
            File file = new File(DEMANDS_SOURCE + "/" + id + ".json");
            if (!file.exists()) return null;
            FileReader reader = new FileReader(file);
            Demand demand = json.fromJson(reader, Demand.class);
            reader.close();
            return demand;
        }
    }

    public void removeDemand(Demand demand) throws Exception {
        synchronized (demandsLock) {
            File file = new File(DEMANDS_SOURCE + "/" + demand.getId() + ".json");
            file.delete();
        }
    }

    public void updateDemand(Demand demand) throws Exception {
        synchronized (demandsLock) {
            File file = new File(DEMANDS_SOURCE + "/" + demand.getId() + ".json");
            if (!file.exists()) file.createNewFile();
            FileWriter writer = new FileWriter(file);
            json.toJson(demand, writer);
            writer.close();
        }
    }

    public LinkedList<Demand> allDemands() throws Exception {
        synchronized (demandsLock) {
            LinkedList<Demand> output = new LinkedList<>();
            File allFile = new File(DEMANDS_SOURCE);
            for (File file : Objects.requireNonNull(allFile.listFiles())) {
                FileReader reader = new FileReader(file);
                Demand demand = json.fromJson(reader, Demand.class);
                reader.close();
                output.add(demand);
            }
            return output;
        }
    }

    public LinkedList<Demand> getDemand(LinkedList<Integer> ids) throws Exception {
        LinkedList<Demand> output = new LinkedList<>();
        for (Integer id : ids) output.add(getDemand(id));
        return output;
    }

    // chat ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void addChat(Chat chat) throws Exception {
        synchronized (chatsLock) {
            File file = new File(CHATS_SOURCE + "/" + chat.getId() + ".json");
            if (!file.exists()) file.createNewFile();
            FileWriter writer = new FileWriter(file);
            json.toJson(chat, writer);
            writer.close();
        }
    }

    public Chat getChat(Integer id) throws Exception {
        synchronized (chatsLock) {
            File file = new File(CHATS_SOURCE + "/" + id + ".json");
            if (!file.exists()) return null;
            FileReader reader = new FileReader(file);
            Chat chat = json.fromJson(reader, Chat.class);
            reader.close();
            return chat;
        }
    }

    public void removeChat(Demand demand) throws Exception {
        synchronized (chatsLock) {
            File file = new File(CHATS_SOURCE + "/" + demand.getId() + ".json");
            file.delete();
        }
    }

    public void updateChat(Chat chat) throws Exception {
        synchronized (chatsLock) {
            File file = new File(CHATS_SOURCE + "/" + chat.getId() + ".json");
            if (!file.exists()) file.createNewFile();
            FileWriter writer = new FileWriter(file);
            json.toJson(chat, writer);
            writer.close();
        }
    }

    public LinkedList<Chat> allChats() throws Exception {
        synchronized (chatsLock) {
            LinkedList<Chat> output = new LinkedList<>();
            File allFile = new File(CHATS_SOURCE);
            for (File file : Objects.requireNonNull(allFile.listFiles())) {
                FileReader reader = new FileReader(file);
                Chat chat = json.fromJson(reader, Chat.class);
                reader.close();
                output.add(chat);
            }
            return output;
        }
    }

    public LinkedList<Chat> getChat(LinkedList<Integer> ids) throws Exception {
        LinkedList<Chat> output = new LinkedList<>();
        for (Integer id : ids) output.add(getChat(id));
        return output;
    }

    // message +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void addMessage(Message message) throws Exception {
        synchronized (messagesLock) {
            File file = new File(MESSAGES_SOURCE + "/" + message.getId() + ".json");
            if (!file.exists()) file.createNewFile();
            FileWriter writer = new FileWriter(file);
            json.toJson(message, writer);
            writer.close();
        }
    }

    public Message getMessage(Integer id) throws Exception {
        synchronized (messagesLock) {
            File file = new File(MESSAGES_SOURCE + "/" + id + ".json");
            if (!file.exists()) return null;
            FileReader reader = new FileReader(file);
            Message message = json.fromJson(reader, Message.class);
            reader.close();
            return message;
        }
    }

    public void removeMessage(Message message) throws Exception {
        synchronized (messagesLock) {
            File file = new File(MESSAGES_SOURCE + "/" + message.getId() + ".json");
            file.delete();
        }
    }

    public void updateMessage(Message message) throws Exception {
        synchronized (messagesLock) {
            File file = new File(MESSAGES_SOURCE + "/" + message.getId() + ".json");
            if (!file.exists()) file.createNewFile();
            FileWriter writer = new FileWriter(file);
            json.toJson(message, writer);
            writer.close();
        }
    }

    public LinkedList<Message> allMessage() throws Exception {
        synchronized (messagesLock) {
            LinkedList<Message> output = new LinkedList<>();
            File allFile = new File(MESSAGES_SOURCE);
            for (File file : Objects.requireNonNull(allFile.listFiles())) {
                FileReader reader = new FileReader(file);
                Message message = json.fromJson(reader, Message.class);
                reader.close();
                output.add(message);
            }
            return output;
        }
    }

    public LinkedList<Message> getMessage(LinkedList<Integer> ids) throws Exception {
        LinkedList<Message> output = new LinkedList<>();
        for (Integer id : ids) output.add(getMessage(id));
        return output;
    }

    public LinkedList<Student> filterStudent(FilterStudentForm form) throws Exception {
        LinkedList<Student> output = new LinkedList<>();
        for (User user : allUsers()) {
            if (!(user instanceof Student)) continue;
            Student student = (Student) user;
            if (form.getYear() != null && form.getYear() != student.getYear()) continue;
            if (form.getRand() != null && form.getRand() != student.getRand()) continue;
            if (form.getDegree() != null && form.getDegree() != student.getDegree()) continue;
            if (form.getDepartmentId() != null && form.getDepartmentId() != student.getDepartmentId()) continue;
            output.add(student);
        }
        return output;
    }

    public User getByName(String name) throws Exception {
        for (User user : allUsers()) {
            if (user.getName().equals(name)) return user;
        }
        return null;
    }

}


