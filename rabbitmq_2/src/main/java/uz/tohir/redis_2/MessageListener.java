package uz.tohir.redis_2;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import uz.tohir.redis_2.config.MQConfig;
import uz.tohir.redis_2.payload.CustomMessage;

@Component
public class MessageListener {

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(CustomMessage message) {
        System.out.println(message);
    }
}
