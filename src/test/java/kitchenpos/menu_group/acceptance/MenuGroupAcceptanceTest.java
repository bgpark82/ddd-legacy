package kitchenpos.menu_group.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.AcceptanceTest;
import kitchenpos.domain.MenuGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static kitchenpos.menu_group.step.MenuGroupStep.createMenuGroup;
import static kitchenpos.menu_group.step.MenuGroupStep.requestCreateMenuGroup;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("MenuGroup 인수 테스트")
public class MenuGroupAcceptanceTest extends AcceptanceTest {

    @DisplayName("메뉴 그룹을 등록한다")
    @Test
    public void create() {
        // given
        MenuGroup menuGroup = createMenuGroup("두마리메뉴");

        // when
        ExtractableResponse<Response> response = requestCreateMenuGroup(menuGroup);

        // then
        assertCreateMenuGroup(response);
    }

    private void assertCreateMenuGroup(final ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.as(MenuGroup.class).getId()).isNotNull();
        assertThat(response.as(MenuGroup.class).getName()).isEqualTo("두마리메뉴");
    }
}
