<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">

    <style>
        body {
            background-color: #f8f9fa;
        }

        .header-title {
            color: #007bff;
            font-size: 2rem;
            font-weight: bold;
        }


        table {
            background-color: #fff;
            width: 100%;
            margin: 0 auto;

            .create-news-btn {
                background-color: #28a745;
                border-color: #28a745;
                color: #fff;
            }

            .create-news-btn:hover {
                background-color: #218838;
                border-color: #1e7e34;
            }

            .table th, .table td {
                vertical-align: middle;
            }

            .action-btns a {
                margin-right: 5px;
            }
        }

        /* Style cho tiêu đề trang */
        .header-title {
            color: #007bff;
            font-size: 2rem;
            font-weight: bold;
        }

        /* Style cho nút thêm danh mục */
        .create-category-btn {
            background-color: #28a745;
            border-color: #28a745;
            color: #fff;
            font-size: 1.25rem;
            font-weight: 600;
            display: flex;
            align-items: center;
            justify-content: center;
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
        }

        .create-category-btn i {
            font-size: 1.5rem;
        }

        .create-category-btn:hover {
            background-color: #218838;
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.2);
        }
    </style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<br>
<div class="container-lg">
    <c:set var="baseUri" value="${pageContext.request.contextPath}"/>
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1 class="header-title">List New</h1>
        <a href="${baseUri}/create_news" class="btn create-category-btn btn-lg">Tạo Tin Tức Mới</a>
    </div>
    <div class="card shadow">
        <div class="card-body">
            <table class="table table-hover table-bordered table-striped">
                <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Tiêu Đề</th>
                    <th>Tác Giả</th>
                    <th>Ngày Đăng</th>
                    <th>Nội Dung</th>
                    <th>Hành Động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="news" items="${listNews}">
                    <!-- Nếu người dùng là admin thì hiển thị tất cả, nếu không thì chỉ hiển thị tin tức của chính họ -->
                    <c:if test="${loggedUser.role == true || loggedUser.id == news.author}">
                        <tr>
                            <td>${news.id}</td>
                            <td>${news.title}</td>
                            <td>
                                <c:forEach var="user" items="${listUser}">
                                    <c:if test="${user.id == news.author}">
                                        ${user.fullname}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td><fmt:formatDate pattern='dd/MM/yyyy' value='${news.postedDate}'/></td>
                            <td>${fn:substring(news.content, 0, 50)}...</td>
                            <td class="text-center action-btns">
                                <a href="${baseUri}/update_news?id=${news.id}" class="btn btn-warning btn-sm">
                                    <i class="bi bi-pencil"></i> Sửa
                                </a>
                                <a href="${baseUri}/delete_news?id=${news.id}" class="btn btn-danger btn-sm"
                                   onclick="return confirm('Bạn có chắc muốn xóa tin này?');">
                                    <i class="bi bi-trash"></i> Xóa
                                </a>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<br>
<!-- Footer -->
<jsp:include page="../footer.jsp"/>
<hr class="mt-5">
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
