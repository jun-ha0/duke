package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UserInterface;

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
     * Executes addition of a task on user interface.
     *
     * @param taskList list of tasks.
     * @param ui user interface displaying the successful addition of a task.
     * @param storage local storage of data.
     */
    @Override
    public void execute(TaskList taskList, UserInterface ui, Storage storage) throws DukeException {
        taskList.addTask(task);
        storage.save(task.getSimplifiedRepresentation());
        ui.showAddition(task);
        ui.showTaskSize(taskList);
    }
}
