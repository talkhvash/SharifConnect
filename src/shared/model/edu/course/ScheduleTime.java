package shared.model.edu.course;

import java.time.LocalTime;

public class ScheduleTime {
    private boolean[] days = new boolean[6];

    private LocalTime start;
    private LocalTime end;

    // getters and setters
    public boolean[] getDays() {
        return days;
    }

    public void setDays(boolean[] days) {
        this.days = days;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }
}
