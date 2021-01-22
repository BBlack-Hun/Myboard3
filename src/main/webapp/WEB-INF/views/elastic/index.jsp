<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../include/header.jsp" %>

<!-- Page Header -->
<div class="content bg-gray-lighter">
	<div class="row items-push">
		<div class="col-sm-7">
			<h1 class="page-heading">
				엘라스틱 <small>Search</small>
			</h1>
		</div>
		<div class="col-sm-5 text-right hidden-xs">
			<ol class="breadcrumb push-10-t">
				<li>게시판</li>
				<li><a class="link-effect" href="index">엘라스틱서치</a></li>
			</ol>
		</div>
	</div>
</div>
<!-- END Page Header -->

<!-- Page Content -->
<div class="content">
	<div class="block block-rounded">
		<div class="block-content">
			<div class="table-responsive">
				<form method="GET">
					<label>검색하고 싶은 것을 입력하세요 ㅎㅎ 어처피 지금은 안됌 ㅎㅎ</label>
					<input type="text" name="str">
					<button type="summit">검색^^</button>
				</form>
				<table class="table table-sm table-vcenter">
					<thead>
						<tr>
							<th class="text-center" style="width:50px;"><a href="javascript:void(0)" class="all-check">#</a></th>
							<th style="width: 10%">통장 번호</th>
							<th class="text-center" style="width: 35%">성</th>
							<th style="width: 15%">이름</th>
							<th style="width: 15%">잔고</th>
							<th style="width: 15%">이메일</th>
							<th style="width: 10%">사는 곳</th>
						</tr>
					</thead>
					<div class="mb-3 d-flex">
						<div class="ml-auto">
							<button type="button" class="btn btn-primary" id="listAddBtn" onclick="">글쓰기</button>
							<button type="button" class="btn btn-danger" onclick="">삭제</button>
						</div>
					</div>
					<tbody>
						<c:forEach items="${elastic}" var = "list">
							<tr>
								<td class="text-center">
									<div class="custom-control custom-ckeckbox mb-1">
										<input type="checkbox" class="custom-control-input" id="${list.index }" name="dict-checkbox">
										<label class="custom-control-label" for="id"></label>
									</div>
								</td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${list.account_number}" /></td>
								<td>
									<a href="read?no=${list.account_number}&page=${pageMaker.cri.page}&perPageNum${pageMaker.cri.perPageNum}"><c:out value="${list.firstname}" /></a>
								</td>
								<td><c:out value="${list.lastname}" /></td>
								<td><c:out value="${list.balance}"  /></td>
								<td><c:out value="${list.email}"  /></td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${list.city}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<hr>
			</div>
		</div>
	</div>
</div>
<!-- END Page Content -->
<!-- Script -->

<!-- footer include -->                
<%@include file="../include/footer.jsp" %>