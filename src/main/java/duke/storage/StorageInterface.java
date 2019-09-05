package duke.storage;

import duke.tasklist.TaskList;
import java.io.IOException;

public interface StorageInterface {

    public TaskList load() throws IOException;

    public void save(TaskList taskList);

}
