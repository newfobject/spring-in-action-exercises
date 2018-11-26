package av.chekalov.tacos.controller.api;

import av.chekalov.tacos.entity.Ingredient;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

public class IngredientResource extends ResourceSupport {
    @Getter
    private String name;
    @Getter
    private Ingredient.Type type;

    public IngredientResource(Ingredient entity) {
        this.name = entity.getName();
        this.type = entity.getType();
    }
}
