package io.quarkuscoffeeshop.web.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkuscoffeeshop.domain.*;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.Optional;

@RegisterForReflection
public class DashboardUpdate {

    public final String orderId;

    public final String itemId;

    public final String name;

    public final Item item;

    public final OrderStatus status;

    public final String madeBy;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public DashboardUpdate(
            @JsonProperty("orderId") final String orderId,
            @JsonProperty("itemId") String itemId,
            @JsonProperty("name") String name,
            @JsonProperty("item") Item item,
            @JsonProperty("status") OrderStatus status,
            @JsonProperty("madeBy") Optional<String> madeBy) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.name = name;
        this.item = item;
        this.status = status;
        if (madeBy.isPresent()) {
            this.madeBy = madeBy.get();
        }else{
            this.madeBy = "";
        }
    }
}
