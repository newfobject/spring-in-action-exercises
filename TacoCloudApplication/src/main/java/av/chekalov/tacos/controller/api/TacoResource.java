package av.chekalov.tacos.controller.api;

import av.chekalov.tacos.entity.Taco;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.util.Date;
import java.util.List;

@Relation(value = "taco", collectionRelation = "tacos")
public class TacoResource extends ResourceSupport {

    private static final IngredientResourceAssembler ingredientAssembler = new IngredientResourceAssembler();

    @Getter
    private final String name;
    @Getter
    private final Date createdAt;
    @Getter
    private final List<IngredientResource> ingredients;

    public TacoResource(Taco taco) {
        this.createdAt = taco.getCreatedAt();
        this.ingredients = ingredientAssembler.toResources(taco.getIngredients());
        this.name = taco.getName();
    }
}