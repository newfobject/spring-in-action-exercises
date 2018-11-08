package av.chekalov.tacos.repo;

import av.chekalov.tacos.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
