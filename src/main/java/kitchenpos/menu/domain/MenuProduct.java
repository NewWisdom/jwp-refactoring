package kitchenpos.menu.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class MenuProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private long quantity;

    public MenuProduct() {
    }

    public MenuProduct(Long productId, long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getSeq() {
        return seq;
    }

    public void changeMenu(Menu menu) {
//        this.menu = menu;
    }

    public long getQuantity() {
        return quantity;
    }

    public BigDecimal getAmount() {
//        return product.getAmount(quantity);
        return null;
    }
}
