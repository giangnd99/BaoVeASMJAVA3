<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="views/page_header.jsp">
        <jsp:param name="title" value="Trang Quản Trị Admin"/>
    </jsp:include>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .sidebar {
            background-color: #343a40;
        }

        .sidebar .list-group-item {
            color: #fff;
        }

        .sidebar .list-group-item.active {
            background-color: #007bff;
            border-color: #007bff;
        }

        .sidebar .list-group-item:hover {
            background-color: #495057;
        }

        .header-title {
            color: #007bff;
        }
    </style>
</head>
<body>
<jsp:include page="views/header.jsp"/>
<div class="container-lg">
    <div class="container mt-4">
        <div class="row">
            <div class="col-lg-9">
                <h2 class="header-title">Chào mừng đến với Trang Quản Trị Admin</h2>
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Thông tin quản lý</h5>
                        <p class="card-text">Cập nhật và quản lý thông tin của trang tin tức công nghệ một cách dễ dàng
                            và hiệu quả.</p>
                        <a href="manage_news" class="btn btn-primary">Quản lý tin tức</a>
                    </div>
                </div>
                <section class="mt-4">
                    <h5 class="header-title">Tin tức hệ thống</h5>
                    <div class="card">
                        <div class="card-body">
                            <p>Cập nhật hệ thống lần cuối: <strong>01/09/2024</strong></p>
                            <p>Số người dùng hiện tại: <strong>350</strong></p>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </div>
</div>
<br>
<br>
<br>
<hr>
<jsp:include page="views/footer.jsp"/>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
