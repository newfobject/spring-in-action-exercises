package av.chekalov.tacos.controller.api;

import av.chekalov.tacos.entity.Ingredient;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class IngredientResourceAssembler extends ResourceAssemblerSupport<Ingredient, IngredientResource> {

    public IngredientResourceAssembler() {
        super(IngredientController.class, IngredientResource.class);
    }

    @Override
    protected IngredientResource instantiateResource(Ingredient entity) {
        return new IngredientResource(entity);
    }

    @Override
    public IngredientResource toResource(Ingredient entity) {
        return createResourceWithId(entity.getId(), entity);
    }
}
