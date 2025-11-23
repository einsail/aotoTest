package com.example.apitesting.client;

import com.example.apitesting.model.ApiUser;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserClient {

    private static final String USERS_PATH = "/api/users";

    public Response fetchUsers(RequestSpecification specification) {
        return specification.when().get(USERS_PATH);
    }

    public List<ApiUser> fetchUsersAsList(RequestSpecification specification) {
        return fetchUsers(specification).then().statusCode(200).extract().as(new TypeRef<>() {});
    }
}
