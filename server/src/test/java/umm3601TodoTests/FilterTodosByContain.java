package umm3601TodoTests;

import com.google.gson.Gson;
import org.junit.Test;
import umm3601.todo.Todo;
import umm3601.todo.TodoController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class FilterTodosByContain {

    @Test
    public void filterTodosByContain() throws IOException {
        TodoController todoController = new TodoController();
        Todo[] filterBodyTodos;
        Gson gson = new Gson();

        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("contains", new String[] {"Banana"});
        String rawTodos = todoController.listTodos(queryParams);
        filterBodyTodos = gson.fromJson(rawTodos, Todo[].class);
        assertEquals("Incorrect number todos for banana", 0, filterBodyTodos.length);

        queryParams = new HashMap<>();
        queryParams.put("contains", new String[] {"consectetur"});
        rawTodos = todoController.listTodos(queryParams);
        Todo[] filterConsecteturBodyTodos = gson.fromJson(rawTodos, Todo[].class);
        assertEquals("Incorrect number of consectetur", 85, filterConsecteturBodyTodos.length);

    }

}
