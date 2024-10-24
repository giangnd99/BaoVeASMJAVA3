package com.poly.services.impl;

import com.poly.model.Users;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

public class SearchServices {
    private UserServices userServices;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public SearchServices(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.userServices = new UserServices(request, response);
    }

    public void searchByKeyWord() throws Exception {
        String keyword = request.getParameter("keyword");
        List<Users> books = getActuallyResult(keyword);
        request.setAttribute("userList", books);
        String homePage = "/views/book/manage_book.jsp";
        request.getRequestDispatcher(homePage).forward(request, response);
    }

    public List<Users> getActuallyResult(String keyword) throws Exception {
        List<Users> allUsers = userServices.getDao().findAll();
        List<Users> result = new ArrayList<>();

        if (keyword.isEmpty() || keyword.trim().equals("")) {
            return allUsers;
        }
        boolean checkAdmin = keyword.equalsIgnoreCase("admin");
        for (Users user : allUsers) {
            if (checkAdmin) {
                if (user.isAdmin()) {
                    result.add(user);
                }
            } else {
                if (!user.isAdmin()) {
                    result.add(user);
                }
            }
        }
        return result;
    }
}
