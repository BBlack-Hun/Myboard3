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
<!-- 이곳에 게시판 넣기 -->
<div class="content d-none" id="detail-content">
	<div class="block block-rounded">
		<div class="block-content block-content-full block-content-narrow container">
			<hr />
				<section id="container">
				
					<form name="readForm" role="form" method="post">
						<input type="hidden" id="no" name="no" value="${read.no}"/>
						<input type="hidden" id="page" name="page" value="${pageMaker.cri.page}"> 
						<input type="hidden" id="perPageNum" name="perPageNum" value="${pageMaker.cri.perPageNum}"> 
					</form>
				
					<div class="form-group">
						<label for="title" class="col-sm-2 control-label">제목</label>
						<input type="text" id="title" name="title" class="form-control" value="${read.title}" readonly="readonly"/>
					</div>
					<div class="form-group">	
						<label for="content" class="col-sm-2 control-label">내용</label>
						<textarea id="content" name="content" class="form-control" readonly="readonly" ><c:out value="${read.content}" /></textarea>
					</div>
					<div class="form-group">
						<label for="writer" class="col-sm-2 control-label">작성자</label>
						<input type="text" id="writer" name="writer" value="${read.writer}" class="form-control" readonly="readonly" />
					</div>
					<div class="form-group">			
						<label for="regdate" class="col-sm-2 control-label">작성날짜</label>
						<fmt:formatDate value="${read.regDt}" pattern="yyyy-MM-dd a h:mm"/>
					</div>					
					<div class="form-group">
						<label for="regdate" class="col-sm-2 control-label">수정날짜</label>
						<fmt:formatDate value="${read.modDt}" pattern="yyyy-MM-dd a h:mm"/>					
					</div>
				</section>
			<br />
			<hr />
			<div class="mb-3 d-flex">
				<div class="ml-auto">
					<a href="update?no=${read.no}" class="btn btn-warning">수정</a>
					<button id="btn-remove" class="btn btn-danger">삭제</button>
					<a href="index" class="btn btn-primary">목록</a>
				</div>
			</div>
			
		</div>
	</div>
</div>
<!-- END Page Content -->
<!-- Script -->                
<script type="text/javascript">
	// 목록
	$(".list_btn").on("click", function(){
		location.href = "index";
	})
</script>

<%@include file="../include/footer.jsp" %>