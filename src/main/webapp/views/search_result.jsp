<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="page_header.jsp">
        <jsp:param name="title" value="Kết quả Tìm Kiếm - Trang Quản Trị Admin"/>
    </jsp:include>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .header-title {
            color: #007bff;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-lg">
    <div class="container mt-4">
        <div class="row">
            <div class="col-lg-12">
                <h2 class="header-title">Kết quả tìm kiếm cho: ${param.keyword}</h2>
                <section class="mt-4">
                    <h5 class="header-title">Tin tức</h5>
                    <div class="card">
                        <div class="card-body">
                            <c:if test="${not empty newsResult}">
                                <ul class="list-group">
                                    <c:forEach var="news" items="${newsResult}">
                                        <li class="list-group-item">${news.title} - ${news.postedDate}</li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                            <c:if test="${empty newsResult}">
                                <p>Không tìm thấy tin tức nào.</p>
                            </c:if>
                        </div>
                    </div>
                </section>

                <section class="mt-4">
                    <h5 class="header-title">Danh mục</h5>
                    <div class="card">
                        <div class="card-body">
                            <c:if test="${not empty categoryResult}">
                                <ul class="list-group">
                                    <c:forEach var="category" items="${categoryResult}">
                                        <li class="list-group-item">${category.name}</li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                            <c:if test="${empty categoryResult}">
                                <p>Không tìm thấy danh mục nào.</p>
                            </c:if>
                        </div>
                    </div>
                </section>

                <section class="mt-4">
                    <h5 class="header-title">Người dùng</h5>
                    <div class="card">
                        <div class="card-body">
                            <c:if test="${not empty userResult}">
                                <ul class="list-group">
                                    <c:forEach var="user" items="${userResult}">
                                        <li class="list-group-item">${user.fullname} (${user.email})</li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                            <c:if test="${empty userResult}">
                                <p>Không tìm thấy người dùng nào.</p>
                            </c:if>
                        </div>
                    </div>
                </section>

                <section class="mt-4">
                    <h5 class="header-title">Người dùng theo vai trò</h5>
                    <div class="card">
                        <div class="card-body">
                            <c:if test="${not empty userByRoleResult}">
                                <ul class="list-group">
                                    <c:forEach var="user" items="${userByRoleResult}">
                                        <li class="list-group-item">${user.fullname} (${user.email})
                                            - ${user.isRole() ? 'Admin' : 'Author'}</li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                            <c:if test="${empty userByRoleResult}">
                                <p>Không tìm thấy người dùng nào với vai trò này.</p>
                            </c:if>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </div>
</div>
<br>
<hr>
<jsp:include page="footer.jsp"/>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
