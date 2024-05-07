package pageObject.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.Data;
import static io.restassured.RestAssured.given;
import static pageObject.user.UserConstants.*;

@Data
public class UserSteps {

    private final RequestSpecification requestSpecification;
    private final ResponseSpecification responseSpecification;

    @Step("Create user")
    public ValidatableResponse create(User user) {
        return given()
                .spec(requestSpecification)
                .body(user)
                .post(CREATE_USER_ENDPOINT)
                .then()
                .spec(responseSpecification);
    }

    @Step("Removed user")
    public ValidatableResponse removed(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .spec(requestSpecification)
                .delete(AUTH_USER_ENDPOINT)
                .then()
                .spec(responseSpecification);
    }
}
