package io.quarkuscoffeeshop.web.infrastructure;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.quarkuscoffeeshop.web.domain.commands.PlaceOrderCommand;
import io.quarkuscoffeeshop.web.infrastructure.testsupport.RestTestProfile;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import java.util.concurrent.CompletableFuture;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest //@TestProfile(RestTestProfile.class)
public class RestResourceTest {

    @ConfigProperty(name = "orderUrl")
    String orderUrl;

//    @Inject
//    OrderService orderService;

    @BeforeAll
    public static void setup() {
        OrderService mock = Mockito.mock(OrderService.class);
        Mockito.when(mock.placeOrder(any(PlaceOrderCommand.class)))
                .thenReturn(CompletableFuture.completedFuture(null));
        QuarkusMock.installMockForType(mock, OrderService.class);
    }

    String jsonPayload = "{\"commandType\":\"PLACE_ORDER\",\"baristaItems\":[{\"item\":\"CAPPUCCINO\",\"price\":2.99,\"name\":\"Goofy\"}],\"kitchenItems\":[{\"item\":\"CROISSANT\",\"price\":2.99,\"name\":\"Goofy\"}],\"id\":\"b2c56eb2-fd7e-4a46-b1f9-85df85ad0068\",\"storeId\":\"ATLANTA\",\"orderSource\":\"WEB\",\"rewardsId\":null,\"total\":5.98}";

    @Test
    public void testOrderIn() {

        given()
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .body(jsonPayload)
                .when()
                .post(orderUrl+ "/order")
                .then()
                .assertThat()
                .statusCode(202);
    }


}