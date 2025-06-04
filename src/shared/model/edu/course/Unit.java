package shared.model.edu.course;

public enum Unit {
    ONE("یک", 1),
    TOW("دو", 2),
    THREE("سه", 3),
    FOUR("چهار", 4);

    private final String name;
    private final int number;

    Unit(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return name;
    }
}
