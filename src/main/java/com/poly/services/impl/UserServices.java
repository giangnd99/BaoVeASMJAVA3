package com.poly.services.impl;

import com.poly.constant.MessageType;
import com.poly.dao.impl.UserDao;
import com.poly.model.Users;
import com.poly.services.GenericService;
import com.poly.services.InterfaceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class UserServices extends GenericService<Users> implements InterfaceService {

    private String message;
    private boolean result = false;
    private MessageType messageType = MessageType.INFO;

    public UserServices(HttpServletRequest request, HttpServletResponse response) {
        super(request, response, new UserDao("email"));
    }

    @Override
    public void loadListToPage() throws Exception {
        loadListToPage(messageType, null);
    }

    public void loadListToPage(MessageType messageType, String message) throws Exception {
        String userPage = "/views/user/list_users.jsp";
        List<Users> users = getDao().findAll();
        message = (message != null) ? message : "";
        setMessage(messageType, message);
        super.loadList(users, "users", userPage);
    }

    @Override
    public void loadEditFormPage() throws Exception {
        String editFormPage = "/views/user/update_user.jsp";
        int id = request.getParameter("id") == null ? -1 : Integer.parseInt(request.getParameter("id"));
        loadEntity(id, editFormPage);
    }

    @Override
    public void loadCreateFormPage() throws Exception {
        String createFormPage = "/views/user/create_user.jsp";
        forward(createFormPage);
    }

    @Override
    public void addEntityFormPage() throws Exception {
        Users newsUser = new Users();
        result = super.addEntity(newsUser);
        if (result) {
            messageType = MessageType.SUCCESS;
            message = "User được thêm thành công";
        } else {
            messageType = MessageType.ERROR;
            message = "Không thêm được user";
        }
        loadListToPage(messageType, message);
    }

    @Override
    public void editEntityFormPage() throws Exception {

        int id = Integer.parseInt(request.getParameter("userId"));
        Users editUser = new Users();
        result = super.editEntity(editUser, id);
        if (result) {
            message = "User được chỉnh sửa thành công";
            messageType = MessageType.SUCCESS;
        } else {
            message = "Không sửa được user";
            messageType = MessageType.ERROR;
        }
        loadListToPage(messageType, message);
    }

    @Override
    public void deleteEntityFromPage() throws Exception {

        int id = Integer.parseInt(request.getParameter("id"));
        result = super.deleteEntity(id);
        if (result) {
            messageType = MessageType.SUCCESS;
            message = "User đã được xóa";
        } else {
            messageType = MessageType.ERROR;
            message = "Không xóa được user";
        }
        loadListToPage(messageType, message);
    }
}

