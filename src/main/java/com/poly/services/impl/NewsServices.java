package com.poly.services.impl;

import com.poly.dao.impl.NewsDAO;
import com.poly.model.News;
import com.poly.services.GenericService;
import com.poly.services.InterfaceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NewsServices extends GenericService<News> implements InterfaceService {

    public NewsServices(HttpServletRequest request, HttpServletResponse response) {
        super(request, response, new NewsDAO("title"));
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
