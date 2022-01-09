package kitchenpos.menu.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private MenuPrice price;

    @Column(nullable = false)
    private Long menuGroupId;

    @Embedded
    private MenuProducts menuProducts;

    protected Menu() {
    }

    public Menu(String name, BigDecimal price, Long menuGroupId) {
        this(name, price, menuGroupId, new ArrayList<>());
    }

    public Menu(String name, BigDecimal price, Long menuGroupId, List<MenuProduct> menuProducts) {
        this(name, new MenuPrice(price), menuGroupId, new MenuProducts(menuProducts));
    }

    public Menu(String name, BigDecimal price, Long menuGroupId, List<MenuProduct> menuProducts, MenuValidator validator) {
        validator.validateMenuGroup(menuGroupId);
        validator.validateMenuProducts(menuProducts);
        this.name = name;
        this.price = new MenuPrice(price);
        this.menuGroupId = menuGroupId;
        this.menuProducts = new MenuProducts(menuProducts);
    }

    public Menu(String name, MenuPrice price, Long menuGroupId, MenuProducts menuProducts) {
        this.name = name;
        this.price = price;
        this.menuGroupId = menuGroupId;
        this.menuProducts = menuProducts;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price.getPrice();
    }

    public Long getMenuGroupId() {
        return menuGroupId;
    }

    public Long getMenuGroup() {
        return menuGroupId;
    }

    public List<MenuProduct> getMenuProducts() {
        return menuProducts.getMenuProducts();
    }

    public void validatePrice() {
        final List<MenuProduct> menuProducts = this.menuProducts.getMenuProducts();
        BigDecimal sum = BigDecimal.ZERO;
        for (final MenuProduct menuProduct : menuProducts) {
            final BigDecimal amount = menuProduct.getAmount();
            sum = sum.add(amount);
        }
        if (price.compareTo(sum) > 0) {
            throw new IllegalArgumentException("메뉴 가격이 메뉴 상품들 가격의 총합보다 클 수 없습니다.");
        }
    }
}
