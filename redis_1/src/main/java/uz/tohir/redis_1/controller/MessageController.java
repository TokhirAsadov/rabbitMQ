package uz.tohir.redis_1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.tohir.redis_1.config.MQConfig;
import uz.tohir.redis_1.payload.CustomMessage;

import java.util.Date;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MessageController {


    private final RabbitTemplate template;

    @PostMapping("/create")
    public String publishMessage(@RequestBody CustomMessage message){
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY,message);

        return "Message Published";
    }
}
