package av.chekalov.tacos.messaging;

import av.chekalov.tacos.entity.Order;

public interface OrderMessagingService {
    void sendOrder(Order order);
}
