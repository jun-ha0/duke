package duke.command;

import duke.DukeException;
import duke.common.Message;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UserInterface;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes exit program on user interface.
     *
     * @param taskList list of tasks.
     * @param ui user interface displaying program exiting.
     * @param storage local storage of data.
     */
    @Override
    public String execute(TaskList taskList, UserInterface ui, Storage storage) throws DukeException {
        return Message.MESSAGE_BYE;
//        ui.exitProgram();
    }

    /**
     * Returns true as program has terminated upon program exiting.
     * @return boolean true.
     */
    @Override
    public boolean isTerminated() {
        return true;
    }
}
