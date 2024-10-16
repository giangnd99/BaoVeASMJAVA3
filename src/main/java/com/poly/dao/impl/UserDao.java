package com.poly.dao.impl;

import com.poly.dao.GenericDAO;
import com.poly.dao.InterfaceDAO;
import com.poly.model.Users;

import java.util.List;

public class UserDao extends GenericDAO<Users> implements InterfaceDAO<Users> {

    /**
     * @param uniqueField là trường dữ liệu unique hoặc id
    */
    public UserDao(String uniqueField){
        super(uniqueField);
    }

    @Override
    public Users create(Users user) throws Exception {
        return super.insertAndReturn(user);
    }

    @Override
    public Users update(Users user) throws Exception {
        return super.update(user, user.getId());
    }

    @Override
    public Users get(Integer id) throws Exception {
        return super.findById(id);
    }

    @Override
    public boolean remove(Integer id) throws Exception {
        return super.delete(id);
    }

    @Override
    public List<Users> listAll() throws Exception {
        return super.findAll();
    }
}
