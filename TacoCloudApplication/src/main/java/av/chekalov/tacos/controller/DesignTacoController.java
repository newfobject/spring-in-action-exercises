package av.chekalov.tacos.controller;

import av.chekalov.tacos.entity.Ingredient;
import av.chekalov.tacos.entity.Order;
import av.chekalov.tacos.entity.Taco;
import av.chekalov.tacos.repo.IngredientRepository;
import av.chekalov.tacos.repo.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static av.chekalov.tacos.entity.Ingredient.Type;

@Controller
@Slf4j
@RequestMapping("/design")
@SessionAttributes("order")
@SuppressWarnings("squid:S3753") // setComplete on SessionStatus object called in OrderController
public class DesignTacoController {

    private static final String DESIGN = "design";
    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        model.addAttribute(DESIGN, new Taco());
        return DESIGN;
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors,
                                @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return DESIGN;
        }

        Taco saved = tacoRepository.save(design);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    private Object filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(i -> type == i.getType())
                .collect(Collectors.toList());
    }
}