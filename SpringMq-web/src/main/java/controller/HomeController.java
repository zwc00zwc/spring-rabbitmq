package controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2016/6/29.
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {
    @Autowired
    private AmqpTemplate amqpTemplate;
    @ResponseBody
    @RequestMapping(value = "/index")
    public String index(){
        amqpTemplate.convertAndSend("queue_one_key", "hello world");
        return "send";
    }

    @ResponseBody
    @RequestMapping(value = "/index1")
    public String index1(){
        amqpTemplate.convertAndSend("queue_two_key","queue_two");
        return "send2";
    }
}
