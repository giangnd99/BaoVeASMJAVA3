package com.poly.dao.impl;

import com.poly.dao.GenericDAO;
import com.poly.dao.InterfaceDAO;
import com.poly.model.Users;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class UsersDAO extends GenericDAO<Users> implements InterfaceDAO<Users> {

    /**
     * @param uniqueField là trường dữ liệu unique hoặc id
     */
    public UsersDAO(String uniqueField) {
        super(uniqueField);
    }

    @Override
    public Users create(Users news) throws Exception {
        return super.insertAndReturn(news);
    }

    @Override
    public Users update(Users news) throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        return super.update(news);
    }

    @Override
    public Users get(String uniqueField) throws Exception {
        return super.findById(uniqueField);
    }

    @Override
    public boolean remove(String uniqueField) throws Exception {
        return super.delete(uniqueField);
    }

    @Override
    public List<Users> listAll() throws Exception {
        return super.findAll();
    }
}
