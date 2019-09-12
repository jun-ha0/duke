package duke.command;

import duke.DukeException;
import duke.common.Message;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Adds a task to storage file.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Creates a command to add a given task to the storage file.
     *
     * @param task task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes addition of a task.
     *
     * @param dukeResponse response from Duke to user.
     * @param taskList list of tasks.
     * @param storage local storage of data.
     */
    @Override
    public void execute(DukeResponse dukeResponse, TaskList taskList, Storage storage) throws DukeException {
        assert task != null;
        taskList.addTask(task);
        storage.save(task.getSimplifiedRepresentation());
        dukeResponse.add(Message.MESSAGE_ADDED);
        dukeResponse.add(" " + task);
        dukeResponse.add(String.format(Message.MESSAGE_SHOW_TASK_SIZE, taskList.size()));
    }
}
