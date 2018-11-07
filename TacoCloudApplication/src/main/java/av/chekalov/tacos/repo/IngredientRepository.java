package av.chekalov.tacos.repo;

import av.chekalov.tacos.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
