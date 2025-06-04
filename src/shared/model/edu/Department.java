package shared.model.edu;

import java.util.LinkedList;

public class Department {
    private int id;
    private String name;
    private LinkedList<Integer> professorsId = new LinkedList<>();
    private LinkedList<Integer> studentsId = new LinkedList<>();
    private LinkedList<Integer> coursesId = new LinkedList<>();

    private LinkedList<Integer> inMinorId = new LinkedList<>();
    private LinkedList<Integer> outMinorId = new LinkedList<>();
    private LinkedList<Integer> withdrawId = new LinkedList<>();
    private LinkedList<Integer> dissertationId = new LinkedList<>();
    private LinkedList<Integer> selectUnitDemandId = new LinkedList<>();

    private Integer viceChairId;
    private Integer chairManId;
    private boolean isMaaref;

    // getters and setters
    public LinkedList<Integer> getSelectUnitDemandId() {
        return selectUnitDemandId;
    }

    public void setSelectUnitDemandId(LinkedList<Integer> selectUnitDemandId) {
        this.selectUnitDemandId = selectUnitDemandId;
    }

    public Integer getChairManId() {
        return chairManId;
    }

    public void setChairManId(Integer chairManId) {
        this.chairManId = chairManId;
    }

    public boolean isMaaref() {
        return isMaaref;
    }

    public void setMaaref(boolean maaref) {
        isMaaref = maaref;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Integer> getProfessorsId() {
        return professorsId;
    }

    public void setProfessorsId(LinkedList<Integer> professorsId) {
        this.professorsId = professorsId;
    }

    public LinkedList<Integer> getStudentsId() {
        return studentsId;
    }

    public void setStudentsId(LinkedList<Integer> studentsId) {
        this.studentsId = studentsId;
    }

    public LinkedList<Integer> getCoursesId() {
        return coursesId;
    }

    public void setCoursesId(LinkedList<Integer> coursesId) {
        this.coursesId = coursesId;
    }

    public LinkedList<Integer> getInMinorId() {
        return inMinorId;
    }

    public void setInMinorId(LinkedList<Integer> inMinorId) {
        this.inMinorId = inMinorId;
    }

    public LinkedList<Integer> getOutMinorId() {
        return outMinorId;
    }

    public void setOutMinorId(LinkedList<Integer> outMinorId) {
        this.outMinorId = outMinorId;
    }

    public LinkedList<Integer> getWithdrawId() {
        return withdrawId;
    }

    public void setWithdrawId(LinkedList<Integer> withdrawId) {
        this.withdrawId = withdrawId;
    }

    public LinkedList<Integer> getDissertationId() {
        return dissertationId;
    }

    public void setDissertationId(LinkedList<Integer> dissertationId) {
        this.dissertationId = dissertationId;
    }

    public Integer getViceChairId() {
        return viceChairId;
    }

    public void setViceChairId(Integer viceChairId) {
        this.viceChairId = viceChairId;
    }
}
