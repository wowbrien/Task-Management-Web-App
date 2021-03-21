package com.deloitte.todolist.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class UserAccountServiceTest {

    @Autowired
    private UserAccountService userAccountService;

    @Test
    public void validateThrowsExceptionWithInvalidUsername() {
        try {
            userAccountService.loadUserByUsername("drop * from UserAccount;");
        } catch (UsernameNotFoundException e) {
            return;
        }
        fail();
    }
}
