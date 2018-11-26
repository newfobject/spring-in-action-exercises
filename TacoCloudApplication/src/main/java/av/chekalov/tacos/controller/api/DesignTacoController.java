package av.chekalov.tacos.controller.api;

import av.chekalov.tacos.entity.Taco;
import av.chekalov.tacos.repo.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController(value = "DesignTacoControllerApi")
@RequestMapping(path = "/design",
        produces = {"application/json", "text/html"})
@CrossOrigin(origins = "*")
public class DesignTacoController {
    private TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping("/recent")
    public Resources<TacoResource> recentTacos() {
        PageRequest pageRequest = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        List<Taco> tacos = tacoRepository.findAll(pageRequest).getContent();

        List<TacoResource> tacoResources = new TacoResourceAssembler().toResources(tacos);
        Resources<TacoResource> recentResources = new Resources<>(tacoResources);
        recentResources.add(linkTo(methodOn(DesignTacoController.class).recentTacos())
                .withRel("recents"));

        return recentResources;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable Long id) {
        Optional<Taco> optionalTaco = tacoRepository.findById(id);
        return optionalTaco.map(taco ->
                new ResponseEntity<>(taco, HttpStatus.OK))
                .orElseGet(() ->
                        new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepository.save(taco);
    }
}
