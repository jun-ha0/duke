package duke.common;

/**
 * Contains messages displayed to user.
 */
public class Message {
    public static final String MESSAGE_WELCOME = "Hello! I'm Duke\nWhat can I do for you?\n\n";
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n\n";
    public static final String MESSAGE_ADDED = "Got it. I've added this task:\n";
    public static final String MESSAGE_DELETED = "Noted. I've removed this task:\n";
    public static final String MESSAGE_MARK_DONE = "Nice! I've marked this task as done:\n";
    public static final String MESSAGE_SHOW_TASK_LIST = "Here are the%1$s tasks in your list:\n";
    public static final String MESSAGE_SHOW_TASK_SIZE = "\nNow you have %1$s tasks in the list.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_INVALID_TODO_FORMAT = "The description of a todo cannot be empty.";
    public static final String MESSAGE_INVALID_DEADLINE_FORMAT = "Deadlines must have /by and cannot be empty. "
            + "E.g. deadline return book /by 2/12/2019 1800";
    public static final String MESSAGE_INVALID_EVENT_FORMAT = "Events must have /at and cannot be empty. "
            + "E.g. event project meeting /at 2/12/2019 1800";
    public static final String MESSAGE_INVALID_TASK_INDEX = "Invalid task number. Task numbers use 1-based indexing.";
    public static final String MESSAGE_INVALID_KEYWORD_FORMAT = "Invalid keyword. Specify keyword to search for tasks.";
    public static final String MESSAGE_ERROR_MISSING_STORAGE_FILE = "Missing storage file: %1$s";
    public static final String MESSAGE_ERROR_CREATING_STORAGE_FILE = "Unable to create new file %1$s. %2$s";
    public static final String MESSAGE_STORAGE_FILE_CREATED = "Created new empty storage file: %1$s";
    public static final String MESSAGE_ERROR_READING_FROM_FILE = "Error when reading file";
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Invalid date. Use d/M/yyyy HHmm (2/12/2019 1800) format.";
    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
}
