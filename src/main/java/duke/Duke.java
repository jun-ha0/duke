package duke;

import java.util.Timer;
import java.util.TimerTask;

import duke.command.Command;
import duke.command.DukeResponse;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents the logic of application.
 *
 * @author Ng Jun Hao
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private static final String DEFAULT_STORAGE_FILEPATH = "data/tasks.txt";

    /**
     * Constructor for instantiating a Duke session.
     */
    public Duke() {
        storage = new Storage(DEFAULT_STORAGE_FILEPATH);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println("OOPS!!! " + e.getMessage());
            taskList = new TaskList();
            exit();
        }
    }

    void exit() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 250);
    }

    /**
     * Generates a response to user input.
     *
     * @param input user input.
     * @return response.
     */
    String getResponse(String input) {
        try {
            DukeResponse dukeResponse = new DukeResponse();
            Command command = Parser.parse(input);
            command.execute(dukeResponse, taskList, storage);
            return dukeResponse.toString();
        } catch (DukeException e) {
            return "OOPS!!! " + e.getMessage();
        }
    }
}
