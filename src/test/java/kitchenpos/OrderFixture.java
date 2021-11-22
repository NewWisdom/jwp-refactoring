package kitchenpos;

import kitchenpos.menu.domain.Menu;
import kitchenpos.order.domain.Order;
import kitchenpos.order.domain.OrderLineItem;
import kitchenpos.order.domain.OrderStatus;
import kitchenpos.table.domain.OrderTable;

import java.util.List;

import static kitchenpos.order.domain.OrderStatus.MEAL;

public class OrderFixture {

    private static final OrderStatus ORDER_STATUS = MEAL;
    private static final long ORDER_LINE_QUANTITY = 1;

    public static Order createOrder(OrderTable orderTable, List<OrderLineItem> orderLineItems) {
        return createOrder(orderTable, ORDER_STATUS, orderLineItems);
    }

    public static Order createOrder(OrderTable orderTable, OrderStatus orderStatus, List<OrderLineItem> orderLineItems) {
        Order order = new Order(orderTable.getId(), orderStatus);
        order.changeOrderLineItems(orderLineItems);
        return order;
    }

    public static OrderLineItem createOrderLineItem(Menu menu) {
        return new OrderLineItem(menu, ORDER_LINE_QUANTITY);
    }
}
