package shared.model.edu.enums;

import java.util.LinkedList;

public enum ProfessorLevel {
    PROFESSOR("استاد تمام"),
    ASSISTANT_PROFESSOR("استادیار"),
    ASSOCIATE_PROFESSOR("دانشیار");

    private final String name;

    ProfessorLevel(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static LinkedList<ProfessorLevel> all() {
        LinkedList<ProfessorLevel> output = new LinkedList<>();
        output.add(PROFESSOR);
        output.add(ASSISTANT_PROFESSOR);
        output.add(ASSOCIATE_PROFESSOR);
        return output;
    }
}
