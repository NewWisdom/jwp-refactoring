package kitchenpos;

import kitchenpos.domain.order.OrderTable;
import kitchenpos.domain.menu.Menu;
import kitchenpos.domain.order.Order;
import kitchenpos.domain.order.OrderLineItem;
import kitchenpos.domain.order.OrderStatus;

import java.util.List;

import static kitchenpos.domain.order.OrderStatus.MEAL;

public class OrderFixture {

    private static final OrderStatus ORDER_STATUS = MEAL;
    private static final long ORDER_LINE_QUANTITY = 1;

    public static Order createOrder(OrderTable orderTable, List<OrderLineItem> orderLineItems) {
        return createOrder(orderTable, ORDER_STATUS, orderLineItems);
    }

    public static Order createOrder(OrderTable orderTable, OrderStatus orderStatus, List<OrderLineItem> orderLineItems) {
        Order order = new Order(orderTable, orderStatus);
        order.changeOrderLineItems(orderLineItems);
        return order;
    }

    public static OrderLineItem createOrderLineItem(Menu menu) {
        return new OrderLineItem(menu, ORDER_LINE_QUANTITY);
    }

    public static OrderLineItem createOrderLineItem(Order order, Menu menu) {
        return new OrderLineItem(order, menu, ORDER_LINE_QUANTITY);
    }
}
