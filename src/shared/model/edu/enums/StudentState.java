package shared.model.edu.enums;

import java.util.LinkedList;

public enum StudentState {
    GRADUATE("فارغ التحصیل"),
    STUDY("در حال تحصیل"),
    WITHDRAW("انصراف از تحصیل");

    private final String name;

    StudentState(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static LinkedList<StudentState> all() {
        LinkedList<StudentState> output = new LinkedList<>();
        output.add(STUDY);
        output.add(GRADUATE);
        output.add(WITHDRAW);
        return output;
    }
}
