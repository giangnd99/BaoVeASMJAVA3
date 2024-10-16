package com.poly.dao.impl;

import com.poly.model.News;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class NewsDAOTest {

    @Test
    void listAll() {
        NewsDAO dao = new NewsDAO("title");
        try {
            List<News> list = dao.listAll();
            assertNotNull(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}