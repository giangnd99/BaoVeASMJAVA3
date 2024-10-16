package com.poly.controller.users;

import com.poly.services.impl.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/list_users")
public class UserManage extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServices service = new UserServices(request, response);
        try {
            service.loadListToPage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

