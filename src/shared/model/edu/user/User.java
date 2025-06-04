package shared.model.edu.user;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class User {
    private int id;
    private String password;
    private String firstName;
    private String lastName;
    private long nationalNumber;
    private long phoneNumber;
    private String email;
    private int departmentId;
    private String imageBase64;
    private LocalDateTime lastInterTime;
    private LinkedList<Integer> passedCourseId = new LinkedList<>();
    private LinkedList<Integer> termCoursesId = new LinkedList<>();

    private LinkedList<Integer> chatsId = new LinkedList<>();


    @Override
    public String toString() {
        return getName();
    }

    public String getName() {
        return firstName + lastName;
    }

    // setters and getters
    public LinkedList<Integer> getChatsId() {
        return chatsId;
    }

    public void setChatsId(LinkedList<Integer> chatsId) {
        this.chatsId = chatsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getNationalNumber() {
        return nationalNumber;
    }

    public void setNationalNumber(long nationalNumber) {
        this.nationalNumber = nationalNumber;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public LocalDateTime getLastInterTime() {
        return lastInterTime;
    }

    public void setLastInterTime(LocalDateTime lastInterTime) {
        this.lastInterTime = lastInterTime;
    }

    public LinkedList<Integer> getPassedCourseId() {
        return passedCourseId;
    }

    public void setPassedCourseId(LinkedList<Integer> passedCourseId) {
        this.passedCourseId = passedCourseId;
    }

    public LinkedList<Integer> getTermCoursesId() {
        return termCoursesId;
    }

    public void setTermCoursesId(LinkedList<Integer> termCoursesId) {
        this.termCoursesId = termCoursesId;
    }
}
