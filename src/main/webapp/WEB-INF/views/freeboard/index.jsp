<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
    <head>
        <meta charset="utf-8">

        <title>Mummu's Manager</title>

        <meta name="description" content="OneUI - Admin Dashboard Template &amp; UI Framework created by pixelcave and published on Themeforest">
        <meta name="author" content="pixelcave">
        <meta name="robots" content="noindex, nofollow">
        <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=0">

        <!-- Icons -->
        <!-- The following icons can be replaced with your own, they are used by desktop and mobile browsers -->
        <link rel="shortcut icon" href="<c:url value="/resources/assets/img/favicons/favicon.png"/>">

        <link rel="icon" type="image/png" href="<c:url value="resources/assets/img/favicons/favicon-16x16.png"/>" sizes="16x16">
        <link rel="icon" type="image/png" href="<c:url value="resources/assets/img/favicons/favicon-32x32.png"/>" sizes="32x32">
        <link rel="icon" type="image/png" href="<c:url value="resources/assets/img/favicons/favicon-192x192.png"/>" sizes="192x192">
        
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
    
        <!-- Page Container -->
        <div id="page-container" class="sidebar-l sidebar-o side-scroll header-navbar-fixed">
        	
			<!-- Left -->
			<nav id="sidebar">
				<div id="sidebar-scroll">
				    <div class="sidebar-content">
					    <!-- Side Header -->
						<div class="side-header side-content bg-white-op">
						    <!-- Logo -->
							<a class="h5 text-white" href="/MM/Main">
								<img class="text-primary" src="<c:url value="/resources/assets/img/favicons/favicon-16x16.png"/>">
							    <span class="h5 font-w700 sidebar-mini-hide">Mummu's</span> <span class="font-w100 sidebar-mini-hide">Manager</span>
							</a>
							<!-- END Logo -->
							
							<!-- Options -->
							<button class="btn btn-link text-gray pull-right hidden-md hidden-lg" type="button" data-toggle="layout" data-action="sidebar_close">
                                <i class="fa fa-times"></i>
                            </button>
							<!-- END Options -->	
						</div>
						<!-- End Side Header -->			    
						
						<!-- Side Navigation -->
						<div class="side-content side-content-full">
						    <ul class="nav-main">
						        <li class='nav-main-item'>
						            <a class='nav-main-link' href="/MM/Main"><i class="nav-main-link-icon si si-energy"></i><span class="sidebar-mini-hide">대시보드</span></a>
						        </li>
						        <li class="nav-main-heading"><span class="sidebar-mini-hide">게시판</span></li>
						        <li>
						            <a class="nav-submenu" data-toggle="nav-submenu" href="#"><i class="si si-energy"></i><span class="sidebar-mini-hide">공지사항</span></a>
						            <ul>
						                <li>
						                    <a href="/MM/notice/index">게시판 이동</a>
						                </li>
						            </ul>
						        </li>
						        <li>
						            <a class="nav-submenu" data-toggle="nav-submenu" href="#"><i class="nav-main-link-icon si si-energy"></i><span class="sidebar-mini-hide">자유게시판</span></a>
						            <ul>
						                <li>
						                    <a href="index">게시판 이동</a>
						                </li>
						            </ul>
						        </li>
						        <li class="nav-main-heading"><span class="sidebar-mini-hide">개발중</span></li>
						        <li>
						        	<a class="nav-submenu" data-toggle="nav-submenu" href="#"><i class="si si-energy"></i><span class="sidebar-mini-hide">개발중</span></a>
						            <ul>
						                <li>
						                    <a href="#">개발중</a>
						                </li>
						            </ul>
						        <li class="nav-main-heading"><span class="sidebar-mini-hide">운영관리</span></li>
						        <li>
						            <a class="nav-main-link"  href="#"><i class="nav-main-link-icon si si-energy"></i><span class="sidebar-mini-hide">사용자 관리</span></a>
						        </li>
						    </ul>
						</div>
					<!-- END Side Navigation -->
					</div>
				</div>
				<!-- END Side Scroll Container -->
			</nav>
			<!-- END Sidebar-->

			<!-- Header -->
			<header id="header-navbar" class="content-mini content-mini-full">
				<!-- Left Session -->
				<ul class="nav-header pull-left">
                    <li class="hidden-md hidden-lg">
                        <!-- Layout API, functionality initialized in App() -> uiLayoutApi() -->
                        <button class="btn btn-default" data-toggle="layout" data-action="sidebar_toggle" type="button">
                            <i class="fa fa-navicon"></i>
                        </button>
                    </li>
                    <li class="hidden-xs hidden-sm">
                        <!-- Layout API, functionality initialized in App() -> uiLayoutApi() -->
                        <button class="btn btn-default" data-toggle="layout" data-action="sidebar_mini_toggle" type="button">
                            <i class="fa fa-ellipsis-v"></i>
                        </button>
                    </li>
				</ul>
				<!-- END Left Session -->
			
				<!-- Right Session -->
				<ul class="nav-header pull-right">
					<li>
						<div class="btn-group">
							<button class="btn btn-default btn-image dropdown-toggle" data-toggle="dropdown" type="button">
                                <img class="rounded" src="<c:url value="/resources/assets/img/avatars/avatar10.jpg"/>" alt="Header Avatar" style="width: 18px;">
								<span class="d-none d-sm-inline-block ml-1">admin</span>
								<i class="fa fa-fw fa-angle-down d-none d-sm-inline-block"></i>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-right">
                                <li class="dropdown-header">Actions</li>
                                <li>
                                    <a tabindex="-1" href="#">
                                        <i class="si si-settings pull-right"></i>admin's Profile
                                    </a>
                                </li>
                                <li>
                                     <a tabindex="-1" href="#" onclick="document.getElementById('logout').submit();">
                                        <i class="si si-logout pull-right"></i>Log out
                                    </a>
									<form id="logout" action="/MM/logout" method="POST">
										<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
									</form>
                                </li>
                            </ul>
						</div>
					</li>				
				</ul>
			</header>
			<!-- END Header -->
	
			<!-- Main Container -->
			<main id="main-container">
				<!-- Page Header -->
				<div class="content bg-gray-lighter">
					<div class="row items-push">
						<div class="col-sm-7">
							<h1 class="page-heading">
								자유게시판 <small>FreeBoard</small>
							</h1>
						</div>
						<div class="col-sm-5 text-right hidden-xs">
							<ol class="breadcrumb push-10-t">
								<li>게시판</li>
								<li><a class="link-effect" href="index">자유게시판</a></li>
							</ol>
						</div>
					</div>
				</div>
				<!-- END Page Header -->
				
                <!-- Page Content -->
                <div class="content content d-none">
	                <div class="block block-rounded">
		                <div class="table-responsive">
		                	<table class="table table-sm table-vcenter">
		                		<thead>
		                			<tr>
		                				<th class="text-center" style="width:50px;"><a href="javascript:void(0)" class="all-check">#</a></th>
		                				<th style="width: 10%">글 번호</th>
		                				<th class="text-center" style="width: 35%">제목</th>
		                				<th style="width: 15%">작성자</th>
		                				<th style="width: 15%">생성일</th>
		                				<th style="width: 15%">수정일</th>
		                				<th style="width: 10%">조회수</th>
		                			</tr>
		                		</thead>
		                		<tbody>
		                			<c:forEach items="${list}" var = "list">
										<tr>
											<td class="text-center">
												<div class="custom-control custom-ckeckbox mb-1">
													<input type="checkbox" class="custom-control-input" id="${list.no }" name="dict-checkbox">
				                                    <label class="custom-control-label" for="id"></label>
												</div>
											</td>
											<td>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${list.no}" /></td>
											<td>
												<a href="/board/readView?no=${list.no}&page=${scrl.page}&perPageNum=${scrl.perPageNum}&searchType=${scrl.searchType}&keyword=${scrl.keyword}"><c:out value="${list.title}" /></a>
											</td>
											<td><c:out value="${list.writer}" /></td>
											<td><fmt:formatDate value="${list.regDt}" pattern="yyyy-MM-dd"/></td>
											<td><fmt:formatDate value="${list.modDt}" pattern="yyyy-MM-dd"/></td>
											<td>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${list.hit}" /></td>
										</tr>
									</c:forEach>
		                		</tbody>
		                	</table>
		                </div>
	                </div>
                </div>
                <!-- END Page Content -->
            </main>
            <!-- END Main Container -->

            <!-- Footer -->
            <footer id="page-footer" class="content-mini content-mini-full font-s12 bg-gray-lighter clearfix">
                <div class="pull-left">
                    <strong>Project</strong> &copy; <span class="js-year-copy"></span>
                </div>
            </footer>
            <!-- END Footer -->
        </div>
        <!-- END Page Container -->        

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

        <!-- Page JS Code -->
        <script src="<c:url value="/resources/assets/js/pages/base_pages_dashboard_v2.js"/>"></script>
        <script>
            jQuery(function () {
                // Init page helpers (CountTo plugin)
                App.initHelpers('appear-countTo');
            });
        </script>
    </body>
</html>