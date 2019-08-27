package duke.task;

public abstract class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void setDone(boolean doneStatus) {
        this.isDone = doneStatus;
    }

    public abstract String getSimplifiedRepresentation();

    @Override
    public String toString() {
        return (isDone ? "[\u2713] " : "[\u2718] ") + this.taskName;
    }
}
