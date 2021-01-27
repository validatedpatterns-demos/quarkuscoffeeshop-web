package io.quarkuscoffeeshop.domain.valueobjects;

import java.util.StringJoiner;

public class LoyaltyUpdate {

    final String email;

    final String reward;

    public LoyaltyUpdate(final String email, final String reward) {
        this.email = email;
        this.reward = reward;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LoyaltyUpdate.class.getSimpleName() + "[", "]")
                .add("email='" + email + "'")
                .add("reward='" + reward + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoyaltyUpdate that = (LoyaltyUpdate) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return reward != null ? reward.equals(that.reward) : that.reward == null;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (reward != null ? reward.hashCode() : 0);
        return result;
    }

    public String getEmail() {
        return email;
    }

    public String getReward() {
        return reward;
    }
}
