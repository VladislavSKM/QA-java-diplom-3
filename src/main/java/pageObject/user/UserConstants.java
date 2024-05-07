package pageObject.user;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class UserConstants {
    public static final String CREATE_USER_ENDPOINT = "/api/auth/register";
    public static final String AUTH_USER_ENDPOINT = "/api/auth/user";
    public static final RequestSpecification REQUEST_SPECIFICATION =
            new RequestSpecBuilder()
                    .log(LogDetail.ALL)
                    .addHeader("Content-Type", "application/json")
                    .setBaseUri("https://stellarburgers.nomoreparties.site")
                    .build();
    public static final ResponseSpecification RESPONSE_SPECIFICATION =
            new ResponseSpecBuilder()
                    .log(LogDetail.ALL)
                    .build();
}
