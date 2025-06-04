package shared.model.edu.enums;

import java.util.LinkedList;

public enum Year {
    YEAR_1401(1401),
    YEAR_1400(1400),
    YEAR_1399(1399),
    YEAR_1398(1398),
    YEAR_1397(1397),
    YEAR_1396(1396),
    YEAR_1395(1395),
    YEAR_1394(1394),
    YEAR_1393(1393),
    YEAR_1392(1392),
    YEAR_1391(1391),
    YEAR_1390(1390);

    private final int number;

    Year(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return number + "";
    }

    public static LinkedList<Year> all() {
        LinkedList<Year> output = new LinkedList<>();
        output.add(YEAR_1390);
        output.add(YEAR_1391);
        output.add(YEAR_1392);
        output.add(YEAR_1393);
        output.add(YEAR_1394);
        output.add(YEAR_1395);
        output.add(YEAR_1396);
        output.add(YEAR_1397);
        output.add(YEAR_1398);
        output.add(YEAR_1399);
        output.add(YEAR_1400);
        output.add(YEAR_1401);
        return output;
    }

    public int getNumber() {
        return number;
    }

    public static Year of(Integer year) {
        switch (year) {
            case 1390:
                return YEAR_1390;
            case 1391:
                return YEAR_1391;
            case 1392:
                return YEAR_1392;
            case 1393:
                return YEAR_1393;
            case 1394:
                return YEAR_1394;
            case 1395:
                return YEAR_1395;
            case 1396:
                return YEAR_1396;
            case 1397:
                return YEAR_1397;
            case 1398:
                return YEAR_1398;
            case 1399:
                return YEAR_1399;
            case 1400:
                return YEAR_1400;
            case 1401:
                return YEAR_1401;
        }
        return null;
    }
}
