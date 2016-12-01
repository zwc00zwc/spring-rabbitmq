package listener;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import utility.MongodbManager;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by XR on 2016/10/27.
 */
public class Mq3Listener implements MessageListener {
    public void onMessage(Message message) {
        String msg="";
        try {
            msg=new String(message.getBody(),"UTF-8");
            //System.out.println("【Mq3Listener】message2:"+msg);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MongoDatabase database=MongodbManager.getAuthDatabase();
            MongoCollection collection= database.getCollection("finish");
            Document document=new Document();
            document.append("detail","【Mq3Listener】"+msg+"消费成功");
            document.append("createtime",new Date());
            collection.insertOne(document);

            //System.out.println("【Mq3Listener】message:"+msg);
        } catch (UnsupportedEncodingException e) {
            MongoDatabase database=MongodbManager.getAuthDatabase();
            MongoCollection collection= database.getCollection("finisherror");
            Document document=new Document();
            document.append("detail","【Mq3Listener】"+msg+"消费失败");
            document.append("createtime",new Date());
            collection.insertOne(document);
        }
    }
}
