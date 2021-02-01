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
				<form  class="search-container" method="get">
					<c:choose>
						<c:when test="${index.str ne '' }">
							<label>JTBC</label>
							<input type="text" name="search" id="search-bar" value="${index.str}" placeholder="검색어를 입력하세요.">
							<input type="hidden" name="Category" id="search-bar" value="JTBC">
							<button type="submit">검색</button>
							<button type="button">상세검색</button>
							<div>
								<c:choose>
									<c:when test="${index.on ne null}">
										<label>
											<input type="checkbox" id="re" name="re" class="input_hide" checked><span> 결과 내 재검색</span>
										</label>
									</c:when>
									<c:when test="${index.on eq null}">
										<label>
											<input type="checkbox" id="re" name="re" class="input_hide"><span> 결과 내 재검색</span>
										</label>
									</c:when>
								</c:choose>
							</div>
							<c:if test="${index.elastic.total > 0 }">
								<span class="lead">"${index.str}"에 대한 검색 결과 <strong>(총<c:out value="${index.elastic.total}"/>건)</strong></span>
							</c:if>
						</c:when>
						<c:otherwise>
							<label>JTBC</label>
							<input type="text" name="search" id="search-bar" placeholder="검색어를 입력하세요.">
							<input type="hidden" name="Category" id="search-bar" value="JTBC">
							<button type="submit">검색</button>
							<button type="button">상세검색</button>
							<div>
								<c:choose>
									<c:when test="${re != index.on}">
										<label>
											<input type="checkbox" id="re" name="re" class="input_hide" checked><span> 결과 내 재검색</span>
										</label>
									</c:when>
									<c:otherwise>
										<label>
											<input type="checkbox" id="re" name="re" class="input_hide"><span> 결과 내 재검색</span>
										</label>
									</c:otherwise>
								</c:choose>
							</div>
							<c:if test="${index.jlen > 0 }">
								<span class="lead">"${index.str}"에 대한 검색 결과 <strong>(총<c:out value="${index.elastic.total}"/>건)</strong></span>
							</c:if>
						</c:otherwise>
					</c:choose>
				</form>
				<c:choose>
					<c:when test="${index.elastic.total > 0}">
						<div>
							<div>
								<strong class="lead">JTBC</strong>
								<c:forEach items="${index.elastic.JTBC}" var="jtbc">
									<div>
										<hr />
										<label for="title" class="col-sm-1 control-label">기사번호</label>
										<p><c:out value="${jtbc.no}" /></p>
										<label for="title" class="col-sm-1 control-label">제목</label>
										<p>
											<a href="read?no=${jtbc.no}&page=${index.pageMaker.cri.page}&perPageNum${index.pageMaker.cri.perPageNum}">${jtbc.violt_cas_nm}</a>
										</p>
										<label for="title" class="col-sm-1 control-label">내용</label>
										<p style="overflow:hidden; white-space : nowrap; text-overflow: ellipsis;">${jtbc.violt_cas_cn}</p>
										<label for="title" class="col-sm-1 control-label">게시날짜</label>
<%-- 										<p><fmt:formatDate value="${jtbc.date}" pattern="yyyy-MM-dd"/></p> --%>
<%-- 										<fmt:parseDate value="${jtbc.date}" var="date" pattern="yyyyMMdd"/> --%>
											<p><c:out value="${jtbc.date}" /></p>
									</div>
								</c:forEach>
							<hr />
							</div>
							<c:if test="${index.pageMaker.totalDataCount > index.pageMaker.cri.perPageNum }">
								<div class="text-center">
									<nav aria-label="pagination">
										<ul class="pagination">
											<!-- prev 버튼 -->
											<li id="page-prev">
												<a href="index?search=${index.str}&Category=${index.Category}&page=${index.pageMaker.startPage-1}&perPageNum=${index.pageMaker.cri.perPageNum}" aria-label="Prev"><span class="aria-hidden="true"><<</span></a>
											</li>
											<c:forEach begin="${index.pageMaker.startPage}" end="${index.pageMaker.endPage }" var="idx">
												<li id="page${idx}">
<%-- 													<a class="page-item active" href="index?search=${index.str}&Category=${index.Category}${index.pageMaker.makeQuery(idx)}"><span>${idx}<span class="sr-only"></span></span></a> --%>
													<a class="page-item active" href="index?search=${index.str}&Category=${index.Category}&page=${idx}&perPageNum=${index.pageMaker.cri.perPageNum}"><span>${idx}<span class="sr-only"></span></span></a>
												</li>
											</c:forEach>
												<!-- next 버튼 -->
											<li id="page-next">
												<a href="index?search=${index.str}&Category=${index.Category}&page=${index.pageMaker.endPage+1}&perPageNum=${index.pageMaker.cri.perPageNum}" aria-label="Next"><span class="aria-hidden="true">>></span></a>
											</li>
										</ul>
									</nav>
								</div>
							</c:if>
						</div>
					</c:when>
					<c:when test="${!empty index.str and index.len eq 0}">
						<div class="subContSec">
							<div class="searchResult">
								<strong><c:out value="${index.str }"/>에 대한 검색결과가 없습니다.</strong>
								<div class="txtBox">
									<p>· 단어의 철자가 정확한지 확인해보세요.</p>
									<p>· 검색어가 바르게 입력 되었는지 확인해 보세요</p>
									<p>· 비슷한 단어로 다시 검색해보세요.</p>
									<p>· 검색 옵션을 변경해서 다시 검색해 보세요.(미구현)</p>
									<a href="index" class="btn_ty3">이전 페이지 돌아가기</a>
								</div>
							</div>
						</div>
					</c:when>
					<c:when test="${index.str eq '' }">
						<div class="subContSec">
							<div class="searchResult">
								<strong>검색어를 입력해주세요</strong>
								<div class="txtBox">
									<p>· 찾고자하는 검색어를 입력하시고, 검색버튼을 눌러주세요.</p>
									<a href="index" class="btn_ty3">이전 페이지 돌아가기</a>
								</div>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="subContSec">
							<div class="searchResult">
								<strong>검색어를 입력해주세요.</strong>
								<div class="txtBox">
									<p>찾고자하는 검색어를 입력하시고, 검색버튼을 눌러주세요.</p>
								</div>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>
<!-- END Page Content -->
<!-- Script -->
<script>
	// 입력받은 카테고리별 항목으로 이동하기
	function fn_contentView(){
		// 고정시킬 url 주소
		var url = "${pageContext.request.contextPath}/elastic/index?search='${index.str}'";
		// 파라미터로 입력받은 카테로리 추가
		url = url + "&Category="+ MBC;
		// 이동
		location.href = url;
	}
	
	$(function(){
		//perPageNum select 박스 설정
		setPerPageNumSelect();
				
		//prev 버튼 활성화, 비활성화 처리
		var canPrev = '${index.pageMaker.prev}';
		if(canPrev !== 'true'){
			$('#page-prev').addClass('disabled');
		}
		
		//next 버튼 활성화, 비활성화 처리
		var canNext = '${index.pageMaker.next}';
		if(canNext !== 'true'){
			$('#page-next').addClass('disabled');
		}
		
		//현재 페이지 파란색으로 활성화
		var thisPage = '${index.pageMaker.cri.page}';
		//매번 refresh 되므로 다른 페이지 removeClass 할 필요는 없음->Ajax 이용시엔 해야함
		$('#page'+thisPage).addClass('active');
	})
	
	function setPerPageNumSelect(){
		var perPageNum = "${index.pageMaker.cri.perPageNum}";
		var $perPageSel = $('#perPageSel');
		var thisPage = '${index.pageMaker.cri.page}';
		$perPageSel.val(perPageNum).prop("selected",true);
		//PerPageNum가 바뀌면 링크 이동
		$perPageSel.on('change',function(){
			//pageMarker.makeQuery 사용 못하는 이유: makeQuery는 page만을 매개변수로 받기에 변경된 perPageNum을 반영못함
			window.location.href = "&page="+thisPage+"&perPageNum="+$perPageSel.val();
		})
	}
	
	
</script>
<!-- footer include -->                
<%@include file="../include/footer.jsp" %>