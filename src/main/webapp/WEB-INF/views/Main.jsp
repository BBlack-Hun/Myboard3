<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="include/header.jsp" %>

				<!-- Page Header -->
<!-- 				<div class="bg-image overflow-hidden" style="background-image: url(/Myboard3/src/main/webapp/resources/assets/img/photos/Dog(fixed).jpg);" > -->
				<div class="bg-image overflow-hidden" style="background-image: url(resources/assets/img/photos/Dog3.jpg);" >
					<div class="bg-black-op">
						<div class="content content-narrow">
							<div class="block block-transparent">
								<div class="block-content block-content-full">
									<h1 class="h1 font-w300 text-white animated fadeInDown push-50-t push-5">Mummu Project</h1>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- END Page Header -->

                <!-- Page Content -->
                <div class="content content-narrow">
                    <!-- Stats -->
                    <div class="row text-uppercase">
                        <div class="col-xs-6 col-sm-3">
                            <div class="block block-rounded">
                                <div class="block-content block-content-full">
                                    <div class="text-muted">
                                        <small><i class="si si-calendar"></i> Today</small>
                                    </div>
                                    <div class="font-s12 font-w700">공지사항 글 갯수</div>
                                    <a class="h2 font-w300 text-primary" href="notice/index" data-toggle="countTo" data-to="${noticecnt}"></a>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-3">
                            <div class="block block-rounded">
                                <div class="block-content block-content-full">
                                    <div class="text-muted">
                                        <small><i class="si si-calendar"></i> Today</small>
                                    </div>
                                    <div class="font-s12 font-w700">자유게시판 글 갯수</div>
                                    <a class="h2 font-w300 text-primary" href="freeboard/index" data-toggle="countTo" data-to="${boardcnt}"></a>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-3">
                            <div class="block block-rounded">
                                <div class="block-content block-content-full">
                                    <div class="text-muted">
                                        <small><i class="si si-calendar"></i> Today</small>
                                    </div>
                                    <div class="font-s12 font-w700">회원가입한 사람</div>
                                    <a class="h2 font-w300 text-primary" data-toggle="countTo" data-to="1"></a>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-3">
                            <div class="block block-rounded">
                                <div class="block-content block-content-full">
                                    <div class="text-muted">
                                        <small><i class="si si-calendar"></i> Today</small>
                                    </div>
                                    <div class="font-s12 font-w700">탈퇴한 사람</div>
                                    <a class="h2 font-w300 text-primary" data-toggle="countTo" data-to="0"></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END Stats -->
                    
                    <!-- search  -->
                    <!-- Dashboard Charts -->
                    <div class="row">
                        <div class="col-md-6">
                            <div class="block block-rounded block-opt-refresh-icon8">
                                <div class="block-header">
                                    <ul class="block-options">
                                        <li>
                                            <button type="button" data-toggle="block-option" data-action="refresh_toggle" data-action-mode="demo"><i class="si si-refresh"></i></button>
                                        </li>
                                    </ul>
                                    <h3 class="block-title">공지사항</h3>
                                </div>
                                
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="block block-rounded block-opt-refresh-icon8">
                                <div class="block-header">
                                    <ul class="block-options">
                                        <li>
                                            <button type="button" data-toggle="block-option" data-action="refresh_toggle" data-action-mode="demo"><i class="si si-refresh"></i></button>
                                        </li>
                                    </ul>
                                    <h3 class="block-title">자유게시판</h3>
                                </div> 
                                
                            </div>
                        </div>
                    </div>
                    <!-- END Dashboard Charts -->
                </div>
                <!-- END Page Content -->
            </main>
            <!-- END Main Container -->
            <!-- footer include -->                
<%@include file="include/footer.jsp" %>
            