import storage.StorageInterface;
import tasklist.TaskList;

import java.io.IOException;

public class StorageStub implements StorageInterface {
    @Override
    public TaskList load() throws IOException {
        return null;
    }

    @Override
    public void save(TaskList taskList) {
    }
}
