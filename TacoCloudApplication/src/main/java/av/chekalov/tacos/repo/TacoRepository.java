package av.chekalov.tacos.repo;

import av.chekalov.tacos.entity.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
