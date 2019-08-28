package duke.storage;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.UserInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static duke.common.Message.MESSAGE_ERROR_CREATING_STORAGE_FILE;
import static duke.common.Message.MESSAGE_ERROR_MISSING_STORAGE_FILE;
import static duke.common.Message.MESSAGE_ERROR_READING_FROM_FILE;
import static duke.common.Message.MESSAGE_STORAGE_FILE_CREATED;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

    private List<String> readFrom(String filePath) throws FileNotFoundException {
        File file = new File(this.filePath);
        Scanner scanner = new Scanner(file);
        List<String> storageLines = new ArrayList<>();
        while(scanner.hasNextLine()) {
            storageLines.add(scanner.nextLine());
        }
        return storageLines;
    }

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
                LocalDateTime by = LocalDateTime.parse(taskPart[3].strip(),
                        DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                task = new Deadline(description, by);
                break;
            case "E":
                LocalDateTime at = LocalDateTime.parse(taskPart[3].strip(),
                        DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                task = new Event(description, at);
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

    private static void createFileIfMissing(String filePath) throws DukeException {
        final File storageFile = new File(filePath);
        if (storageFile.exists()) {
            return;
        }
        System.out.println(String.format(MESSAGE_ERROR_MISSING_STORAGE_FILE, UserInterface.LINE_PREFIX, filePath));
        try {
            storageFile.createNewFile();
            System.out.println(String.format(MESSAGE_STORAGE_FILE_CREATED, UserInterface.LINE_PREFIX,filePath));
        } catch (IOException ioe) {
            throw new DukeException(String.format(MESSAGE_ERROR_CREATING_STORAGE_FILE, filePath, ioe.getMessage()));
        }
    }

    public void save(List<String> simplifiedTaskRepresentations) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            for (String simplifiedTaskRepresentation : simplifiedTaskRepresentations) {
                fileWriter.write(simplifiedTaskRepresentation + "\n");
            }
            fileWriter.close();
        } catch (IOException ioe) {
            throw new DukeException(MESSAGE_ERROR_MISSING_STORAGE_FILE);
        }
    }

    public void save(String simplifiedTaskRepresentation) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath, true);
            fileWriter.write(simplifiedTaskRepresentation + "\n");
            fileWriter.close();
        } catch (IOException ioe) {
            throw new DukeException(MESSAGE_ERROR_MISSING_STORAGE_FILE);
        }
    }
}
