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

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest @TestProfile(RestTestProfile.class)
public class RestResourceTest {

    @ConfigProperty(name = "orderUrl")
    String orderUrl;

    @Inject
    OrderService orderService;

    @BeforeAll
    public static void setup() {
        OrderService mockOrderService = Mockito.mock(OrderService.class);
        Mockito.when(mockOrderService.placeOrder(any(PlaceOrderCommand.class))).thenReturn(null);
        QuarkusMock.installMockForType(mockOrderService, OrderService.class);
    }

    String jsonPayload = "{\"commandType\":\"PLACE_ORDER\",\"baristaItems\":{\"empty\":false,\"present\":true},\"kitchenItems\":{\"empty\":false,\"present\":true},\"id\":\"3239f18a-9c6a-48b8-aca1-ecb12dce454e\",\"storeId\":\"ATLANTA\",\"orderSource\":\"WEB\",\"rewardsId\":{\"empty\":false,\"present\":true},\"total\":null}";

    @Test
    public void testOrderIn() {

        given()
                .body(jsonPayload)
                .when()
                .post(orderUrl)
                .then()
                .assertThat()
                .statusCode(202);
    }


}
