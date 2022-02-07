package io.quarkuscoffeeshop.web.infrastructure.testsupport;

import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkuscoffeeshop.web.infrastructure.MockOrderService;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RestTestProfile implements QuarkusTestProfile {

    @Override
    public List<TestResourceEntry> testResources() {
        return Collections.singletonList(new TestResourceEntry(KafkaTestResource.class));
    }

}
