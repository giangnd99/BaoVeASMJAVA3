package com.poly.dao;

import com.poly.utils.XJdbc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * GenericDAO để cung cấp các phương thức cơ bản: Thêm, sửa, xóa, tìm kiếm, áp dụng cho các bảng trong cơ sở dữ liệu.
 *
 * @param <T> Lớp đối tượng tương ứng với bảng trong cơ sở dữ liệu.
 */
public class GenericDAO<T> {

    private final Class<T> type;
    private List<T> cache = new ArrayList<>(); // Cache lưu trữ các đối tượng đã truy vấn
    private String uniqueField;

    // Constructor lấy kiểu dữ liệu của class T với id tự tăng
    @SuppressWarnings("unchecked")
    public GenericDAO(String uniqueField) {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.uniqueField = uniqueField;
    }

    /**
     * Thêm một bản ghi và trả về đối tượng tự tăng ID đã thêm
     *
     * @param entity Đối tượng cần thêm
     * @return Đối tượng đã thêm
     * @throws IllegalAccessException, SQLException, InstantiationException, InvocationTargetException, NoSuchMethodException
     */
    public T insertAndReturn(T entity) throws IllegalAccessException, SQLException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        // Kiểm tra xem đối tượng đã tồn tại theo trường duy nhất chưa
        if (findByUniqueField(String.valueOf(uniqueField), getFieldValue(entity, String.valueOf(uniqueField))) != null) {
            return null;
        }

        // Thực hiện chèn dữ liệu, kiểm tra xem trường uniqueField có phải là ID không
        if (uniqueField.getClass().getSimpleName().toLowerCase().contains("id")) {
            executeInsert(entity, true);
        } else {
            executeInsert(entity, false);
            entity = findByUniqueField(String.valueOf(uniqueField), getFieldValue(entity, String.valueOf(uniqueField)));
        }
        cache.add(entity);
        return entity;
    }

    /**
     * Tìm kiếm tất cả các bản ghi trong bảng
     *
     * @return Danh sách các đối tượng tìm thấy
     */
    public List<T> findAll() throws SQLException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String sql = "SELECT * FROM " + type.getSimpleName().toLowerCase();
        ResultSet resultSet = XJdbc.query(sql);
        cache = mapResultSetToEntityList(resultSet);
        return cache;
    }

    /**
     * Tìm kiếm đối tượng theo id
     *
     * @param id Định danh của đối tượng
     * @return Đối tượng tìm thấy hoặc null
     */
    public T findById(int id) throws SQLException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return findByUniqueField("id", id);
    }

    /**
     * Cập nhật đối tượng trong bảng
     *
     * @param entity Đối tượng cần cập nhật
     * @param id     Định danh của đối tượng cần cập nhật
     * @return Đối tượng sau khi đã cập nhật
     */
    public T update(T entity, int id) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        StringBuilder sql = new StringBuilder("UPDATE ");
        sql.append(type.getSimpleName().toLowerCase()).append(" SET ");

        Field[] fields = type.getDeclaredFields();
        StringBuilder setClause = new StringBuilder();
        List<Object> params = new ArrayList<>();

        for (Field field : fields) {
            field.setAccessible(true);
            if (!field.getName().equalsIgnoreCase("id")) { // Bỏ qua trường id
                setClause.append(field.getName()).append(" = ?, ");
                params.add(field.get(entity));
            }
        }

        sql.append(setClause, 0, setClause.length() - 2);
        sql.append(" WHERE id = ?");
        params.add(id);

        XJdbc.update(sql.toString(), params.toArray());
        return findById(id);
    }

    /**
     * Xóa đối tượng theo id
     *
     * @param id Định danh của đối tượng
     * @return true nếu xóa thành công, ngược lại false
     */
    public boolean delete(int id) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        T entity = findById(id);
        if (entity != null) {
            String sql = "DELETE FROM " + type.getSimpleName().toLowerCase() + " WHERE id = ?";
            XJdbc.update(sql, id);
            return true;
        }
        return false;
    }

    /**
     * Tìm kiếm đối tượng theo từ khóa
     *
     * @param keyword Từ khóa tìm kiếm
     * @return Danh sách đối tượng khớp với từ khóa
     */
    public List<T> searchByKeyword(String keyword) throws SQLException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String sql = "SELECT * FROM " + type.getSimpleName().toLowerCase() + " WHERE LOWER(title) LIKE ? OR LOWER(content) LIKE ?";
        ResultSet resultSet = XJdbc.query(sql, "%" + keyword.toLowerCase() + "%", "%" + keyword.toLowerCase() + "%");
        return mapResultSetToEntityList(resultSet);
    }

    /**
     * Tìm kiếm đối tượng theo trường duy nhất
     *
     * @param value Giá trị của trường
     * @return Đối tượng tìm thấy hoặc null
     */
    private T findByUniqueField(String uniqueField, Object value) throws SQLException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // Kiểm tra cache trước
        Optional<T> entityInCache = cache.stream()
                .filter(e -> {
                    try {
                        return Objects.equals(getFieldValue(e, uniqueField), value);
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                    return false;
                })
                .findFirst();

        if (entityInCache.isPresent()) {
            return entityInCache.get();
        }

        // Nếu không tồn tại trong cache, query từ DB và thêm vào cache
        String sql = "SELECT * FROM " + type.getSimpleName().toLowerCase() + " WHERE `" + uniqueField + "` = ?";
        ResultSet resultSet = XJdbc.query(sql, value);
        if (resultSet.next()) {
            T entity = mapResultSetToEntity(resultSet);
            cache.add(entity); // Thêm đối tượng vào cache sau khi tìm thấy
            return entity;
        }
        return null;
    }

    // Ánh xạ ResultSet vào đối tượng entity
    protected T mapResultSetToEntity(ResultSet resultSet) throws SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        T entity = type.getDeclaredConstructor().newInstance();
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = getResultSetValue(resultSet, field, field.getName());
            if (value != null) {
                field.set(entity, value);
            }
        }
        return entity;
    }

    // Lấy danh sách đối tượng từ ResultSet
    protected List<T> mapResultSetToEntityList(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        List<T> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapResultSetToEntity(rs));
        }
        return list;
    }

    // Lấy giá trị của Field từ đối tượng entity
    private Object getFieldValue(T entity, String fieldName) throws IllegalAccessException {
        for (Field field : type.getDeclaredFields()) {
            if (field.getName().equalsIgnoreCase(fieldName)) {
                field.setAccessible(true);
                return field.get(entity);
            }
        }
        return null;
    }

    // Lấy giá trị từ ResultSet theo kiểu Field
    private Object getResultSetValue(ResultSet resultSet, Field field, String columnName) throws SQLException {
        Class<?> fieldType = field.getType();
        if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
            return resultSet.getInt(columnName);
        } else if (fieldType.equals(String.class)) {
            return resultSet.getString(columnName);
        } else if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
            return resultSet.getBoolean(columnName);
        } else if (fieldType.equals(java.sql.Date.class)) {
            return resultSet.getDate(columnName);
        } else if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
            return resultSet.getLong(columnName);
        } else if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
            return resultSet.getDouble(columnName);
        } else {
            return resultSet.getObject(columnName);
        }
    }

    // Thực hiện chèn dữ liệu vào bảng
    private void executeInsert(T entity, boolean hasId) throws IllegalAccessException {

        StringBuilder sql = new StringBuilder("INSERT INTO ");
        sql.append(type.getSimpleName().toLowerCase());
        sql.append(" (");

        Field[] fields = type.getDeclaredFields();
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        for (Field field : fields) {
            if (hasId || !field.getName().equalsIgnoreCase("id")) { // Bỏ qua trường id tự tăng nếu không có id
                field.setAccessible(true);
                columns.append(field.getName()).append(",");
                values.append("?,");
            }
        }

        sql.append(columns, 0, columns.length() - 1).append(") VALUES (");
        sql.append(values, 0, values.length() - 1).append(")");

        List<Object> params = new ArrayList<>();
        for (Field field : fields) {
            if (hasId || !field.getName().equalsIgnoreCase("id")) {
                params.add(field.get(entity));
            }
        }
        XJdbc.update(sql.toString(), params.toArray());
    }
}
