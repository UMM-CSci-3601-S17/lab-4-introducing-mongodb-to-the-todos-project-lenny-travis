package umm3601TodoTests;

import com.google.gson.Gson;
import org.junit.Test;
import umm3601.todo.Todo;
import umm3601.todo.TodoController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class sortTodos {
    @Test
    public void orderByOwner() throws IOException {
        TodoController todoController = new TodoController();
        Todo[] filterBodyTodos;
        Gson gson = new Gson();

        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("orderBy", new String[] {"owner"});
        String rawTodos = todoController.listTodos(queryParams);
        filterBodyTodos = gson.fromJson(rawTodos, Todo[].class);
        assertEquals("Incorrect ordering if constaint is owner (first element wrong)", "588959856f0b82ee93cd93eb", filterBodyTodos[0]._id);
        assertEquals("Incorrect ordering if constaint is owner (last element wrong)", "5889598507bf610948f8fb64",filterBodyTodos[299]._id);
    }

    @Test
    public void orderByBody() throws IOException {
        TodoController todoController = new TodoController();
        Todo[] filterBodyTodos;
        Gson gson = new Gson();

        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("orderBy", new String[] {"body"});
        String rawTodos = todoController.listTodos(queryParams);
        filterBodyTodos = gson.fromJson(rawTodos, Todo[].class);
        assertEquals("Incorrect ordering if constaint is body (first element wrong)", "58895985ffd38481b57cac97",filterBodyTodos[0]._id);
        assertEquals("Incorrect ordering if constaint is body (last element wrong)", "58895985afee3d5d4bde99d7",filterBodyTodos[299]._id);
    }

    @Test
    public void orderByStatus() throws IOException {
        TodoController todoController = new TodoController();
        Todo[] filterBodyTodos;
        Gson gson = new Gson();

        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("orderBy", new String[] {"status"});
        String rawTodos = todoController.listTodos(queryParams);
        filterBodyTodos = gson.fromJson(rawTodos, Todo[].class);
        assertEquals("Incorrect ordering if constaint is status (first element wrong)", "58895985a22c04e761776d54",filterBodyTodos[0]._id);
        assertEquals("Incorrect ordering if constaint is status (last element wrong)", "58895985a69d6afde00af172",filterBodyTodos[299]._id);
    }

    @Test
    public void orderByCategory() throws IOException {
        TodoController todoController = new TodoController();
        Todo[] filterBodyTodos;
        Gson gson = new Gson();

        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("orderBy", new String[] {"category"});
        String rawTodos = todoController.listTodos(queryParams);
        filterBodyTodos = gson.fromJson(rawTodos, Todo[].class);
        assertEquals("Incorrect ordering if constaint is catagory (first element wrong)", "5889598555fbbad472586a56",filterBodyTodos[0]._id);
        assertEquals("Incorrect ordering if constaint is catagory (last element wrong)", "5889598507bf610948f8fb64",filterBodyTodos[299]._id);
    }

    @Test
    public void orderByFrounds() throws IOException {
        TodoController todoController = new TodoController();
        Todo[] filterBodyTodos;
        Gson gson = new Gson();

        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("orderBy", new String[] {"frounds"});
        String rawTodos = todoController.listTodos(queryParams);
        filterBodyTodos = gson.fromJson(rawTodos, Todo[].class);
        assertEquals("Incorrect ordering if constraint is frounds (first element wrong)", "58895985a22c04e761776d54", filterBodyTodos[0]._id);
        assertEquals("Incorrect ordering if constraint is frounds (last element wrong)", "58895985f0a4bbea24084abf", filterBodyTodos[299]._id);
    }
}
