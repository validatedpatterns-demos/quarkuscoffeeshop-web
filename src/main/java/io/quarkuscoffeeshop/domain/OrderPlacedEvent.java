package io.quarkuscoffeeshop.domain;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@RegisterForReflection
public class OrderPlacedEvent {

    public String id;

    OrderSource orderSource;

    public String rewardsId;

    public List<LineItem> beverages = new ArrayList<>();

    public List<LineItem> kitchenOrders = new ArrayList<>();

    public final EventType eventType = EventType.ORDER_PLACED;

    public OrderPlacedEvent() {
    }

    public List<LineItem> getBeverages() {
        return beverages == null ? new ArrayList<LineItem>() : beverages;
    }

    public List<LineItem> getKitchenOrders() {
        return kitchenOrders == null ? new ArrayList<LineItem>() : kitchenOrders;
    }

    public void addBeverages(final String id, final List<LineItem> beverageList) {
        this.id = id;
        this.beverages.addAll(beverageList);
    }

    public void addKitchenItems(final String id, final List<LineItem> kitchenOrdersList) {
        this.id = id;
        this.kitchenOrders.addAll(kitchenOrdersList);
    }

    public OrderPlacedEvent(String id, OrderSource orderSource, String rewardsId, List<LineItem> beverages, List<LineItem> kitchenOrders) {
        this.id = id;
        this.orderSource = orderSource;
        this.rewardsId = rewardsId;
        this.beverages = beverages;
        this.kitchenOrders = kitchenOrders;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrderPlacedEvent.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("orderSource=" + orderSource)
                .add("rewardsId=" + rewardsId)
                .add("beverages=" + beverages)
                .add("kitchenOrders=" + kitchenOrders)
                .add("eventType=" + eventType)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderPlacedEvent that = (OrderPlacedEvent) o;
        return Objects.equals(id, that.id) &&
                orderSource == that.orderSource &&
                rewardsId == that.rewardsId &&
                Objects.equals(beverages, that.beverages) &&
                Objects.equals(kitchenOrders, that.kitchenOrders) &&
                eventType == that.eventType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderSource, rewardsId, beverages, kitchenOrders, eventType);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderSource getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(OrderSource orderSource) {
        this.orderSource = orderSource;
    }

    public String getRewardsId() {
        return rewardsId;
    }

    public void setRewardsId(String rewardsId) { this.rewardsId = rewardsId; }

    public void setBeverages(List<LineItem> beverages) {
        this.beverages = beverages;
    }

    public void setKitchenOrders(List<LineItem> kitchenOrders) {
        this.kitchenOrders = kitchenOrders;
    }
}
