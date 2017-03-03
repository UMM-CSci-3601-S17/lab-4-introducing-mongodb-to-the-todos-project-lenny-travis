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
import java.util.*;

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
        //clear list is true when the input limit = 0
        boolean clearList = false;
        /*
        This String will set the type that orderBy will sort with,
        if mongoDB's sort does not get a key it can sort by it just returns an unsorted result.
        Pony is not a key in the JSON object.
         */
        String sortBy = "pony";


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

        if(queryParams.containsKey("orderBy")) {
            String orderByInput = queryParams.get("orderBy")[0];
           if(orderByInput.equals("owner") || orderByInput.equals("body") || orderByInput.equals("status") || orderByInput.equals("category")){
               sortBy = orderByInput;
           }
        }

        if (queryParams.containsKey("limit")) {
            try {
                int inputLimit = Integer.parseInt(queryParams.get("limit")[0]);
                if(inputLimit > 0) {
                    targetLimit = 0;
                }
                else if(inputLimit == 0) {
                    clearList = true;
                }
                else {
                    targetLimit = inputLimit;
                }
            }
            catch (NumberFormatException parseExpception) {
                // targetInput will be zero (aka it will return everything)
            }
        }

        if(clearList){
            filterDoc.clear();
            filterDoc.append("_id", null);
        }

        FindIterable<Document> matchingUsers = todoCollection.find(filterDoc).sort(Sorts.ascending(sortBy)).limit(targetLimit);

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
