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
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("queue_name", true, false, false, null);
        String message = "Hello World!";
        channel.basicPublish("", "queue_name", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
    }
}
