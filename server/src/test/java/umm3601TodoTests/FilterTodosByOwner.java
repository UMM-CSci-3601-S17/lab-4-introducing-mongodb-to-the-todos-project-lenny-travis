package umm3601TodoTests;

import com.google.gson.Gson;
import org.junit.Test;
import umm3601.todo.Todo;
import umm3601.todo.TodoController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import static junit.framework.TestCase.assertEquals;

public class FilterTodosByOwner {
    /*@Test
    public void filterTodosByOwner() throws IOException {
        TodoController todoController = new TodoController();
        Todo[] allTodos = todoController.listTodos(new HashMap<>());
        Todo[] filterFryTodos = todoController.filterTodosByOwner(allTodos, "Fry");
        assertEquals("Incorrect number todos for Ms Fry", 61, filterFryTodos.length);
        Todo[] filterMcPheeTodos = todoController.filterTodosByOwner(allTodos, "McPhee");
        assertEquals("Incorrect number of McPhees", 0, filterMcPheeTodos.length);
    }

    @Test
    public void listTodosWithOwnerFilter() throws IOException {
        TodoController todoController = new TodoController();
        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("owner", new String[] {"Fry"});
        Todo[] filterFryTodos = todoController.listTodos(queryParams);
        assertEquals("Incorrect number todos for Ms Fry", 61, filterFryTodos.length);
        queryParams.put("owner", new String[] {"McPhee"});
        Todo[] filterMcPheeTodos = todoController.listTodos(queryParams);
        assertEquals("Incorrect number of McPhees", 0, filterMcPheeTodos.length);
    }*/

    @Test
    public void filterTodosByOwner() throws IOException {
        TodoController todoController = new TodoController();
        Todo[] filterFryTodos;
        Gson gson = new Gson();

        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("owner", new String[] {"Fry"});
        String rawTodos = todoController.listTodos(queryParams);
        filterFryTodos = gson.fromJson(rawTodos, Todo[].class);
        assertEquals("Incorrect number todos for Fry", 61, filterFryTodos.length);

        queryParams = new HashMap<>();
        queryParams.put("owner", new String[] {"McPhee"});
        rawTodos = todoController.listTodos(queryParams);
        Todo[] filterMcPheeTodos = gson.fromJson(rawTodos, Todo[].class);
        assertEquals("Incorrect number of McPhees", 0, filterMcPheeTodos.length);

    }

    @Test
    public void grabAllTodos() throws IOException {
        TodoController todoController = new TodoController();
        Todo[] getAllTodos;
        Gson gson = new Gson();

        Map<String, String[]> queryParams = new HashMap<>();

        String rawTodos = todoController.listTodos(queryParams);
        getAllTodos = gson.fromJson(rawTodos, Todo[].class);
        //System.out.println(rawTodos);
        assertEquals("Incorrect number of Todos", 300, getAllTodos.length);
    }
}