package duke.task;

/**
 * Represents a task which can be described with a name and a done status.
 * Task can take the form of a to-do, deadline or event.
 */
public abstract class Task {
    protected String taskName;
    protected boolean isDone;

    /**
     * Constructs an undone task which can be described with a name.
     *
     * @param taskName name of task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Returns the name of this task.
     *
     * @return the task name.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Sets this task to the given done status.
     *
     * @param doneStatus true if done, otherwise false.
     */
    public void setDone(boolean doneStatus) {
        this.isDone = doneStatus;
    }

    /**
     * Returns a simplified summary of this task.
     *
     * @return simplified string representation.
     */
    public abstract String getSimplifiedRepresentation();

    /**
     * Returns a string representation of this task.
     *
     * @return string representation.
     */
    @Override
    public String toString() {
        return (isDone ? "[\u2713] " : "[\u2718] ") + taskName;
    }
}
