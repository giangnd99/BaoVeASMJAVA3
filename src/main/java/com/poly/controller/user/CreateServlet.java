package com.poly.controller.user;

import com.poly.services.impl.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@MultipartConfig
@WebServlet("/create_user")
public class CreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServices bookServices = new UserServices(req, resp);
        try {
            bookServices.addEntityFormPage();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServices bookServices = new UserServices(req, resp);
        try {
            bookServices.addEntityFormPage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
