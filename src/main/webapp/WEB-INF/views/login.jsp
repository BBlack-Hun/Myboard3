<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
        <meta charset="utf-8">

        <title> Mummu's World! </title>

        <meta name="description" content="OneUI - Admin Dashboard Template &amp; UI Framework created by pixelcave and published on Themeforest">
        <meta name="author" content="pixelcave">
        <meta name="robots" content="noindex, nofollow">
        <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=0">

        <!-- Icons -->
        <link rel="shortcut icon" href="<c:url value="/resources/assets/img/favicons/favicon.png"/>" >

        <link rel="icon" type="image/png" href="<c:url value="/resources/assets/img/favicons/favicon-192x192.png"/>" sizes="192x192">
        <!-- END Icons -->

        <!-- Stylesheets -->
        <!-- Web fonts -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400italic,600,700%7COpen+Sans:300,400,400italic,600,700">

        <!-- Bootstrap and OneUI CSS framework -->
        <link rel="stylesheet" href="<c:url value="/resources/assets/css/bootstrap.min.css"/>">
        <link rel="stylesheet" id="css-main" href="<c:url value="/resources/assets/css/oneui.css"/>">

        <!-- You can include a specific file from css/themes/ folder to alter the default color theme of the template. eg: -->
        <!-- <link rel="stylesheet" id="css-theme" href="assets/css/themes/flat.min.css"> -->
        <!-- END Stylesheets -->
    </head>
    <body>
        <!-- Login Content -->
        <div class="bg-white pulldown">
            <div class="content content-boxed overflow-hidden">
                <div class="row">
                    <div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 col-lg-4 col-lg-offset-4">
                        <div class="push-30-t push-50 animated fadeIn">
                            <!-- Login Title -->
                            <div class="text-center">
<!--                             	메인 이미지 및 글씨 -->
                                <img src="<c:url value="/resources/assets/img/login/login.jpg"/>" width="200" height="200">
                                <p class="text-muted push-15-t">Mummu's Manager~</p>
                            </div>
                            <!-- END Login Title -->

                            <!-- Login Form -->
                            <!-- jQuery Validation (.js-validation-login class is initialized in js/pages/base_pages_login.js) -->
                            <!-- For more examples you can check out https://github.com/jzaefferer/jquery-validation -->
                            <!-- 로그인시 값을 실행 시킬 action="page 지정 및 controller에서 처리할 것" -->
                            <form class="js-validation-login form-horizontal push-30-t" action="/MM/login" method="post">
                                <div class="form-group">
                                    <div class="col-xs-12">
                                        <div class="form-material form-material-primary floating">
                                        	<!-- 태그로 인식할 id -->
                                            <input class="form-control" type="text" id="login-username" name="login-username">
                                            <label for="login-username">Username</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-12">
                                        <div class="form-material form-material-primary floating">
                                        	<!-- 태그로 인식할 id -->
                                            <input class="form-control" type="password" id="login-password" name="login-password">
                                            <label for="login-password">Password</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group push-30-t">
                                    <div class="col-xs-12 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4">
                                        <button class="btn btn-sm btn-block btn-primary" type="submit">Log in</button>
                                    </div>
                                </div>
                            </form>
                            <!-- END Login Form -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END Login Content -->

        <!-- Login Footer -->
        <div class="pulldown push-30-t text-center animated fadeInUp">
            <Strong>Project</Strong> &copy; <span class="js-year-copy"></span>
        </div>
        <!-- END Login Footer -->

        <!-- OneUI Core JS: jQuery, Bootstrap, slimScroll, scrollLock, Appear, CountTo, Placeholder, Cookie and App.js -->
        <script src="<c:url value="/resources/assets/js/core/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/assets/js/core/bootstrap.min.js"/>"></script>
        <script src="<c:url value="/resources/assets/js/core/jquery.slimscroll.min.js"/>"></script>
        <script src="<c:url value="/resources/assets/js/core/jquery.scrollLock.min.js"/>"></script>
        <script src="<c:url value="/resources/assets/js/core/jquery.appear.min.js"/>"></script>
        <script src="<c:url value="/resources/assets/js/core/jquery.countTo.min.js"/>"></script>
        <script src="<c:url value="/resources/assets/js/core/jquery.placeholder.min.js"/>"></script>
        <script src="<c:url value="/resources/assets/js/core/js.cookie.min.js"/>"></script>
        <script src="<c:url value="/resources/assets/js/app.js"/>"></script>

        <!-- Page JS Plugins -->
        <script src="<c:url value="/resources/assets/js/plugins/jquery-validation/jquery.validate.min.js"/>"></script>

        <!-- Page JS Code -->
        <script src="<c:url value="/resources/assets/js/pages/base_pages_login.js"/>"></script>
    </body>
</html>