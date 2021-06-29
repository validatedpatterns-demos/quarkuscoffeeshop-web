package io.quarkuscoffeeshop.web.testing;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import io.smallrye.reactive.messaging.connectors.InMemoryConnector;

import java.util.HashMap;
import java.util.Map;

public class KafkaTestResource  implements QuarkusTestResourceLifecycleManager {
    @Override
    public Map<String, String> start() {
        Map<String, String> env = new HashMap<>();
        Map<String, String> props1 = InMemoryConnector.switchIncomingChannelsToInMemory("web-updates");
        Map<String, String> props2 = InMemoryConnector.switchIncomingChannelsToInMemory("loyalty-updates");
        Map<String, String> props3 = InMemoryConnector.switchIncomingChannelsToInMemory("orders");
        env.putAll(props1);
        env.putAll(props2);
        env.putAll(props3);
        return env;
    }

    @Override
    public void stop() {

        InMemoryConnector.clear();
    }
}
