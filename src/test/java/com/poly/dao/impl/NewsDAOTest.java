package com.poly.dao.impl;

import com.poly.model.Users;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class NewsDAOTest {

    @Test
    void listAll() {
        UsersDAO dao = new UsersDAO("id");
        try {
            List<Users> list = dao.listAll();
            assertNotNull(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}