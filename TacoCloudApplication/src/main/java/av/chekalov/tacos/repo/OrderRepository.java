package av.chekalov.tacos.repo;

import av.chekalov.tacos.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
