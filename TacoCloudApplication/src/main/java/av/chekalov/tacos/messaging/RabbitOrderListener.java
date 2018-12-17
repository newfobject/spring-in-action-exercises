package av.chekalov.tacos.messaging;

import av.chekalov.tacos.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitOrderListener {

    @RabbitListener(queues = "tacocloud.order.queue")
    public void receiveOrder(Order order) {
        log.info(order.toString());
    }
}
