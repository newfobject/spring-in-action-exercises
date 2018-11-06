package av.chekalov.tacos.repo;

import av.chekalov.tacos.entity.Order;

public interface OrderRepository {
    Order save(Order order);
}
