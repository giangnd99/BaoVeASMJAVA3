package com.poly.dao.impl;

import com.poly.dao.GenericDAO;
import com.poly.dao.InterfaceDAO;
import com.poly.model.News;

import java.util.List;

public class NewsDAO extends GenericDAO<News> implements InterfaceDAO<News> {

    /**
     * @param uniqueField là trường dữ liệu unique hoặc id
     */
    public NewsDAO(String uniqueField) {
        super(uniqueField);
    }

    @Override
    public News create(News news) throws Exception {
        return super.insertAndReturn(news);
    }

    @Override
    public News update(News news) throws Exception {
        return super.update(news, news.getId());
    }

    @Override
    public News get(Integer id) throws Exception {
        return super.findById(id);
    }

    @Override
    public boolean remove(Integer id) throws Exception {
        return super.delete(id);
    }

    @Override
    public List<News> listAll() throws Exception {
        return super.findAll();
    }
}
