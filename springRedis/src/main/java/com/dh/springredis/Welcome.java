package com.dh.springredis;


import RabbitMq.NewTask;
import RabbitMq.Work;
import com.dh.springredis.rabbitmq.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Welcome {





    @GetMapping(value = "/welcome")
    public String welcome(Model model){
        model.addAttribute("hello","hello world");
        new RabbitSender().sendMsg("fsdfhsdjkfahskfshadfajfhasdjkf");
        return "test";
    }

    @GetMapping(value = "ok")
    @ResponseBody
    public String ok(){
        NewTask.test2();
        return "ok";
    }

}
