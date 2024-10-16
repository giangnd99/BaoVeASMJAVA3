package com.poly.controller;

import com.poly.services.impl.HomePageServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/")
public class HomePageServlet extends HttpServlet {

    private static final long serialVersionUID = -756398617714845129L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HomePageServices services = new HomePageServices(resp, req);
        try {
            services.showHomePage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
