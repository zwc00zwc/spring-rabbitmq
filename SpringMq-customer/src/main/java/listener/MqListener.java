package listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.io.UnsupportedEncodingException;

/**
 * Created by admin on 2016/6/30.
 */
public class MqListener implements MessageListener {
    public void onMessage(Message message) {
        try {
            String msg=new String(message.getBody(),"UTF-8");
            System.out.println("message:"+msg);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
