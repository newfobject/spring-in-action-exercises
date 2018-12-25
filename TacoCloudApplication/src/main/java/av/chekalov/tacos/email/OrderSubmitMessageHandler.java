package av.chekalov.tacos.email;

import av.chekalov.tacos.entity.Order;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class OrderSubmitMessageHandler implements GenericHandler<Order> {
    private RestTemplate restTemplate;
    private ApiProperties apiProperties;

    public OrderSubmitMessageHandler(RestTemplate restTemplate, ApiProperties apiProperties) {
        this.restTemplate = restTemplate;
        this.apiProperties = apiProperties;
    }

    @Override
    public Object handle(Order payload, Map<String, Object> headers) {
        restTemplate.postForObject(apiProperties.getUrl(), payload, String.class);
        return null;
    }
}
