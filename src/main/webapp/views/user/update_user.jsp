<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        /* Căn chỉnh hợp lý cho form */
        .form-check-inline {
            margin-right: 20px;
        }

        .gender-section {
            margin-top: 20px;
        }

        .form-outline {
            margin-bottom: 20px;
        }

        .checkbox-label {
            text-align: center;
        }
    </style>
</head>
<body style="height: auto;">
<!-- Include Header -->
<jsp:include page="/views/header.jsp"/>
<section style="background-color: #eee; padding: 20px 20px">
    <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-lg-12 col-xl-11">
                <div class="card text-black" style="border-radius: 25px;">
                    <div class="card-body p-md-1">
                        <div class="row justify-content-center">
                            <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">
                                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Đăng kí</p>
                                <form action="${pageContext.request.contextPath}/update_user" method="POST"
                                      class="mx-1 mx-md-4">
                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="idUser">ID của bạn</label>
                                        <input type="text" id="idUser" name="userId" value="${Users.id}" readonly
                                               class="form-control"/>
                                    </div>
                                    <!-- Tên của bạn -->
                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="fullname">Tên của bạn</label>
                                        <input type="text" id="fullname" name="fullname" value="${Users.fullname}"
                                               class="form-control"/>
                                    </div>
                                    <!-- Sinh nhật -->
                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="birthday">Sinh nhật</label>
                                        <input type="date" id="birthday" name="birthday" value="${Users.birthday}"
                                               class="form-control"/>
                                    </div>
                                    <!-- Giới tính -->
                                    <div class="gender-section">
                                        <h6>Giới Tính:</h6>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="gender"
                                                   id="femaleGender" value="false" checked/>
                                            <label class="form-check-label" for="femaleGender">Nữ</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="gender"
                                                   id="maleGender" value="true" ${Users.gender?'checked':''}/>
                                            <label class="form-check-label" for="maleGender">Nam</label>
                                        </div>
                                    </div>
                                    <!-- Email -->
                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="form3Example3c">Email của bạn</label>
                                        <input type="email" id="form3Example3c" name="email" class="form-control"
                                               value="${Users.email}"/>
                                    </div>
                                    <!-- Số điện thoại -->
                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="phone">Số điện thoại của bạn</label>
                                        <input type="number" id="phone" name="phone" class="form-control"
                                               value="${Users.phone}"/>
                                    </div>
                                    <!-- Mật khẩu -->
                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="form3Example4c">Mật khẩu</label>
                                        <input type="password" id="form3Example4c" name="password" class="form-control"
                                               value="${Users.password}"/>
                                    </div>
                                    <div class="gender-section">
                                        <h6>Vai trò:</h6>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="role"
                                                   id="admin" value="true" ${Users.role?'checked':''}/>
                                            <label class="form-check-label" for="femaleGender">Admin</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="role"
                                                   id="author" value="false"/>
                                            <label class="form-check-label" for="author">Author</label>
                                        </div>

                                    </div>
                                    <br>
                                    <!-- Điều khoản dịch vụ -->
                                    <div class="form-check d-flex justify-content-center mb-5">
                                        <input class="form-check-input me-2" type="checkbox" value=""
                                               id="form2Example3c"/>
                                        <label class="form-check-label checkbox-label" for="form2Example3c">
                                            Đồng ý với những điều kiện của chúng tôi
                                            <a href="#!">Điều khoản dịch vụ</a>
                                        </label>
                                    </div>

                                    <!-- Nút Đăng ký -->
                                    <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                        <button type="submit" class="btn btn-primary btn-lg">Lưu</button>
                                    </div>

                                </form>

                            </div>

                            <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">
                                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
                                     class="img-fluid" alt="Sample image">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Include Footer -->
<jsp:include page="/views/footer.jsp"></jsp:include>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
