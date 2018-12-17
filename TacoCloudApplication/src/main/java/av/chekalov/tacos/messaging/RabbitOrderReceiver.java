package av.chekalov.tacos.messaging;

import av.chekalov.tacos.entity.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

@Component
public class RabbitOrderReceiver implements OrderReceiver {
    private static final int TIMEOUT_MILLIS = 30_000;
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitOrderReceiver(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public Order receiveOrder() {
        return rabbitTemplate.receiveAndConvert("tacocloud.orders", TIMEOUT_MILLIS,
                new ParameterizedTypeReference<Order>() {
                });
    }
}
