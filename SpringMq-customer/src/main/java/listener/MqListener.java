package listener;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.bson.Document;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import utility.MongodbManager;
import utility.RabbitManager;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by admin on 2016/6/30.
 */
public class MqListener implements MessageListener {
    public void onMessage(Message message) {
        try {
            for (int i=0;i<200;i++){
                Channel channel=RabbitManager.getChannel();
                channel.queueDeclare("queue_two", true, false, false, null);
                String sendmessage = "Hello World!"+i+"";
                channel.basicPublish("", "queue_two", null, sendmessage.getBytes());
                MongoCollection collection= MongodbManager.getcollection("rabbitmq","wait");
                Document document=new Document();
                document.append("detail","message提交发送");
                document.append("createtime",new Date());
                collection.insertOne(document);
            }
        } catch (Exception e) {
            MongoCollection collection= MongodbManager.getcollection("rabbitmq","waiterror");
            Document document=new Document();
            document.append("detail","提交发送消费失败");
            document.append("createtime",new Date());
            collection.insertOne(document);
        }
        finally {

        }

    }
}
