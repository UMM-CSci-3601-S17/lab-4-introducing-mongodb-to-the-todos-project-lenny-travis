package umm3601TodoTests;


import com.google.gson.Gson;
import org.junit.Test;
import umm3601.todo.Todo;
import umm3601.todo.TodoController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class FilterTodosById {
    @Test
    public void testIDs() throws IOException {
        TodoController todoController = new TodoController();
        Todo filterBodyTodos;
        Gson gson = new Gson();

        String rawTodos = todoController.getTodo("588959850ccede43cc675826");
        System.out.println(rawTodos);
        filterBodyTodos = gson.fromJson(rawTodos, Todo.class);
        System.out.println(filterBodyTodos.owner);
        assertEquals("Incorrect number todos for True",
                "Blanche", filterBodyTodos.owner);


        rawTodos = todoController.getTodo("5889598528c4748a0292e014");
        filterBodyTodos = gson.fromJson(rawTodos, Todo.class);
        assertEquals("Incorrect number todos for True", "Workman", filterBodyTodos.owner);
    }
}
