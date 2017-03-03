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

//import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.*;

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

        //when the target limit is 0, Mongo returns all items
        int targetLimit = 0;


        if (queryParams.containsKey("owner")) {
            String targetOwner = queryParams.get("owner")[0];
            filterDoc = filterDoc.append("owner", targetOwner);
        }

        if (queryParams.containsKey("contains")) {
            String targetBody = queryParams.get("contains")[0];
            filterDoc = filterDoc.append("body", new Document("$regex", targetBody));
        }

        if (queryParams.containsKey("category")) {
            String targetCategory = queryParams.get("category")[0];
//            String regex = "{ $regex: /" + targetCategory + "/ }";
            filterDoc = filterDoc.append("category", targetCategory);
        }


        if (queryParams.containsKey("status")) {
            String targetStatus = queryParams.get("status")[0];
            Boolean targetBoolean = false;
            if (targetStatus.equals("complete")) {
                targetBoolean = true;
            } else if (targetStatus.equals("incomplete")) {
                targetBoolean = false;
            } else {
                targetBoolean = null;
            }
            filterDoc = filterDoc.append("status", targetBoolean);
        }

        if (queryParams.containsKey("limit")) {
            try {
                int inputLimit = Integer.parseInt(queryParams.get("limit")[0]);
                if(inputLimit != Math.abs(inputLimit)) {
                    targetLimit = 0;
                }
                else {
                    targetLimit = inputLimit;
                }
            }
            catch (NumberFormatException parseExpception) {
                // targetInput will be zero (aka it will return everything)
            }
        }


        FindIterable<Document> matchingUsers = todoCollection.find(filterDoc).limit(targetLimit);

        return JSON.serialize(matchingUsers);
    }

    // Get a single todo
    public String getTodo(String id) {
        FindIterable<Document> jsonUsers
                = todoCollection
                .find(eq("_id", id));

        Iterator<Document> iterator = jsonUsers.iterator();

        Document user = iterator.next();

        return user.toJson();
    }


}
