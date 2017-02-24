package umm3601TodoTests;


import com.google.gson.Gson;
import org.junit.Test;
import umm3601.todo.Todo;
import umm3601.todo.TodoController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class FilterTodosByStatus {

    @Test
    public void testTrue() throws IOException {
        TodoController todoController = new TodoController();
        Todo[] filterBodyTodos;
        Gson gson = new Gson();

        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("status", new String[] {"complete"});
        String rawTodos = todoController.listTodos(queryParams);
        filterBodyTodos = gson.fromJson(rawTodos, Todo[].class);
        assertEquals("Incorrect number todos for True",
                143, filterBodyTodos.length);
    }

    @Test
    public void testFalse() throws IOException {
        TodoController todoController = new TodoController();
        Todo[] filterBodyTodos;
        Gson gson = new Gson();

        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("status", new String[] {"incomplete"});
        String rawTodos = todoController.listTodos(queryParams);
        filterBodyTodos = gson.fromJson(rawTodos, Todo[].class);
        assertEquals("Incorrect number todos for True",
                157, filterBodyTodos.length);
    }

    @Test
    public void testOther() throws IOException {
        TodoController todoController = new TodoController();
        Todo[] filterBodyTodos;
        Gson gson = new Gson();

        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("status", new String[] {"potato"});
        String rawTodos = todoController.listTodos(queryParams);
        filterBodyTodos = gson.fromJson(rawTodos, Todo[].class);
        assertEquals("Incorrect number todos for True",
                0, filterBodyTodos.length);
    }
}
