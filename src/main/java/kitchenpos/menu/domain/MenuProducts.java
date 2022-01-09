package kitchenpos.menu.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class MenuProducts {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id")
    private List<MenuProduct> menuProducts = new ArrayList<>();

    public MenuProducts() {
    }

    public MenuProducts(List<MenuProduct> menuProducts) {
        this.menuProducts = menuProducts;
    }

    public static MenuProducts of(BigDecimal price, List<MenuProduct> menuProducts) {
        validate(price, menuProducts);
        return new MenuProducts(menuProducts);
    }

    private static void validate(BigDecimal price, List<MenuProduct> menuProducts) {
        BigDecimal sum = BigDecimal.ZERO;
        for (final MenuProduct menuProduct : menuProducts) {
            final BigDecimal amount = menuProduct.getAmount();
            sum = sum.add(amount);
        }
        if (price.compareTo(sum) > 0) {
            throw new IllegalArgumentException("메뉴 가격이 메뉴 상품들 가격의 총합보다 클 수 없습니다.");
        }
    }

    public List<MenuProduct> getMenuProducts() {
        return menuProducts;
    }

    public List<Long> getProductIds() {
        return menuProducts.stream()
                .map(MenuProduct::getProductId)
                .collect(Collectors.toList());
    }
}
