package shared.model.edu.enums;

import java.util.LinkedList;

public enum Degree {
    UNDERGRADUATE("کارشناسی"),
    POSTGRADUATE("کارشناسی ارشد"),
    DOCTORATE("دکترا");

    private final String name;

    Degree(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public static LinkedList<Degree> all() {
        LinkedList<Degree> output = new LinkedList<>();
        output.add(DOCTORATE);
        output.add(POSTGRADUATE);
        output.add(DOCTORATE);
        return output;
    }
}
