package av.chekalov.tacos.controller.api;

import av.chekalov.tacos.entity.Taco;
import av.chekalov.tacos.repo.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public Iterable<Taco> recentTacos() {
        PageRequest pageRequest = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        return tacoRepository.findAll(pageRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable Long id) {
        Optional<Taco> optionalTaco = tacoRepository.findById(id);
        return optionalTaco.map(taco ->
                new ResponseEntity<>(taco, HttpStatus.OK))
                .orElseGet(() ->
                        new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}
