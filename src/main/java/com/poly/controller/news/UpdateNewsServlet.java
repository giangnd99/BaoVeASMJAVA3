package com.poly.controller.news;

import com.poly.services.impl.NewsServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;

@WebServlet("/admin/update_news")
@MultipartConfig
public class UpdateNewsServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -6725822377987848189L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsServices service = new NewsServices(req, resp);
        try {
//            service.editNews();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsServices service = new NewsServices(req, resp);
        try {
//            service.updateNews();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
