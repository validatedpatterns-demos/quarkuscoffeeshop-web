package io.quarkuscoffeeshop.web.domain.commands;

import io.quarkuscoffeeshop.domain.OrderLineItem;
import io.quarkuscoffeeshop.domain.PlaceOrderCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class WebOrderCommand {

    private final String id;

    private final String orderSource = "WEB";

    private final String location;

    private final String loyaltyMemberId;

    private final List<OrderLineItem> baristaLineItems;

    private final List<OrderLineItem> kitchenLineItems;

    public WebOrderCommand(final PlaceOrderCommand placeOrderCommand) {

        this.id = placeOrderCommand.getId();
        this.location = placeOrderCommand.getStoreId();

        if (placeOrderCommand.getBaristaItems().isPresent()) {
            this.baristaLineItems = placeOrderCommand.getBaristaItems().get();
        }else{
            this.baristaLineItems = new ArrayList<>(0);
        }

        if (placeOrderCommand.getKitchenItems().isPresent()) {
            this.kitchenLineItems = placeOrderCommand.getKitchenItems().get();
        }else {
            this.kitchenLineItems = new ArrayList<>(0);
        }

        if (placeOrderCommand.getRewardsId().isPresent()) {
            this.loyaltyMemberId = placeOrderCommand.getRewardsId().get();
        }else{
            this.loyaltyMemberId = null;
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", WebOrderCommand.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("orderSource='" + orderSource + "'")
                .add("location='" + location + "'")
                .add("loyaltyMemberId='" + loyaltyMemberId + "'")
                .add("baristaLineItems=" + baristaLineItems)
                .add("kitchenLineItems=" + kitchenLineItems)
                .toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WebOrderCommand that = (WebOrderCommand) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (orderSource != null ? !orderSource.equals(that.orderSource) : that.orderSource != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (loyaltyMemberId != null ? !loyaltyMemberId.equals(that.loyaltyMemberId) : that.loyaltyMemberId != null)
            return false;
        if (baristaLineItems != null ? !baristaLineItems.equals(that.baristaLineItems) : that.baristaLineItems != null)
            return false;
        return kitchenLineItems != null ? kitchenLineItems.equals(that.kitchenLineItems) : that.kitchenLineItems == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orderSource != null ? orderSource.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (loyaltyMemberId != null ? loyaltyMemberId.hashCode() : 0);
        result = 31 * result + (baristaLineItems != null ? baristaLineItems.hashCode() : 0);
        result = 31 * result + (kitchenLineItems != null ? kitchenLineItems.hashCode() : 0);
        return result;
    }

    public String getId() {
        return id;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public String getLocation() {
        return location;
    }

    public String getLoyaltyMemberId() {
        return loyaltyMemberId;
    }

    public List<OrderLineItem> getBaristaLineItems() {
        return baristaLineItems;
    }

    public List<OrderLineItem> getKitchenLineItems() {
        return kitchenLineItems;
    }
}
