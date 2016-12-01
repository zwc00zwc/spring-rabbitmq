package utility;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by XR on 2016/10/27.
 */
public class MongodbManager {
    private static Properties props = new Properties();
    static {
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("mongo.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static MongoClient client=null;

    private static MongoDatabase mongoDatabase=null;

    private static MongoClient authClient=null;

    private static MongoDatabase authMongoDatabase=null;

    public static MongoDatabase getDatabase(String database){
        if (mongoDatabase==null){
            if (client==null){
                client=new MongoClient("192.168.0.178",27017);
            }
            mongoDatabase=client.getDatabase(database);
        }
        return mongoDatabase;
    }

    /**
     * 认证
     * @return
     */
    public static MongoDatabase getAuthDatabase(){
        if (authMongoDatabase==null){
            if (authClient==null){
                MongoCredential credential = MongoCredential.createCredential(props.getProperty("db_username"), props.getProperty("db_name"), props.getProperty("db_password").toCharArray());
                authClient=new MongoClient(new ServerAddress(props.getProperty("mongo_ip"),Integer.parseInt(props.getProperty("mongo_port"))), Arrays.asList(credential));
            }
            authMongoDatabase=authClient.getDatabase(props.getProperty("db_name"));
        }
        return authMongoDatabase;
    }


}
