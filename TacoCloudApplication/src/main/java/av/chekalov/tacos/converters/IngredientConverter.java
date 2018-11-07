package av.chekalov.tacos.converters;

import av.chekalov.tacos.entity.Ingredient;
import av.chekalov.tacos.repo.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String source) {
        return ingredientRepository.findById(source).orElse(null);
    }
}
