package kitchenpos.table.application.dto;

import javax.validation.constraints.Positive;

public class OrderTableGuestRequest {

    @Positive
    private int numberOfGuests;

    public OrderTableGuestRequest() {
    }

    public OrderTableGuestRequest(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }
}
