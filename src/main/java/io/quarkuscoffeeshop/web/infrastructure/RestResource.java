package io.quarkuscoffeeshop.web.infrastructure;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.runtime.annotations.RegisterForReflection;
import io.quarkuscoffeeshop.web.domain.PlaceOrderCommandJson;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.quarkuscoffeeshop.domain.*;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static io.quarkuscoffeeshop.web.infrastructure.JsonUtil.toJson;

@RegisterForReflection
@Path("/")
public class RestResource {

    Logger logger = LoggerFactory.getLogger(RestResource.class);

    @ConfigProperty(name="sourceUrl")
    String sourceUrl;

    @Inject
    OrderService orderService;

    @Inject
    Template cafeTemplate;

    Jsonb jsonb = JsonbBuilder.create();

    @GET
    @Path("cafe")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getIndex(){
        return cafeTemplate.data("sourceUrl", sourceUrl);
    }

    @POST
    @Path("order")
    @Transactional
    public Response orderIn(final PlaceOrderCommandJson placeOrderCommandJson) {

        logger.debug("order received: {}", toJson(placeOrderCommandJson));

        PlaceOrderCommand placeOrderCommand = new PlaceOrderCommand(OrderSource.WEB, placeOrderCommandJson.getBaristaItems(), placeOrderCommandJson.getKitchenItems());

        logger.debug("placeOrderCommand: {}", toJson(placeOrderCommand));

        return orderService.placeOrder(placeOrderCommand)
            .handle((res, ex) -> {
                if (ex != null) {
                    logger.error(ex.getMessage());
                    return Response.serverError().entity(ex).build();
                }else{
                    return Response.accepted().entity(placeOrderCommandJson).build();
                }
            }).join();
    }

}
