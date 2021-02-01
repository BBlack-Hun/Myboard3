<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
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
        
        <!-- ajax -->
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

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
						                    <a href="/MM/freeboard/index">게시판 이동</a>
						                </li>
						            </ul>
						        </li>
						        <li>
						        	<c:choose>
						        		<c:when test="${!empty index.elastic}">
							            	<a class="nav-submenu" data-toggle="nav-submenu" href="#"><i class="nav-main-link-icon si si-energy"></i><span class="sidebar-mini-hide">엘라스틱 서치</span></a>
						            	</c:when>
						            	<c:otherwise>
						            		<a class="nav-submenu" data-toggle="nav-submenu" href="#"><i class="nav-main-link-icon si si-energy"></i><span class="sidebar-mini-hide">엘라스틱 서치</span></a>
						            	</c:otherwise>
						            </c:choose>
						            <ul>
						                <li>
						                    <a href="/MM/elastic/index?search=${index.str}&Category=통합검색">통합검색</a>
						                </li>
						            </ul>
						            <ul>
						                <li>
						                    <a href="/MM/elastic/index?search=${index.str}&Category=JTBC">JTBC</a>
						                </li>
						            </ul>
						            <ul>
						                <li>
						                    <a href="/MM/elastic/index?search=${index.str}&Category=KBS">KBS</a>
						                </li>
						            </ul>
						            <ul>
						                <li>
						                    <a href="/MM/elastic/index?search=${index.str}&Category=MBC">MBC</a>
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
								<span class="d-none d-sm-inline-block ml-1">user</span>
								<i class="fa fa-fw fa-angle-down d-none d-sm-inline-block"></i>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-right">
                                <li class="dropdown-header">Actions</li>
                                <li>
                                    <a tabindex="-1" href="#">
                                        <i class="si si-settings pull-right"></i>user's Profile
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