<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    <title>List User</title>
    <style>
        /* Style tùy chỉnh */
        body {
            background-color: #f8f9fa;
        }

        .header-title {
            font-size: 2rem;
            font-weight: bold;
            color: #007bff;
        }

        .table th, .table td {
            vertical-align: middle;
        }

        /* Màu sắc cho các nút */
        .btn-primary-custom {
            background-color: #28a745;
            border-color: #28a745;
            color: #fff;
        }

        .btn-primary-custom:hover {
            background-color: #218838;
            border-color: #1e7e34;
        }

        .password-col {
            max-width: 150px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }

        .action-btns a {
            margin-right: 5px;
        }
    </style>
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="container-lg mt-5">
    <!-- Tiêu đề và nút Thêm Người Dùng -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1 class="header-title">Danh sách người dùng</h1>
        <a href="${pageContext.request.contextPath}/create_user" class="btn btn-primary-custom btn-lg">+ Thêm người dùng</a>
    </div>

    <!-- Bảng danh sách người dùng -->
    <div class="card shadow-sm">
        <div class="card-body">
            <table class="table table-hover table-bordered table-striped">
                <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Họ Tên</th>
                    <th>Password</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Ngày Sinh</th>
                    <th>Giới Tính</th>
                    <th>Vai Trò</th>
                    <th>Hành Động</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.fullname}</td>
                    <td class="password-col">${user.password}</td>
                    <td>${user.email}</td>
                    <td>${user.phone}</td>
                    <td>${user.birthday}</td>
                    <td>${user.gender? "Nam" : "Nữ"}</td>
                    <td>${user.role?"ADMIN":"AUTHOR"}</td>
                    <td class="text-center action-btns">
                        <a href="update_user?id=${user.id}" class="btn btn-warning btn-sm">
                            <i class="bi bi-pencil"></i> Sửa
                        </a>
                        <a href="delete_user?id=${user.id}" class="btn btn-danger btn-sm">
                            <i class="bi bi-trash"></i> Xóa
                        </a>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Footer -->
<hr class="mt-5">
<jsp:include page="../footer.jsp"/>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
