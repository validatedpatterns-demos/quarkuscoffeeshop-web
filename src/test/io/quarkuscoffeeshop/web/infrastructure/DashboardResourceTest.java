package io.quarkuscoffeeshop.web.infrastructure;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkuscoffeeshop.web.testing.KafkaTestResource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import static io.restassured.RestAssured.when;

@QuarkusTest @QuarkusTestResource(KafkaTestResource.class) @TestHTTPEndpoint(DashboardResource.class)
public class DashboardResourceTest {

    @Test
    public void testStreaming() {

        try {
            URI uri = new URI("http://localhost:8080/dashboard/stream");
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder(uri).GET().build();
            Stream<String> lines = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofLines()).body();
            String result = lines.findFirst().get();
            assertNull(result, "Result should not be null");
//            when().get()
//                    .then()
//                    .statusCode(200)
//                    .body(is("hello"));
        } catch (URISyntaxException e) {
            assertNull(e, "Exception thrown");
        } catch (IOException e) {
            assertNull(e, "Exception thrown");
        } catch (InterruptedException e) {
            assertNull(e, "Exception thrown");
        }

    }

}
