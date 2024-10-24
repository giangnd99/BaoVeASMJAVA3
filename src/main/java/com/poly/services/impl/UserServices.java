package com.poly.services.impl;

import com.poly.constant.MessageType;
import com.poly.dao.impl.UsersDAO;
import com.poly.model.Users;
import com.poly.services.GenericService;
import com.poly.services.InterfaceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class UserServices extends GenericService<Users> implements InterfaceService {

    private String message = "";
    private MessageType messageType = MessageType.INFO;
    private boolean result = false;
    String managePage = "/views/book/manage_book.jsp";


    public UserServices(HttpServletRequest request, HttpServletResponse response) {
        super(request, response, new UsersDAO("id"));
    }

    @Override
    public void loadListToPage() throws Exception {
        List<Users> bookList = getDao().findAll();
        super.loadList(bookList, "userList");
        message = message == null ? "" : message;
        super.setMessage(messageType, message);
        super.forward(managePage);
    }

    @Override
    public void addEntityFormPage() throws Exception {
        Users book = new Users();
        result = super.addEntity(book);
        if (result) {
            messageType = MessageType.SUCCESS;
            message = "Thêm user thành công";
            loadEntity(book.getId());
        } else {
            messageType = MessageType.ERROR;
            message = "Không thể thêm ";
        }
        loadListToPage();
    }

    @Override
    public void editEntityFormPage() throws Exception {
        Users book = new Users();
        result = super.editEntity(book);
        if (result) {
            messageType = MessageType.SUCCESS;
            message = "Cập nhật thành công";
            loadEntity(book.getId());
        } else {
            messageType = MessageType.ERROR;
            message = "Không thể cập nhật";
        }
        loadListToPage();
    }

    @Override
    public void deleteEntityFromPage() throws Exception {
        String id = request.getParameter("id");
        result = super.deleteEntity(id);
        if (result) {
            messageType = MessageType.SUCCESS;
            message = "Xóa thành công";
        } else {
            messageType = MessageType.ERROR;
            message = "Không xóa được ";
        }
        loadListToPage();
    }

    @Override
    public void loadEditFormPage() throws Exception {
        String id = request.getParameter("id") == null ? "" : request.getParameter("id");
        loadEntity(id);
        loadListToPage();
    }


    @Override
    public void loadCreateFormPage() throws Exception {
        forward(managePage);
    }

}
