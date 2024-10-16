package com.poly.services;

import com.poly.constant.MessageType;
import com.poly.dao.GenericDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;

/**
 * GenericService để cung cấp các chức năng cơ bản: Thêm, sửa, xóa, tìm kiếm, forward trang, redirect trang.
 *
 * @param <T> Đối tượng tương ứng với model trong cơ sở dữ liệu.
 */
public class GenericService<T> {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    @Getter
    protected GenericDAO<T> dao;

    public GenericService(HttpServletRequest request, HttpServletResponse response, GenericDAO<T> dao) {
        this.request = request;
        this.response = response;
        this.dao = dao;
    }

    /**
     * Load danh sách các đối tượng
     *
     * @param list Đối tượng danh sách cần load
     * @param uri  Đường dẫn URI cần forward
     * @throws ServletException, IOException Lỗi khi forward trang
     */
    public void loadList(List<T> list, String listName, String uri) throws ServletException, IOException {
        request.setAttribute(listName, list);
        forward(uri);
    }

    /**
     * Load một đối tượng theo id
     *
     * @param id  Id của đối tượng cần load
     * @param uri Đường dẫn URI cần forward
     * @throws Exception Lỗi khi forward trang
     */
    public void loadEntity(int id, String uri) throws Exception {
        T entity = dao.findById(id);
        setObject(entity);
        forward(uri);
    }

    /**
     * Thêm đối tượng mới
     *
     * @param entity Đối tượng cần thêm
     * @throws Exception
     */
    public boolean addEntity(T entity) throws Exception {
        boolean result = false;
        readFields(entity);
        entity = dao.insertAndReturn(entity);
        if (entity != null) {
            result = true;
        }
        return result;
    }

    /**
     * Sửa một đối tượng
     *
     * @param entity Đối tượng cần sửa
     * @param id     Id của đối tượng
     * @throws Exception
     */
    public boolean editEntity(T entity, int id) throws Exception {
        readFields(entity);
        entity = dao.update(entity, id);
        if (entity != null) {
            return true;
        }
        return false;
    }

    /**
     * Xóa một đối tượng
     *
     * @param id Id của đối tượng cần xóa
     * @throws SQLException
     */
    public boolean deleteEntity(int id) throws Exception {
        return dao.delete(id);
    }

    /**
     * Tìm kiếm đối tượng theo tên
     *
     * @param keyword Từ khoá cần tìm
     * @param uri     Đường dẫn URI cần forward
     * @throws ServletException, IOException
     */
    public void searchEntities(String keyword, String uri) throws Exception {
        List<T> list = dao.searchByKeyword(keyword);
        request.setAttribute("listResult", list);
        request.setAttribute("keyword", keyword);
        forward(uri);
    }

    /**
     * Forward đến trang có URI cấp
     *
     * @param uri Đường dẫn URI cần forward
     * @throws ServletException, IOException
     */
    public void forward(String uri) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(uri);
        dispatcher.forward(request, response);
    }

    /**
     * Redirect về trang chủ
     *
     * @throws IOException
     */
    public void redirectToHome() throws IOException {
        response.sendRedirect(request.getContextPath() + "/");
    }

    /**
     * Đọc và gán giá trị các trường từ request vào đối tượng
     *
     * @param entity Đối tượng cần gán giá trị
     * @throws ServletException, IOException
     */
    public void readFields(T entity) throws ServletException, IOException {
        Field[] fields = entity.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                String parameterValue = request.getParameter(field.getName());
                if (parameterValue != null && !parameterValue.isEmpty()) {
                    Object value = convertToFieldType(parameterValue, field.getType());
                    field.set(entity, value);
                }
            }
        } catch (IllegalAccessException e) {
            throw new ServletException("Lỗi khi đọc giá trị từ request", e);
        }
    }

    /**
     * Chuyển đổi giá trị từ String sang kiểu dữ liệu của trường
     *
     * @param value     Giá trị cần chuyển đổi
     * @param fieldType Kiểu dữ liệu của trường
     * @return Giá trị đã chuyển đổi
     */
    private Object convertToFieldType(String value, Class<?> fieldType) {
        if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
            return Integer.parseInt(value);
        } else if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
            return Long.parseLong(value);
        } else if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
            return Boolean.parseBoolean(value);
        } else if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
            return Double.parseDouble(value);
        } else if (fieldType.equals(java.util.Date.class)) {
            return java.sql.Date.valueOf(value);
        } else {
            return value;
        }
    }

    /**
     * Chuyển đổi giá trị từ Object sang kiểu dữ liệu của trường
     * Ánh xạ value với name value
     *
     * @param value Giá trị cần chuyển đổi
     */
    public void setObject(Object value) {
        String nameValue = value.getClass().getSimpleName();
        request.setAttribute(nameValue, value);
    }

    /**
     * Chuyển đổi giá trị từ Object sang kiểu dữ liệu của trường
     * Ánh xạ value với name value
     *
     * @param messageType Kiểu thông báo cần chuyển đổi
     *                    SUCCESS("successMessage"),
     *                    ERROR("errorMessage"),
     *                    WARNING("warningMessage"),
     *                    INFO("infoMessage")
     * @param message     Nội dung
     */
    public void setMessage(MessageType messageType, String message) {
        request.setAttribute(messageType.getValue(), message);
    }

}