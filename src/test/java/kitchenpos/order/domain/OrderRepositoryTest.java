package kitchenpos.order.domain;

import kitchenpos.menu.domain.Menu;
import kitchenpos.menugroup.domain.MenuGroup;
import kitchenpos.product.domain.Product;
import kitchenpos.table.domain.OrderTable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.Collections;

import static kitchenpos.MenuFixture.createMenu1;
import static kitchenpos.MenuGroupFixture.createMenuGroup1;
import static kitchenpos.OrderFixture.createOrder;
import static kitchenpos.OrderFixture.createOrderLineItem;
import static kitchenpos.ProductFixture.createProduct1;
import static kitchenpos.TableFixture.createOrderTable;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("주어진 주문 테이블과 주문 상태들을 가진 주문이 있는지 확인한다.")
    @Test
    void existsByOrderTableAndOrderStatusIn() {
        final MenuGroup menuGroup1 = createMenuGroup1();
        em.persist(menuGroup1);
        final Product product1 = createProduct1();
        em.persist(product1);
        final Menu menu = createMenu1(menuGroup1, Collections.singletonList(product1));
        em.persist(menu);
        final OrderTable orderTable1 = createOrderTable();
        em.persist(orderTable1);
        final OrderTable orderTable2 = createOrderTable();
        em.persist(orderTable2);
        final OrderLineItem orderLineItem = createOrderLineItem(menu);
        final Order order = createOrder(orderTable1, Collections.singletonList(orderLineItem));
        em.persist(order);
        em.flush();

        final boolean isExist = orderRepository.existsByOrderTableIdAndOrderStatusIn(orderTable1.getId(), Collections.singletonList(order.getOrderStatus()));
        final boolean isNotExist = orderRepository.existsByOrderTableIdAndOrderStatusIn(orderTable2.getId(), Collections.singletonList(OrderStatus.COOKING));

        assertThat(isExist).isTrue();
        assertThat(isNotExist).isFalse();
    }

    @DisplayName("주어진 주문 테이블들과 주문 상태들을 가진 주문이 있는지 확인한다.")
    @Test
    void existsByOrderTableInAndOrderStatusIn() {
        final MenuGroup menuGroup1 = createMenuGroup1();
        em.persist(menuGroup1);
        final Product product1 = createProduct1();
        em.persist(product1);
        final Menu menu = createMenu1(menuGroup1, Collections.singletonList(product1));
        em.persist(menu);
        final OrderTable orderTable1 = createOrderTable();
        em.persist(orderTable1);
        final OrderTable orderTable2 = createOrderTable();
        em.persist(orderTable2);
        final OrderLineItem orderLineItem = createOrderLineItem(menu);
        final Order order = createOrder(orderTable1, Collections.singletonList(orderLineItem));
        em.persist(order);
        em.flush();

        final boolean isExist = orderRepository.existsByOrderTableIdInAndOrderStatusIn(
                Arrays.asList(orderTable1.getId(), orderTable2.getId()), Collections.singletonList(order.getOrderStatus()));

        assertThat(isExist).isTrue();
    }
}