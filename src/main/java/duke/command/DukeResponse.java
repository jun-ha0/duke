package duke.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the response from Duke to user.
 */
public class DukeResponse {
    private List<String> responses;

    /**
     * Constructor of Duke's response to user.
     */
    public DukeResponse() {
        this.responses = new ArrayList<>();
    }

    /**
     * Adds a response to the entire list of responses.
     *
     * @param response response from Duke to user.
     */
    public void add(String response) {
        responses.add(response);
    }

    /**
     * Returns entire string representation of this Duke's response to user by appending newline to each response.
     *
     * @return entire string representation.
     */
    @Override
    public String toString() {
        StringBuilder fullDukeResponse = new StringBuilder();
        for (String response : responses) {
            fullDukeResponse.append(response);
            fullDukeResponse.append("\n");
        }
        return fullDukeResponse.toString();
    }
}
