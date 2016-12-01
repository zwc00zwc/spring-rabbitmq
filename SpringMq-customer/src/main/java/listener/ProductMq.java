package listener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
 * Created by admin on 2016/7/1.
 */
public class ProductMq {
    public static void main(String[] args) throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.0.159");
        Connection connection = factory.newConnection();
        for (int i=0;i<10;i++){
            Channel channel = connection.createChannel();
            channel.queueDeclare("command", true, false, false, null);

            String message = "Hello World!"+i+"";
            channel.basicPublish("", "command", null, message.getBytes());
            System.out.println(" ["+i+"] Sent '" + message + "'");
        }
    }
}
