package io.quarkuscoffeeshop.web.infrastructure;

import io.quarkus.test.Mock;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

import org.slf4j.Logger;


@Alternative
@ApplicationScoped
public class MockOrderService extends OrderService {

    private static final Logger logger = LoggerFactory.getLogger(MockOrderService.class);

//    @Override
//    public CompletableFuture<Void> placeOrder(final PlaceOrderCommand placeOrderCommand){
//
//        logger.info("onOrderIn called on Mock: {}", placeOrderCommand);
//        return null;
//    }

}