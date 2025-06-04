package shared.model.edu.user;

import shared.model.edu.enums.ProfessorLevel;

import java.util.LinkedList;

public class Professor extends User {
    private int roomNumber;
    private ProfessorLevel level;
    private boolean chairman;
    private boolean viceChair;

    private LinkedList<Integer> superVisorId = new LinkedList<>();
    private LinkedList<Integer> recommendId = new LinkedList<>();

    // getters and setters
    public LinkedList<Integer> getSuperVisorsId() {
        return superVisorId;
    }

    public void setSuperVisorId(LinkedList<Integer> superVisorId) {
        this.superVisorId = superVisorId;
    }

    public LinkedList<Integer> getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(LinkedList<Integer> recommendId) {
        this.recommendId = recommendId;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public ProfessorLevel getLevel() {
        return level;
    }

    public void setLevel(ProfessorLevel level) {
        this.level = level;
    }

    public boolean isChairman() {
        return chairman;
    }

    public void setChairman(boolean chairman) {
        this.chairman = chairman;
    }

    public boolean isViceChair() {
        return viceChair;
    }

    public void setViceChair(boolean viceChair) {
        this.viceChair = viceChair;
    }

}
