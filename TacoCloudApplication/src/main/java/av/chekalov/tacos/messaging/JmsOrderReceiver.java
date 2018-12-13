package av.chekalov.tacos.messaging;

import av.chekalov.tacos.entity.Order;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsOrderReceiver implements OrderReceiver {
    private JmsTemplate jmsTemplate;

    public JmsOrderReceiver(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public Order receiveOrder() {
        return (Order) jmsTemplate.receiveAndConvert("tacocloud.order.queue");
    }
}
