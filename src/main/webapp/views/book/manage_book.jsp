<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../page_header.jsp">
        <jsp:param name="title" value="Quản lý sách"/>
    </jsp:include>
    <style>
        body {
            background-color: #f8f9fa;
        }

        h1 {
            color: #007bff;
        }

        .form-control, .form-select {
            border-radius: 0.5rem;
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
        }

        .btn-secondary {
            background-color: #6c757d;
            border: none;
        }

        .card {
            border: none;
            border-radius: 0.5rem;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .search-bar {
            max-width: 600px;
            margin: 20px auto;
        }

        .table-container {
            margin-top: 30px;
        }

        .table {
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .btn-sm {
            padding: 5px 10px;
        }

        .action-btns a {
            margin-right: 10px;
        }

        /* Customize form labels */
        label {
            font-weight: bold;
            color: #343a40;
        }

    </style>
</head>
<body>
<div class="container-lg">
    <jsp:include page="../header.jsp"/>
    <c:set var="baseUri" value="${pageContext.request.contextPath}"/>
    <h1 class="text-center my-4">Thông tin</h1>

    <!-- Form for Book Management -->
    <div class="container my-5">
        <div class="card p-4">
            <form action="${baseUri}/manage_user" method="POST" enctype="multipart/form-data">
                <div class="mb-3">
                    <label class="form-label">ID</label>
                    <input type="text" class="form-control" name="id" value="${users.id}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Fullname</label>
                    <input type="text" class="form-control" name="fullname" value="${users.fullname}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Password</label>
                    <input type="password" class="form-control" name="password" required value="${users.password}"/>
                </div>

                <div class="mb-3">
                    <div class="gender-section">
                        <h6>Vai trò:</h6>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="admin"
                                   id="admin" value="true" ${users.admin?'checked':''}/>
                            <label class="form-check-label">Admin</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="admin"
                                   id="author" ${users.admin?'':'checked'} value="false"/>
                            <label class="form-check-label" for="author">User</label>
                        </div>

                    </div>
                </div>
                <div class="form-group text-center">
                    <button type="submit" formaction="${baseUri}/create_user" class="btn btn-success">Create</button>
                    <button type="submit" formaction="${baseUri}/update_user" class="btn btn-primary">Update</button>
                    <button type="submit" formaction="${baseUri}/delete_user?id=${users.id}" class="btn btn-danger">
                        Delete
                    </button>
                    <button type="reset" class="btn btn-warning">Reset</button>
                </div>
            </form>
        </div>
    </div>
    <div class="table-container">
        <form action="${baseUri}/search?keyword" method="GET" class="mb-3">
            <div class="row g-3 align-items-center justify-content-center">
                <div class="col-auto">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="keyword"
                               value=""/>
                        <label class="form-check-label" for="author">All</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="keyword"
                               value="admin"/>
                        <label class="form-check-label">Admin</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="keyword"
                               value="user"/>
                        <label class="form-check-label" for="author">User</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <button type="submit">Lọc</button>
                    </div>
                </div>

            </div>
        </form>
        <!-- Bảng hiển thị sách -->
        <div class="card shadow">
            <div class="card-body">
                <table class="table table-hover table-bordered table-striped">
                    <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Fullname</th>
                        <th>Password</th>
                        <th>Role</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="users" items="${userList}">
                        <tr>
                            <td>${users.id}</td>
                            <td>${users.fullname}</td>
                            <td>${users.password}</td>
                            <td>
                                    ${users.admin==true?'admin':'user'}
                            </td>
                            <td class="text-center action-btns">
                                <a href="${baseUri}/update_user?id=${users.id}" class="btn btn-warning btn-sm">
                                    <i class="bi bi-pencil"></i> Sửa
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
