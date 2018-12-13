package av.chekalov.tacos.messaging;

import av.chekalov.tacos.entity.Order;

public interface OrderReceiver {
    Order receiveOrder();
}
