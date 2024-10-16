<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../page_header.jsp">
        <jsp:param name="title" value="Cập nhật bài"/>
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
    </style>
</head>
<body>
<div class="container-lg">
    <jsp:include page="../header.jsp"/>
    <c:set var="baseUri" value="${pageContext.request.contextPath}"/>
    <h1 class="text-center my-4">Chỉnh sửa bài</h1>
    <!-- Form for Writing/Editing News -->
    <div class="container my-5">
        <div class="card p-4">
            <form action="${baseUri}/create_news" method="POST"
                  enctype="multipart/form-data">
                <div class="mb-3">
                    <label class="form-label">Tiêu đề</label>
                    <input type="text" class="form-control" name="title" value="${news.title}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Nội dung</label>
                    <textarea class="form-control" name="content" rows="20" required>${news.content}</textarea>
                </div>

                <div class="mb-3">
                    <label class="form-label">Hình ảnh</label>
                    <input type="file" class="form-control" name="image" value="${news.image}">
                    <c:if test="${news != null}">
                        <img id="thumbnail" alt="Image Preview" style="width:20%; margin-top: 10px"
                             src="${pageContext.request.contextPath}/newsImages/${news.image}"/>
                    </c:if>
                </div>

                <div class="mb-3">
                    <label class="form-label">Danh mục</label>
                    <select class="form-select" name="category_id" required>
                        <c:forEach var="cat" items="${listCategory}">
                            <option value="${cat.id}" ${news != null && cat.id == news.categoryId ? 'selected' : ''}>${cat.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label">Tác giả</label>
                    <select class="form-select" name="author" required>
                        <c:forEach var="user" items="${listUser}">
                            <option value="${user.id}" ${news != null && user.id == news.author ? 'selected' : ''}>${user.fullname}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label">Ngày đăng</label>
                    <input type="date" name="postedDate" size="20" required class="form-control"
                           value="<fmt:formatDate pattern='yyyy-MM-dd' value='${news.postedDate}' />"/>
                </div>
                <div class="mb-3 form-check">
                    <input type="checkbox" class="form-check-input" id="home"
                           name="home">
                    <label class="form-check-label" for="home">Hiển thị trên trang chủ</label>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Lưu Bài</button>
                    <button type="reset" class="btn btn-secondary">Làm mới</button>
                    <button type="button" class="btn btn-secondary ml-2" onclick="history.go(-1);">Hủy</button>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp"/>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
