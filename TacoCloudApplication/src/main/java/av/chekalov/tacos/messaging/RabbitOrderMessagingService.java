package av.chekalov.tacos.messaging;

import av.chekalov.tacos.entity.Order;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitOrderMessagingService implements OrderMessagingService {

    private RabbitTemplate template;

    @Autowired
    public RabbitOrderMessagingService(RabbitTemplate template) {
        this.template = template;
    }

    @Override
    public void sendOrder(Order order) {
        MessageConverter converter = template.getMessageConverter();
        MessageProperties properties = new MessageProperties();
        properties.setHeader("X_ORDER_SOURCE", "WEB");
        Message message = converter.toMessage(order, properties);
        template.send("tacocloud.order", message);
    }
}
