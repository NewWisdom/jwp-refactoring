package kitchenpos;

import kitchenpos.table.domain.OrderTable;
import kitchenpos.table.domain.TableGroup;

public class TableFixture {

    private static final int NUMBER_OF_GUEST = 10;

    public static OrderTable createOrderTable() {
        return createOrderTable(false);
    }

    public static OrderTable createOrderTable(TableGroup tableGroup) {
        return new OrderTable(tableGroup, NUMBER_OF_GUEST, false);
    }

    public static OrderTable createOrderTable(boolean empty) {
        return new OrderTable(NUMBER_OF_GUEST, empty);
    }

    public static OrderTable createOrderTable(TableGroup tableGroup, boolean empty) {
        return new OrderTable(tableGroup, NUMBER_OF_GUEST, empty);
    }
}
