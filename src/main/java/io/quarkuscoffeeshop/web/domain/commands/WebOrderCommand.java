package io.quarkuscoffeeshop.web.domain.commands;

import io.quarkuscoffeeshop.domain.PlaceOrderCommand;
import io.quarkuscoffeeshop.web.domain.LineItem;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class WebOrderCommand {

    private final String id;

    private final String orderSource = "WEB";

    private String location = "ATLANTA";

    private String loyaltyMemberId;

    private List<LineItem> baristaLineItems;

    private List<LineItem> kitchenLineItems;

    private final Instant timestamp = Instant.now();

    public WebOrderCommand(final PlaceOrderCommand placeOrderCommand) {
        this.id = placeOrderCommand.getId();
        if (placeOrderCommand.getBaristaItems().isPresent()) {
            this.baristaLineItems = new ArrayList<>();
            placeOrderCommand.getBaristaItems().get().forEach(baristaLineItem -> {
                this.baristaLineItems.add(new LineItem(baristaLineItem.getItem(), baristaLineItem.getName(), this.id));
            });
        }
        if (placeOrderCommand.getKitchenItems().isPresent()) {
            this.kitchenLineItems = new ArrayList<>();
            placeOrderCommand.getKitchenItems().get().forEach(kitchenLineItem -> {
                this.kitchenLineItems.add(new LineItem(kitchenLineItem.getItem(), kitchenLineItem.getName(), this.id));
            });
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
                .add("timestamp=" + timestamp)
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
        if (kitchenLineItems != null ? !kitchenLineItems.equals(that.kitchenLineItems) : that.kitchenLineItems != null)
            return false;
        return timestamp != null ? timestamp.equals(that.timestamp) : that.timestamp == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orderSource != null ? orderSource.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (loyaltyMemberId != null ? loyaltyMemberId.hashCode() : 0);
        result = 31 * result + (baristaLineItems != null ? baristaLineItems.hashCode() : 0);
        result = 31 * result + (kitchenLineItems != null ? kitchenLineItems.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
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

    public List<LineItem> getBaristaLineItems() {
        return baristaLineItems;
    }

    public List<LineItem> getKitchenLineItems() {
        return kitchenLineItems;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
