package com.poly.controller.news;

import com.poly.services.impl.NewsServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@MultipartConfig
@WebServlet("/admin/create_news")
public class CreateNewsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsServices newsService = new NewsServices(req, resp);
//        try {
//            newsService.showNewsNewForm();
//        } catch (Exception ex) {
//            Logger.getLogger(CreateNewsServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsServices newsService = new NewsServices(req, resp);
//
//        try {
//            newsService.createNews();
//
//        } catch (Exception e) {
//            throw new RuntimeException("Error when create news" + e, e.getCause());
//        }
    }
}
