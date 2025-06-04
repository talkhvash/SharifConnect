package shared.model.edu.enums;

import java.util.LinkedList;

public enum Rand {
    RAND_1(1),
    RAND_2(2),
    RAND_3(3),
    RAND_4(4);

    private final int number;

    Rand(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return number + "";
    }

    public static LinkedList<Rand> all() {
        LinkedList<Rand> output = new LinkedList<>();
        output.add(RAND_1);
        output.add(RAND_2);
        output.add(RAND_3);
        output.add(RAND_4);
        return output;
    }
}
