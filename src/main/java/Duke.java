public class Duke {
    private UserInterface ui;
    private TaskList taskList;

    private Duke() {
        ui = new UserInterface();
        taskList = new TaskList();
    }

    private void run() {
        boolean isTerminated = false;
        Task task;
        ui.showWelcomeMessage();
        while (!isTerminated) {
            try {
                String inputLine = ui.readLine();
                String command = getCommandFrom(inputLine);
                ui.showLine();
                switch (command) {
                    case "bye":
                        isTerminated = true;
                        ui.exitProgram();
                        break;
                    case "list":
                        ui.showTaskList(taskList.getTaskNames());
                        break;
                    case "done":
                        task = taskList.markDone(getIndexFrom(inputLine));
                        ui.showMarkDone(task);
                        break;
                    case "todo":
                        task = createTodoFrom(inputLine);
                        taskList.addTask(task);
                        ui.showAddition(task);
                        ui.showTaskSize(taskList);
                        break;
                    case "deadline":
                        task = createDeadlineFrom(inputLine);
                        taskList.addTask(task);
                        ui.showAddition(task);
                        ui.showTaskSize(taskList);
                        break;
                    case "event":
                        task = createEventFrom(inputLine);
                        taskList.addTask(task);
                        ui.showAddition(task);
                        ui.showTaskSize(taskList);
                        break;
                    default:
                        throw new DukeException(ui.MESSAGE_INVALID_COMMAND_FORMAT);
                }
            } catch (DukeException e) {
                ui.showExceptionMessage(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    private String getCommandFrom(String inputLine) {
        return inputLine.strip().split(" ")[0];
    }

    private int getIndexFrom(String inputLine) throws DukeException {
        try {
            int index = Integer.parseInt(inputLine.strip().split(" ")[1]);
            if (index <= 0 || index > taskList.size()) {
                throw new DukeException(ui.MESSAGE_INVALID_MARK_DONE_FORMAT);
            }
            return index;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ui.MESSAGE_INVALID_MARK_DONE_FORMAT);
        }
    }

    private Todo createTodoFrom(String inputLine) throws DukeException {
        String description = inputLine.substring("todo".length()).strip();
        if (description.isEmpty()) {
            throw new DukeException(ui.MESSAGE_INVALID_TODO_FORMAT);
        }
        return new Todo(description);
    }

    private Deadline createDeadlineFrom(String inputLine) throws DukeException {
        try {
            String[] deadlinePart = inputLine.substring("deadline".length()).split("/by");
            return new Deadline(deadlinePart[0].strip(), deadlinePart[1].strip());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ui.MESSAGE_INVALID_DEADLINE_FORMAT);
        }
    }

    private Event createEventFrom(String inputLine) throws DukeException {
        try {
            String[] eventPart = inputLine.substring("event".length()).split("/at");
            return new Event(eventPart[0].strip(), eventPart[1].strip());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ui.MESSAGE_INVALID_EVENT_FORMAT);
        }
    }

    public static void main (String[]args){
        Duke duke = new Duke();
        duke.run();
    }
}