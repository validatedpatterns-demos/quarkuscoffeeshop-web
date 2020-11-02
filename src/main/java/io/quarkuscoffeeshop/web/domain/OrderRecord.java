package io.quarkuscoffeeshop.web.domain;

import io.quarkuscoffeeshop.domain.OrderPlacedEvent;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

//@Entity
public class OrderRecord {//extends PanacheEntity {

    String orderId;

    String orderSource = "WEB";

    String rewardsId = "";

//    @OneToMany(mappedBy = "orderRecord", cascade = CascadeType.ALL)
    List<OrderLineItem> lineItems;

    public OrderRecord() {
    }

    public OrderRecord(String orderId) {
        this.orderId = orderId;
        this.lineItems = new ArrayList<>();
    }

    public static OrderRecord createFromOrderInCommand(final OrderPlacedEvent orderPlacedEvent) {
        OrderRecord orderRecord = new OrderRecord(orderPlacedEvent.id);
        orderRecord.rewardsId = orderPlacedEvent.rewardsId;
        orderRecord.lineItems.addAll(orderPlacedEvent.getBeverages().stream().map(beverage -> {
            return new OrderLineItem(orderRecord, beverage.item, beverage.name);
        }).collect(Collectors.toList()));
        orderRecord.lineItems.addAll(orderPlacedEvent.getKitchenOrders().stream().map(kitchenOrder -> {
            return new OrderLineItem(orderRecord, kitchenOrder.item, kitchenOrder.name);
        }).collect(Collectors.toList()));
        return orderRecord;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrderRecord.class.getSimpleName() + "[", "]")
                .add("orderId='" + orderId + "'")
                .add("orderSource='" + orderSource + "'")
                .add("rewardsId='" + rewardsId + "'")
                .add("lineItems=" + lineItems)
//                .add("id=" + id)
                .toString();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        OrderRecord that = (OrderRecord) o;

        return new EqualsBuilder()
                .append(orderId, that.orderId)
                .append(orderSource, that.orderSource)
                .append(rewardsId, that.rewardsId)
                .append(lineItems, that.lineItems)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(orderId)
                .append(orderSource)
                .append(rewardsId)
                .append(lineItems)
                .toHashCode();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getRewardsId() {
        return rewardsId;
    }

    public void setRewardsId(String rewardsId) { this.rewardsId = rewardsId; }

    public List<OrderLineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<OrderLineItem> lineItems) {
        this.lineItems = lineItems;
    }
}
