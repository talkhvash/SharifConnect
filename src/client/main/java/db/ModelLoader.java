package client.main.java.db;

import server.main.java.db.DB;
import shared.model.edu.Captcha;
import shared.model.edu.course.Course;
import shared.model.edu.Department;
import shared.model.edu.user.User;

import java.util.ArrayList;

public class ModelLoader {

    private static ModelLoader loader;

    public static ModelLoader getLoader() {
        if (loader == null) loader = new ModelLoader();
        return loader;
    }

    public User loadUser(Integer id) {
        try {
            return DB.getDB().getUser(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Department loadDepartment(Integer id) {
        try {
            return DB.getDB().getDepartment(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Course loadCourse(Integer id) {
        try {
            return DB.getDB().getCourse(id);
        } catch (Exception e) {
            return new Course();
        }
    }
}
