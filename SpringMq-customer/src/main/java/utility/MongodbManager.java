package utility;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Created by XR on 2016/10/27.
 */
public class MongodbManager {
    private static MongoClient client=null;

    private static MongoDatabase mongoDatabase=null;

    private static MongoCollection collection=null;
    public static MongoCollection getcollection(String database,String tablename){
        if (collection==null){
            if (mongoDatabase==null){
                if (client==null){
                    client=new MongoClient("127.0.0.1",27017);
                }
                mongoDatabase=client.getDatabase(database);
            }
            collection=mongoDatabase.getCollection(tablename);
        }
        return collection;
    }
}
