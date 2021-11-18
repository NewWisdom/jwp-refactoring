package kitchenpos.application;

import kitchenpos.domain.menugroup.MenuGroup;
import kitchenpos.ui.dto.menugroup.MenuGroupRequest;
import kitchenpos.ui.dto.menugroup.MenuGroupResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static kitchenpos.MenuFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MenuGroupServiceTest extends EntityManagerSupport {

    @Autowired
    private MenuGroupService menuGroupService;

    @DisplayName("메뉴 그룹을 생성할 수 있다.")
    @Test
    void create() {
        final MenuGroupRequest request = new MenuGroupRequest(MENU_GROUP_NAME1);

        final MenuGroupResponse actual = menuGroupService.create(request);

        assertThat(actual.getName()).isEqualTo(MENU_GROUP_NAME1);
    }

    @DisplayName("메뉴 그룹 목록을 조회할 수 있다.")
    @Test
    void list() {
        final MenuGroup menuGroup1 = save(createMenuGroup1());
        final MenuGroup menuGroup2 = save(createMenuGroup2());

        final List<MenuGroupResponse> actual = menuGroupService.list();

        assertAll(
                () -> assertThat(actual).hasSize(2),
                () -> assertThat(actual.get(0).getId()).isEqualTo(menuGroup1.getId()),
                () -> assertThat(actual.get(0).getName()).isEqualTo(menuGroup1.getName()),
                () -> assertThat(actual.get(1).getId()).isEqualTo(menuGroup2.getId()),
                () -> assertThat(actual.get(1).getName()).isEqualTo(menuGroup2.getName())
        );
    }
}
