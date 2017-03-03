package umm3601TodoTests;

import com.google.gson.Gson;
import org.junit.Test;
import umm3601.todo.Todo;
import umm3601.todo.TodoController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class FilterTodosByCategory {

    @Test
    public void filterTodosByCatagory() throws IOException {
        TodoController todoController = new TodoController();
        Todo[] filteredTodos;
        Gson gson = new Gson();

        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("category", new String[] {"software design"});
        String rawTodos = todoController.listTodos(queryParams);
        filteredTodos = gson.fromJson(rawTodos, Todo[].class);
        assertEquals("Incorrect number todos for software design", 74, filteredTodos.length);

        queryParams = new HashMap<>();
        queryParams.put("category", new String[] {"McPhee"});
        rawTodos = todoController.listTodos(queryParams);
        Todo[] filterMcPheeTodos = gson.fromJson(rawTodos, Todo[].class);
        assertEquals("Incorrect number of McPhee todos", 0, filterMcPheeTodos.length);

    }

}
