package kitchenpos.ui;

import kitchenpos.MenuFixture;
import kitchenpos.application.MenuService;
import kitchenpos.domain.menu.Menu;
import kitchenpos.ui.dto.MenuResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static kitchenpos.MenuFixture.*;
import static kitchenpos.ProductFixture.createProduct1;
import static kitchenpos.ProductFixture.createProduct2;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MenuRestController.class)
class MenuRestControllerTest extends ControllerTest {

    @MockBean
    private MenuService menuService;

    @DisplayName("메뉴를 생성할 수 있다.")
    @Test
    void create() throws Exception {
        long menuId = 1L;
        Menu menu = createMenu1(createMenuGroup1(), Collections.singletonList(createProduct1()));
        MenuResponse response = MenuResponse.of(menu);
        when(menuService.create(any())).thenReturn(response);

        mockMvc.perform(post("/api/menus")
                .content(objectMapper.writeValueAsString(menu))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/menus/" + menuId))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @DisplayName("메뉴 목록을 조회할 수 있다.")
    @Test
    void list() throws Exception {
        List<MenuResponse> response = Arrays.asList(
                MenuResponse.of(createMenu1(createMenuGroup1(), Collections.singletonList(createProduct1()))),
                MenuResponse.of(createMenu2(createMenuGroup2(), Collections.singletonList(createProduct2()))));
        when(menuService.list()).thenReturn(response);

        mockMvc.perform(get("/api/responses"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
}
