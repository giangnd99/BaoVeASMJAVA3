package com.poly.services.impl;

import com.poly.dao.impl.CategoryDAO;
import com.poly.model.Category;
import com.poly.services.GenericService;
import com.poly.services.InterfaceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CategoryServices extends GenericService<Category> implements InterfaceService {

    public CategoryServices(HttpServletRequest request, HttpServletResponse response) {
        super(request, response, new CategoryDAO("name"));
    }

    @Override
    public void loadListToPage() throws Exception {

    }

    @Override
    public void addEntityFormPage() throws Exception {

    }

    @Override
    public void editEntityFormPage() throws Exception {

    }

    @Override
    public void deleteEntityFromPage() throws Exception {

    }

    @Override
    public void loadEditFormPage() throws Exception {

    }

    @Override
    public void loadCreateFormPage() throws Exception {

    }
}
