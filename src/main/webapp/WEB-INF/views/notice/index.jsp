<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../include/header.jsp" %>

				<!-- Page Header -->
				<div class="content bg-gray-lighter">
					<div class="row items-push">
						<div class="col-sm-7">
							<h1 class="page-heading">공지사항 <small>NoticeBoard</small></h1>
						</div>
						<div class="col-sm-5 text-right hidden-xs">
							<ol class="breadcrumb push-10-t">
								<li>게시판</li>
								<li><a class="link-effect" href="index">공지사항</a></li>
							</ol>
						</div>
					</div>
				</div>
				<!-- END Page Header -->

                <!-- Page Content -->
                <div class="content ">
	                <div class="block block-rounded">
	                	<div class="block-content">
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
									<div class="mb-3 d-flex">
										<div class="ml-auto">
											<button type="button" class="btn btn-primary" id="listAddBtn" onclick="javascript:searchmanagerUtils.goAnchor('#detail-content'); searchmanagerUtils.navShow('#add-tab');">글쓰기</button>
											<button type="button" class="btn btn-danger" onclick="javascript:userManagement.deleteBtn();">삭제</button>
										</div>
									</div>
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
			                	<nav aria-label="Page navigation">
					        		<ul class="pagination"><li class='page-item disabled'><a class='page-link'><i class='fa fa-angle-left'></i></a></li> 
										<li class='page-item active'><a href='javascript:;' class='page-link'>1</a></li> 
										<li class='page-item disabled'><a href='javascript:;' class='page-link'><i class='fa fa-angle-right'></i></a></li>
									</ul>
					        	</nav>
			                	<hr>
			                </div>
		                </div>
	                </div>
                </div>
                <!-- END Page Content -->
<!-- footer include -->                
<%@include file="../include/footer.jsp" %>