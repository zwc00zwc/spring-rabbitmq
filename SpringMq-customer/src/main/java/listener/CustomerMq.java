package listener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
 * Created by admin on 2016/6/30.
 */
public class CustomerMq {
    public static void main(String[] args) throws IOException, InterruptedException {
        //区分不同工作进程的输出
        //int hashCode = Work.class.hashCode();
        //创建连接和频道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare("queue_one_key", false, false, false, null);
        //System.out.println(hashCode
         //       + " [*] Waiting for messages. To exit press CTRL+C");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 指定消费队列
        channel.basicConsume("queue_one_key", true, consumer);
        while (true)
        {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());

            System.out.println("message:"+message);
            //System.out.println(hashCode + " [x] Received '" + message + "'");
            //doWork(message);
            //System.out.println(hashCode + " [x] Done");

        }
    }
}
