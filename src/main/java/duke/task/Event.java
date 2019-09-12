package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.parser.Parser;

/**
 * Represents an event at a given date and time.
 */
public class Event extends Task {
    private LocalDateTime eventDateTime;

    /**
     * Constructs an event at a given date and time with a description.
     *
     * @param description description of event.
     * @param eventDateTime date and time of event.
     */
    public Event(String description, LocalDateTime eventDateTime) {
        super(description);
        this.eventDateTime = eventDateTime;
    }

    /**
     * Returns a simplified summary of this event.
     *
     * @return simplified string representation.
     */
    @Override
    public String getSimplifiedRepresentation() {
        return "E | " + (super.isDone ? 1 : 0) + " | " + super.taskName + " | "
                + DateTimeFormatter.ofPattern("d/M/yyyy HHmm").format(eventDateTime);
    }

    /**
     * Returns an expressive string representation of this event.
     *
     * @return expressive string representation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Parser.getFormattedDateTimeFrom(eventDateTime) + ")";
    }
}
