package com.poly.dao.impl;

import com.poly.dao.GenericDAO;
import com.poly.dao.InterfaceDAO;
import com.poly.model.Category;

import java.util.List;

public class CategoryDAO extends GenericDAO<Category> implements InterfaceDAO<Category> {

    /**
     * @param uniqueField là trường dữ liệu unique hoặc id
     */
    public CategoryDAO(String uniqueField) {
        super(uniqueField);
    }

    @Override
    public Category create(Category category) throws Exception {
        return super.insertAndReturn(category);
    }

    @Override
    public Category update(Category category) throws Exception {
        return super.update(category, category.getId());
    }

    @Override
    public Category get(Integer id) throws Exception {
        return super.findById(id);
    }

    @Override
    public boolean remove(Integer id) throws Exception {
        return super.delete(id);
    }

    @Override
    public List<Category> listAll() throws Exception {
        return super.findAll();
    }
}
