package kitchenpos.menu.application;

import kitchenpos.menu.application.dto.MenuRequest;
import kitchenpos.menu.application.dto.MenuResponse;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.menu.domain.MenuRepository;
import kitchenpos.menu.domain.MenuValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final MenuValidator menuValidator;

    public MenuService(
            MenuRepository menuRepository,
            MenuValidator menuValidator
    ) {
        this.menuRepository = menuRepository;
        this.menuValidator = menuValidator;
    }

    @Transactional
    public MenuResponse create(final MenuRequest request) {
        final List<MenuProduct> menuProducts = generateMenuProducts(request);
        final Menu menu = new Menu(request.getName(), request.getPrice(), request.getMenuGroupId(), menuProducts, menuValidator);
        menu.validatePrice();
        final Menu savedMenu = menuRepository.save(menu);
        return MenuResponse.of(savedMenu);
    }

    private List<MenuProduct> generateMenuProducts(MenuRequest request) {
        return request.getMenuProducts()
                .stream()
                .map(r -> new MenuProduct(
                        r.getProductId(),
                        r.getQuantity()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MenuResponse> list() {
        final List<Menu> menus = menuRepository.findAll();
        return MenuResponse.toList(menus);
    }
}
