package controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import utility.RabbitManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by XR on 2016/10/27.
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {
    @ResponseBody
    @RequestMapping(value = "/sendcommand")
    public Object sendcommcnd(){
        Map<String,String> map=new HashMap<String, String>();
        try {
            Channel channel = RabbitManager.getChannel();
            channel.queueDeclare("command", true, false, false, null);
            String message="开始发送";
            channel.basicPublish("", "command", null, message.getBytes());
            //System.out.println(" ["+i+"] Sent '" + message + "'");
            map.put("msg","命令发送成功");
        } catch (IOException e) {
            map.put("msg","命令发送失败");
        }
        return map;
    }
}
