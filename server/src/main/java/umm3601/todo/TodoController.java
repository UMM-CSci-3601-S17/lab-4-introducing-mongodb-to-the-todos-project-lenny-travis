package umm3601.todo;

import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Sorts;
import com.mongodb.util.JSON;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

public class TodoController {

    private final MongoCollection<Document> todoCollection;

    public TodoController() throws IOException {
        // Set up our server address
        // (Default host: 'localhost', default port: 27017)
        // ServerAddress testAddress = new ServerAddress();

        // Try connecting to the server
        //MongoClient mongoClient = new MongoClient(testAddress, credentials);
        MongoClient mongoClient = new MongoClient(); // Defaults!

        // Try connecting to a database
        MongoDatabase db = mongoClient.getDatabase("test");

        todoCollection = db.getCollection("todos");
    }

    // List todos
    public String listTodos(Map<String, String[]> queryParams) {
        Document filterDoc = new Document();

        if (queryParams.containsKey("owner")) {
            int targetOwner = Integer.parseInt(queryParams.get("owner")[0]);
            filterDoc = filterDoc.append("owner", targetOwner);
        }

        FindIterable<Document> matchingUsers = todoCollection.find(filterDoc);

        return JSON.serialize(matchingUsers);
    }

    // Get a single todo
    public String getTodo(String id) {
        FindIterable<Document> jsonUsers
                = todoCollection
                .find(eq("_id", new ObjectId(id)));

        Iterator<Document> iterator = jsonUsers.iterator();

        Document user = iterator.next();

        return user.toJson();
    }


    public String filterTodosByOwner(String searchBy) {
        Document filterDoc = new Document();


            filterDoc = filterDoc.append("owner", searchBy);


        FindIterable<Document> matchingUsers = todoCollection.find(filterDoc);

        return JSON.serialize(matchingUsers);
    }
}
