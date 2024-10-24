<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Header Section -->
<style>
    .header-container {
        background: linear-gradient(45deg, #4A4E69, #9A8C98);
        padding: 20px 0;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    }

    .header-logo h1 {
        font-size: 2rem;
        font-weight: 700;
        color: #fff;
        text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
    }

    .navbar {
        background-color: #22223b;
    }

    .navbar-nav .nav-link {
        color: #fff;
        font-size: 0.9rem;
        margin: 0 10px;
        transition: color 0.3s ease;
    }

    .navbar-nav .nav-link:hover {
        color: #f9c74f;
    }

    .form-control {
        border-radius: 20px;
    }

    .btn-outline-light {
        border-radius: 20px;
    }

    .admin-title {
        color: #f9c74f;
    }
</style>

<div class="header-container">
    <div class="header-logo text-center">
        <a href="${pageContext.request.contextPath}" class="text-decoration-none">
            <h1 class="display-4 text-light">- Quản Trị Hệ Thống -</h1>
        </a>
    </div>
</div>
<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#topNavbar"
                aria-controls="topNavbar" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <c:set var="baseUri" value="${pageContext.request.contextPath}"/>
        <div class="collapse navbar-collapse" id="topNavbar">
            <form action="${baseUri}/search" method="get" class="d-flex me-auto">
                <input type="search" name="keyword" class="form-control me-2" placeholder="Tìm kiếm" required/>
                <button type="submit" class="btn btn-outline-light">Tìm</button>
            </form>
            <ul class="navbar-nav ms-auto">
                <!-- Hiển thị nếu loggedUser.role == true -->
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}" class="nav-link">Trang chủ</a>
                </li>
                <%--                <c:if test="${loggedUser.role == true}">--%>
                <li class="nav-item">
                    <a href="${baseUri}/manage_user" class="nav-link">Quản Lý User</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<jsp:include page="/views/message.jsp"/>
