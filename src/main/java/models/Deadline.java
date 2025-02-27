package models;

import java.time.LocalDateTime;

import static logic.parsers.DateTimeParser.parseDateTimeForOutput;
import static logic.parsers.DateTimeParser.parseStringFromDateTime;

public class Deadline extends Task {
    private LocalDateTime endTime;

    public Deadline(String name, Boolean marked, LocalDateTime endTime) {
        super(name, marked);
        this.endTime = endTime;
    }

    @Override
    public String getTaskDetails() {
        return "Deadline," + this.name + "," + this.isMarked + "," + parseStringFromDateTime(this.endTime);
    }

    @Override
    public String toString() {
        if (this.isMarked) {
            return "[D][X] " + this.name +
                    " (by: " + parseDateTimeForOutput(this.endTime) + ")";
        }

        return "[D][ ] " + this.name +
                " (by: " + parseDateTimeForOutput(this.endTime) + ")";
    }
}
