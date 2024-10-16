package com.poly.dao.impl;

import com.poly.model.Category;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDAOTest {

    @Test
    void listAll() {
        CategoryDAO dao = new CategoryDAO("name");
        try {
            List<Category> list = dao.listAll();
            assertNotNull(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}