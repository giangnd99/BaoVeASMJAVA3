package com.poly.controller.user;

import com.poly.services.impl.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;

@WebServlet("/update_user")
@MultipartConfig
public class UpdateServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -6725822377987848189L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServices service = new UserServices(req, resp);
        try {
            service.loadEditFormPage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServices service = new UserServices(req, resp);
        try {
            service.editEntityFormPage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
