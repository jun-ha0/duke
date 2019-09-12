package duke.storage;

import static duke.common.Message.MESSAGE_ERROR_CREATING_STORAGE_FILE;
import static duke.common.Message.MESSAGE_ERROR_MISSING_STORAGE_FILE;
import static duke.common.Message.MESSAGE_ERROR_READING_FROM_FILE;
import static duke.common.Message.MESSAGE_STORAGE_FILE_CREATED;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents local storage of data from Duke application.
 */
public class Storage {
    private final String filePath;
    public static final String LINE_PREFIX = "     ";

    /**
     * Constructor of data storage at a given file.
     *
     * @param filePath path of storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the Duke application data from this storage file if file exists.
     * Otherwise, create the missing file using the given file path.
     *
     * @return the list of tasks being loaded from storage file.
     * @throws DukeException if missing file is unable to be created and/or there were errors reading data from file.
     */
    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        try {
            List<String> storageLines = readFrom(this.filePath);
            for (String storageLine : storageLines) {
                if (storageLine.strip().isEmpty()) {
                    continue;
                }
                tasks.add(createTaskFrom(storageLine));
            }
        } catch (FileNotFoundException fnfe) {
            createFileIfMissing(this.filePath);
        }
        return tasks;
    }

    /**
     * Reads data from this storage file. Reads maximum one task per line.
     *
     * @param filePath path of storage file.
     * @return list of lines being read.
     * @throws FileNotFoundException if specified file is missing.
     */
    private List<String> readFrom(String filePath) throws FileNotFoundException {
        File file = new File(this.filePath);
        Scanner scanner = new Scanner(file);
        List<String> storageLines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            storageLines.add(scanner.nextLine());
        }
        return storageLines;
    }

    /**
     * Creates task from reading and parsing the lines in the storage file.
     *
     * @param storageLine lines in the storage file.
     * @return task created.
     * @throws DukeException if there were errors reading data from file.
     */
    private Task createTaskFrom(String storageLine) throws DukeException {
        Task task;
        try {
            String[] taskPart = storageLine.strip().split("\\|");
            String typeOfTask = taskPart[0].strip();
            String doneStatus = taskPart[1].strip();
            String description = taskPart[2].strip();
            switch (typeOfTask) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                LocalDateTime deadlineDateTime = LocalDateTime.parse(taskPart[3].strip(),
                        DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                task = new Deadline(description, deadlineDateTime);
                break;
            case "E":
                LocalDateTime eventDateTime = LocalDateTime.parse(taskPart[3].strip(),
                        DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                task = new Event(description, eventDateTime);
                break;
            default:
                throw new DukeException(MESSAGE_ERROR_READING_FROM_FILE);
            }
            task.setDone(doneStatus.equals("1"));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(MESSAGE_ERROR_READING_FROM_FILE);
        }
        return task;
    }

    /**
     * Creates the file using the given file path if file is missing.
     *
     * @param filePath path of the file.
     * @throws DukeException if there were errors when creating the file.
     */
    private static void createFileIfMissing(String filePath) throws DukeException {
        final File storageFile = new File(filePath);
        if (storageFile.exists()) {
            return;
        }
        System.out.println(String.format(MESSAGE_ERROR_MISSING_STORAGE_FILE, LINE_PREFIX, filePath));
        try {
            storageFile.createNewFile();
            System.out.println(String.format(MESSAGE_STORAGE_FILE_CREATED, LINE_PREFIX,filePath));
        } catch (IOException ioe) {
            throw new DukeException(String.format(MESSAGE_ERROR_CREATING_STORAGE_FILE, filePath, ioe.getMessage()));
        }
        assert storageFile.exists();
        assert storageFile.isFile();
        assert storageFile.canRead();
        assert storageFile.canWrite();
    }

    /**
     * Saves the list of simplified task representations to the storage file.
     *
     * @param simplifiedTaskRepresentations list of simplified task representations.
     * @throws DukeException if there were errors converting and/or storing data to file.
     */
    public void save(List<String> simplifiedTaskRepresentations) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (String simplifiedTaskRepresentation : simplifiedTaskRepresentations) {
                fileWriter.write(simplifiedTaskRepresentation + "\n");
            }
            fileWriter.close();
        } catch (IOException ioe) {
            throw new DukeException(MESSAGE_ERROR_MISSING_STORAGE_FILE);
        }
    }

    /**
     * Saves the simplified task representation to the storage file.
     *
     * @param simplifiedTaskRepresentation simplified task representation.
     * @throws DukeException if there were errors converting and/or storing data to file.
     */
    public void save(String simplifiedTaskRepresentation) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            fileWriter.write(simplifiedTaskRepresentation + "\n");
            fileWriter.close();
        } catch (IOException ioe) {
            throw new DukeException(MESSAGE_ERROR_MISSING_STORAGE_FILE);
        }
    }
}
