package com.poly.controller;

import com.poly.services.impl.SearchServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SearchServices services = new SearchServices(req, resp);
        try {
            services.searchByKeyWord();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
