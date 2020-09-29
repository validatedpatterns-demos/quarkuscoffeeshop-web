package io.quarkuscoffeeshop.web.domain;

import io.quarkus.runtime.annotations.RegisterForReflection;
import io.quarkuscoffeeshop.domain.CommandType;
import io.quarkuscoffeeshop.domain.MenuItem;
import io.quarkuscoffeeshop.domain.OrderSource;

import java.math.BigDecimal;
import java.util.*;

@RegisterForReflection
public class PlaceOrderCommandJson {

    private OrderSource orderSource;

    private final CommandType commandType = CommandType.PLACE_ORDER;

    HashMap<String, MenuItem> baristaItems = new HashMap<>();

    HashMap<String, MenuItem> kitchenItems = new HashMap<>();

    private BigDecimal total;

    public PlaceOrderCommandJson() {
    }

    public PlaceOrderCommandJson(OrderSource orderSource, HashMap<String, MenuItem> baristaItems, HashMap<String, MenuItem> kitchenItems, BigDecimal total) {
        this.orderSource = orderSource;
        this.baristaItems = baristaItems;
        this.kitchenItems = kitchenItems;
        this.total = total;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PlaceOrderCommandJson.class.getSimpleName() + "[", "]")
                .add("orderSource=" + orderSource)
                .add("commandType=" + commandType)
                .add("baristaItems=" + baristaItems)
                .add("kitchenItems=" + kitchenItems)
                .add("total=" + total)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaceOrderCommandJson that = (PlaceOrderCommandJson) o;
        return orderSource == that.orderSource &&
                commandType == that.commandType &&
                Objects.equals(baristaItems, that.baristaItems) &&
                Objects.equals(kitchenItems, that.kitchenItems) &&
                Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderSource, commandType, baristaItems, kitchenItems, total);
    }

    public OrderSource getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(OrderSource orderSource) {
        this.orderSource = orderSource;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public HashMap<String, MenuItem> getBaristaItems() {
        return baristaItems;
    }

    public void setBaristaItems(HashMap<String, MenuItem> baristaItems) {
        this.baristaItems = baristaItems;
    }

    public HashMap<String, MenuItem> getKitchenItems() {
        return kitchenItems;
    }

    public void setKitchenItems(HashMap<String, MenuItem> kitchenItems) {
        this.kitchenItems = kitchenItems;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}

