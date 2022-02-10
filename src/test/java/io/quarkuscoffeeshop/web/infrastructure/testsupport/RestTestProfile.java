package io.quarkuscoffeeshop.web.infrastructure.testsupport;

import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkuscoffeeshop.web.infrastructure.OrderServiceMock;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RestTestProfile implements QuarkusTestProfile {

    @Override
    public Set<Class<?>> getEnabledAlternatives() {
        return Collections.singleton(OrderServiceMock.class);
    }

    @Override
    public List<TestResourceEntry> testResources() {
        return Collections.singletonList(new TestResourceEntry(KafkaTestResource.class));
    }
}
