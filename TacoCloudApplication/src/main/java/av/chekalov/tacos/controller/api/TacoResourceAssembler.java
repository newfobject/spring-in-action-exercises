package av.chekalov.tacos.controller.api;

import av.chekalov.tacos.entity.Taco;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class TacoResourceAssembler extends ResourceAssemblerSupport<Taco, TacoResource> {

    public TacoResourceAssembler() {
        super(DesignTacoController.class, TacoResource.class);
    }

    @Override
    protected TacoResource instantiateResource(Taco entity) {
        return new TacoResource(entity);
    }

    @Override
    public TacoResource toResource(Taco entity) {
        return createResourceWithId(entity.getId(), entity);
    }
}
