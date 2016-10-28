package listener;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by admin on 2016/6/29.
 */
public class MqListener implements MessageListener {
    public void onMessage(Message message) {
        try {
            String msg=new String(message.getBody(),"UTF-8");
            System.out.println("message:"+msg);
            MongoDatabase mongoDatabase;
            MongoClient client=new MongoClient("127.0.0.1",27017);
            mongoDatabase=client.getDatabase("zheng");
            MongoCollection collection= mongoDatabase.getCollection("log");
            Document document=new Document();
//            document.append("error",errortype);
//            document.append("detail",error);
            document.append("createtime",new Date());
            collection.insertOne(document);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
