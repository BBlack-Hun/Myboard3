<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../include/header.jsp" %>



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
<div class="content">
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
							<button type="button" class="btn btn-primary" id="listAddBtn" onclick="">글쓰기</button>
							<button type="button" class="btn btn-danger" onclick="">삭제</button>
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
									<a href="read?no=${list.no}&page=${pageMaker.cri.page}&perPageNum${pageMaker.cri.perPageNum}"><c:out value="${list.title}" /></a>
								</td>
								<td><c:out value="${list.writer}" /></td>
								<td><fmt:formatDate value="${list.regDt}" pattern="yyyy-MM-dd"/></td>
								<td><fmt:formatDate value="${list.modDt}" pattern="yyyy-MM-dd"/></td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${list.hit}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 페이지가 1페이지 미만일때는 페이징처리 안함.. -->
				<c:if test="${pageMaker.totalDataCount > pageMaker.cri.perPageNum }">
					<div class="text-center">
						<nav aria-label="pagination">
							<ul class="pagination">
								<!-- prev 버튼 -->
								<li id="page-prev">
									<a href="${pageMaker.makeQuery(pageMaker.startPage-1)}" aria-label="Prev"><span class="aria-hidden="true"><<</span></a>
								</li>
								<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage }" var="idx">
									<li id="page${idx}">
										<a class="page-item active" href="index${pageMaker.makeQuery(idx)}"><span>${idx}<span class="sr-only"></span></span></a>
									</li>
								</c:forEach>
									<!-- next 버튼 -->
								<li id="page-next">
									<a href="index${pageMaker.makeQuery(pageMaker.endPage+1)}" aria-label="Next"><span class="aria-hidden="true">>></span></a>
								</li>
							</ul>
						</nav>
					</div>
				</c:if>
				<hr>
			</div>
		</div>
	</div>
</div>
<!-- END Page Content -->
<!-- Script -->
<script>
	$(function(){
		//perPageNum select 박스 설정
		setPerPageNumSelect();
		
		//등록, 삭제 후 문구 처리
		var result = '${result}';
		$(function(){
			if(result === 'registerOK'){
				$('#registerOK').removeClass('hidden');
				$('#registerOK').fadeOut(2000);
			}
			if(result === 'removeOK'){
				$('#removeOK').removeClass('hidden');
				$('#removeOK').fadeOut(2000);
			}
		})
		
		//prev 버튼 활성화, 비활성화 처리
		var canPrev = '${pageMaker.prev}';
		if(canPrev !== 'true'){
			$('#page-prev').addClass('disabled');
		}
		
		//next 버튼 활성화, 비활성화 처리
		var canNext = '${pageMaker.next}';
		if(canNext !== 'true'){
			$('#page-next').addClass('disabled');
		}
		
		//현재 페이지 파란색으로 활성화
		var thisPage = '${pageMaker.cri.page}';
		//매번 refresh 되므로 다른 페이지 removeClass 할 필요는 없음->Ajax 이용시엔 해야함
		$('#page'+thisPage).addClass('active');
	})
	
	function setPerPageNumSelect(){
		var perPageNum = "${pageMaker.cri.perPageNum}";
		var $perPageSel = $('#perPageSel');
		var thisPage = '${pageMaker.cri.page}';
		$perPageSel.val(perPageNum).prop("selected",true);
		//PerPageNum가 바뀌면 링크 이동
		$perPageSel.on('change',function(){
			//pageMarker.makeQuery 사용 못하는 이유: makeQuery는 page만을 매개변수로 받기에 변경된 perPageNum을 반영못함
			window.location.href = "listPage?page="+thisPage+"&perPageNum="+$perPageSel.val();
		})
	}
</script>
<!-- footer include -->                
<%@include file="../include/footer.jsp" %>