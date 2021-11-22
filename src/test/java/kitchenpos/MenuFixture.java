package kitchenpos;

import kitchenpos.menu.domain.Menu;
import kitchenpos.menugroup.domain.MenuGroup;
import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.product.domain.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class MenuFixture {

    public static final String MENU_NAME1 = "육회초밥단품";
    private static final String MENU_NAME2 = "육회초밥+연어초밥";
    public static final BigDecimal MENU_PRICE = BigDecimal.valueOf(15900);
    public static final Long MENU_GROUP_ID = 1L;
    public static final Long PRODUCT_ID = 1L;
    public static final long MENU_QUANTITY = 1;

    public static Menu createMenu1(MenuGroup menuGroup, List<Product> products) {
        Menu menu = new Menu(MENU_NAME1, MENU_PRICE, menuGroup);
        List<MenuProduct> menuProducts = products.stream()
                .map(it -> new MenuProduct(it, MENU_QUANTITY))
                .collect(Collectors.toList());
        menu.changeMenuProducts(menuProducts);
        return menu;
    }

    public static Menu createMenu2(MenuGroup menuGroup, List<Product> products) {
        Menu menu = new Menu(MENU_NAME2, MENU_PRICE, menuGroup);
        List<MenuProduct> menuProducts = products.stream()
                .map(it -> new MenuProduct(it, MENU_QUANTITY))
                .collect(Collectors.toList());
        menu.changeMenuProducts(menuProducts);
        return menu;
    }

    public static MenuProduct createMenuProduct(Product product) {
        return new MenuProduct(product, MENU_QUANTITY);
    }
}
