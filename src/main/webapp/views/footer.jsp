<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<style>
    .footer {
        background: linear-gradient(135deg, #4b79a1, #283e51);
        color: #fff;
    }
    .footer .container {
        padding: 40px 0;
    }
    .footer h5 {
        font-size: 1.5rem;
        font-weight: bold;
    }
    .footer p {
        font-size: 0.9rem;
        margin-bottom: 10px;
    }
    .footer .btn-success {
        background-color: #28a745;
        border-color: #28a745;
    }
    .footer .btn-primary {
        background-color: #007bff;
        border-color: #007bff;
    }
    .footer .text-muted {
        opacity: 0.8;
        color:#fff !important;
    }
    .footer .social-links i {
        font-size: 1.5rem;
        margin: 0 10px;
        color: #fff;
    }
    .footer .social-links i:hover {
        color: #f9c74f;
    }
    .footer .navigation-links a {
        color: #f9c74f;
        margin: 0 10px;
    }
    .footer .navigation-links a:hover {
        text-decoration: underline;
    }
</style>

<footer class="footer">
    <div class="container">
        <div class="row align-items-center">
            <!-- Left Section: Logo and Admin Information -->
            <div class="col-md-4 text-center text-md-start mb-3 mb-md-0">
                <img src="${pageContext.request.contextPath}/images/FPT_Polytechnic.png" alt="Admin Logo" class="mb-2" style="max-width: 150px;">
                <p class="mb-1">Quản trị viên: <strong>Nguyễn Đằng Giang</strong></p>
                <p class="mb-1">Quản lý trang tin điện tử Số </p>
                <p class="mb-1">Thông tin liên hệ - Văn phòng quản lý</p>
            </div>

            <!-- Right Section: Contact Information -->
            <div class="col-md-4 text-center text-md-end">
                <p class="mb-1">Địa chỉ: <strong>123 Đường ABC, P.4, Q.12, TP. Hồ Chí Minh</strong></p>
                <p class="mb-1">Hotline: <strong>0987.654.321</strong> - Email: <strong>nguyendanggiang99@congnghe.com</strong></p>
                <p class="mb-1">Hỗ trợ kỹ thuật: <strong>0123.456.789</strong></p>
            </div>
        </div>
        <div class="row mt-4">
            <div class="col text-center text-muted">
                <hr>
                <p class="mb-0">© Copyright 2024 FPT Polytechnic Co., Ltd. All rights reserved.</p>
            </div>
        </div>
    </div>
</footer>
