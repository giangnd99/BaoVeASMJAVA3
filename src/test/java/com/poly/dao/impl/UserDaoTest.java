package com.poly.dao.impl;

import com.poly.model.Users;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    @Test
    void listAll() {
        UserDao dao = new UserDao("email");
        try {
            List<Users> userList = dao.listAll();
            assertNotNull(userList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}