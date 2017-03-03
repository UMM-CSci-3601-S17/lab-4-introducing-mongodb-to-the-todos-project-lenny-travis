package umm3601TodoTests;

import com.google.gson.Gson;
import org.junit.Test;
import umm3601.todo.Todo;
import umm3601.todo.TodoController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Dogxx000 on 3/2/17.
 */
public class LimitTodos {

    @Test
    public void limitTodos() throws IOException {
        TodoController todoController = new TodoController();
        Todo[] filterLimitTodos;
        Gson gson = new Gson();

        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("Limit", new String[] {"7"});
        String rawTodos = todoController.listTodos(queryParams);
        filterLimitTodos = gson.fromJson(rawTodos, Todo[].class);
        assertEquals("Incorrect number of limited todos (7 todos)", 7, filterLimitTodos.length);

        queryParams = new HashMap<>();
        queryParams.put("Limit", new String[] {"0"});
        rawTodos = todoController.listTodos(queryParams);
        filterLimitTodos = gson.fromJson(rawTodos, Todo[].class);
        assertEquals("Incorrect number of limited todos (0 todos)", 0, filterLimitTodos.length);

    }

}
