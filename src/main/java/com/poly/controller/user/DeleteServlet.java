package com.poly.controller.user;

import com.poly.services.impl.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet({"/delete_user"})
public class DeleteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

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

        UserServices bookServices = new UserServices(req, resp);
        try {
            bookServices.deleteEntityFromPage();
        } catch (Exception ex) {
            Logger.getLogger(DeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
