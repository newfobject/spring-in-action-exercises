package av.chekalov.tacos.messaging;

import av.chekalov.tacos.entity.Order;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {

    private JmsTemplate jmsTemplate;

    public JmsOrderMessagingService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendOrder(Order order) {
        jmsTemplate.convertAndSend(
                "tacocloud.order.queue",
                order,
                this::postProcessMessage);
    }

    private Message postProcessMessage(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }
}
