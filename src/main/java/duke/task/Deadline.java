package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.parser.Parser;

/**
 * Represents a deadline which is a task needed to be done by a given date and time.
 */
public class Deadline extends Task {
    private LocalDateTime deadlineDateTime;

    /**
     *  Constructs a deadline by a given date and time with a description.
     *
     * @param description description of deadline.
     * @param deadlineDateTime date and time of deadline.
     */
    public Deadline(String description, LocalDateTime deadlineDateTime) {
        super(description);
        this.deadlineDateTime = deadlineDateTime;
    }

    /**
     * Returns a simplified summary of this deadline.
     *
     * @return simplified string representation.
     */
    @Override
    public String getSimplifiedRepresentation() {
        return "D | " + (super.isDone ? 1 : 0) + " | " + super.taskName + " | "
                + DateTimeFormatter.ofPattern("d/M/yyyy HHmm").format(deadlineDateTime);
    }

    /**
     * Returns an expressive string representation of this deadline.
     *
     * @return expressive string representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.getFormattedDateTimeFrom(deadlineDateTime) + ")";
    }
}
