package com.example.apitesting.test;

import com.example.apitesting.client.UserClient;
import com.example.apitesting.model.ApiUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class UserApiTest extends BaseApiTest {

    @Autowired
    private UserClient userClient;

    @Test(description = "Demo users are returned with names and emails")
    public void shouldReturnDemoUsers() {
        List<ApiUser> users = userClient.fetchUsersAsList(requestSpec);
        assert !users.isEmpty() : "Expected demo users";
        assert users.stream().allMatch(user -> user.getName() != null && user.getEmail() != null);
    }
}
