package kitchenpos;

import kitchenpos.menugroup.domain.MenuGroup;

public class MenuGroupFixture {

    public static final String MENU_GROUP_NAME1 = "나혼자세트";
    private static final String MENU_GROUP_NAME2 = "둘이서세트";

    public static MenuGroup createMenuGroup1() {
        return new MenuGroup(MENU_GROUP_NAME1);
    }

    public static MenuGroup createMenuGroup2() {
        return new MenuGroup(MENU_GROUP_NAME2);
    }
}
