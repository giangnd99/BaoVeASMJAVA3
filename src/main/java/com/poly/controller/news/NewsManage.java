package com.poly.controller.news;
import com.poly.services.impl.NewsServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/list_news")
public class NewsManage extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            NewsServices service = new NewsServices(request, response);
//            service.listNews();
        } catch (Exception ex) {
            Logger.getLogger(NewsManage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

