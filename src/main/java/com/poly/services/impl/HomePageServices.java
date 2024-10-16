package com.poly.services.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomePageServices {
    private HttpServletResponse response;
    private HttpServletRequest request;

    public HomePageServices(HttpServletResponse response, HttpServletRequest request) {
        this.response = response;
        this.request = request;
    }

    public void showHomePage() throws Exception {
        String homePage = "/index.jsp";
        request.getRequestDispatcher(homePage).forward(request, response);
    }
}
