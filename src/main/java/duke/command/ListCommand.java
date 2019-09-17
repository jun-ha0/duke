package duke.command;

import duke.DukeException;
import duke.common.Message;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Lists all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes listing of task list.
     *
     * @param dukeResponse response from Duke to user.
     *  @param taskList list of tasks.
     * @param storage local storage of data.
     */
    @Override
    public void execute(DukeResponse dukeResponse, TaskList taskList, Storage storage) throws DukeException {
        dukeResponse.add(String.format(Message.MESSAGE_SHOW_TASK_LIST, ""));
        for (String taskName : taskList.getTaskNames()) {
            dukeResponse.add(taskName + "\n");
        }
    }
}
