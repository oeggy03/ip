package logic.commandlogic;

import exceptions.DukeInvalidDateTimeException;
import exceptions.DukeInvalidFormatException;
import models.Deadline;
import models.TaskArray;

import static logic.parsers.DateTimeParser.parseDateTimeFromString;

/**
 * DeadlineHandler handles all 'deadline' commands.
 */
public class DeadlineHandler implements Command {
    private TaskArray tasks;

    /**
     * Constructor for DeadlineHandler.
     *
     * @param tasks The TaskArray we are working with
     */
    public DeadlineHandler(TaskArray tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the content of the input.
     *
     * @param commandContent The content of the input.
     */
    @Override
    public String parseCommandContent(String commandContent) {
        try {
            assert(!commandContent.equals("")): "You cannot add an empty deadline task!";

            String[] taskArr = commandContent.split("/", 2);

            if (taskArr.length != 2) {
                String errorStr = "\nFormat for Deadline task incorrect!\n" +
                        "Expected 2 parts (Task name & deadline) in input, got " + taskArr.length;
                throw new DukeInvalidFormatException(errorStr);
            }

            tasks.addTask(new Deadline(taskArr[0].strip(),
                    false, parseDateTimeFromString(taskArr[1].strip())));

            return ("Got it, I've added this task: \n" +
                    tasks.get(tasks.size() - 1) + "\n" +
                    "You now have " + tasks.size() + " task(s) in the list");

        } catch (DukeInvalidFormatException | DukeInvalidDateTimeException | AssertionError e) {
            return ("Something went wrong! Please format the task properly and add it again. \n" +
                        "Error: " + e);
        }
    }
}
