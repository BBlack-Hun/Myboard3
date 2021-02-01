<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
				<div class="searchBar">
					<div class="searchFormWrap" id="autocomplateBox">
						<label>통합검색</label>
						<input style="font-size:12px;" type="text" placeholder="검색어를 입력하세요." id="search" name="serach" class="input_ty1"
						value ="${index.str}" onkeypress="javascript:search_enter();" autocomplete="off">
						<button type="button" class="search" onclick="javascript:search_btn();">검색</button>
						<button type="button" href="javascript:void(0);" class="search ty_detail pc_only" onclick="javascript:openDetailFilter(); return false;">상세검색</button>
					</div>
					<label>
						<input type="checkbox" id="re" name="re" value="true" class="input_hide"><span> 결과 내 재검색</span>
					</label>
				</div>
				<%-- 상세검색 --%>
				<div class="totalSearchDetailFilter" style="display: none;">
					<div>
						<div class="totalFilterCont">
							<div class="list_box">
								<div class="filter_list">
									<strong>검색단어</strong>
									<div>
										<input type="text" class="input_ty1" name="detailQuery" id="detailQuery" placeholder="검색어">
										<label class="ty_check input_custum"><input type="checkbox" name="orQuery" id="orQuery" value="" class="input_hide"><span class="fake_input"></span>입력한 단어가 하나 이상 포함된 문서검색(OR방식으로 검색)</label>
									</div>
								</div>
								<div class="filter_list">
									<strong>상세옵션</strong>
									<div>
										<p class="desc">* 여러개의 단어를 입력할 때는 쉼표(,)로 구분해서 입력하세요.</p>
										<!-- 
										<label class="ty_check input_custum"><input type="checkbox" id="" name="" value="" class="input_hide"><span class="fake_input"></span>정확히 일치하는 단어/문장</label>
										 -->
									</div>
								</div>
							</div>
							<div class="list_box">
								<div class="filter_list">
									<strong>정확하게 일치</strong>
									<div>
										<input type="text" class="input_ty_underline" name="exactQuery" id="exactQuery" placeholder="정확하게 일치하는 단어/문장(&quot;&quot;)">
									</div>
								</div>
								<div class="filter_list">
									<strong>포함하는 단어</strong>
									<div>
										<input type="text" class="input_ty_underline" name="includeQuery" id="includeQuery" placeholder="포함하는 단어를 입력하세요.">
									</div>
								</div>
								<div class="filter_list">
									<strong>제외하는 단어</strong>
									<div>
										<input type="text" class="input_ty_underline" name="excludeQuery" id="excludeQuery" placeholder="제외하는 단어를 입력하세요.">
									</div>
								</div>
							</div>
						</div>
						<div class="info-box">
							<p>* 기본검색의 결과 범위를 줄이고자 할 때 사용합니다.</p>
							<p>* 여러개의 단어를 입력하실 때는쉼표(,)로 구분해서 입력하세요.</p>
						</div>
						<div class="btn_box_ty_filter">
							<a href="javascript:void(0);" class="btn_submit static" id="search_btn_detail" onclick="javascript:search_btn_detail();"><span>검색</span></a>
							<a href="javascript:void(0);" class="btn_submit ty_enroll static" onclick="javascript:initValDetailFilter(this); return false;"><span>초기화</span></a>
							<a href="javascript:void(0);" class="btn_submit ty_black static" onclick="javascript:closeDetailFilter(this); return false;"><span>닫기</span></a>
						</div>
					</div>
				</div>
				<c:if test="${index.len > 0 }">
					<span class="lead">"${index.str}"에 대한 검색 결과 <strong>(총<c:out value="${index.len}"/>건)</strong></span>
				</c:if>
				<form action="index" method="get" id='form_search'>
					<input type="hidden" name="search">
					<input type="hidden" name="osearch">
					<input type="hidden" name="Category">
				</form>
				<!-- 값을 저장해 두기 위한 쿠키 필드.. 인거 같다. -->
				<textarea id="paramVO_search" style="display: none;">${index.str}</textarea>
				<textarea id="paramVO_osearch" style="display: none;">${index.ostr}</textarea>
				<c:choose>
					<c:when test="${index.len > 0}">
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
											<a href="read?no=${jtbc.no}&page=${pageMaker.cri.page}&perPageNum${pageMaker.cri.perPageNum}">${jtbc.violt_cas_nm}</a>
										</p>
										<label for="title" class="col-sm-1 control-label">내용</label>
										<p style="overflow:hidden; white-space : nowrap; text-overflow: ellipsis;">${jtbc.violt_cas_cn}</p>
										<label for="title" class="col-sm-1 control-label">게시날짜</label>
											<p><c:out value="${jtbc.date}" /></p>
									</div>
								</c:forEach>
								<c:if test="${index.elastic.stotal.item0 > 4 }">			
									<button type="button" onClick="searchCategory('JTBC')">JTBC 기사 더보기</button>
								</c:if>
							<hr />
							</div>
							<div>
								<strong class="lead">KBS</strong>
								<c:forEach items="${index.elastic.KBS}" var="kbs">
									<div>
										<hr />
										<label for="title" class="col-sm-1 control-label">번호</label>
										<p><c:out value="${kbs.no}" /></p>
										<label for="title" class="col-sm-1 control-label">제목</label>
										<p>
											<a href="read?no=${kbs.no}&page=${pageMaker.cri.page}&perPageNum${pageMaker.cri.perPageNum}">${kbs.violt_cas_nm}</a>
										</p>
										<label for="title" class="col-sm-1 control-label">내용</label>
										<p style="overflow:hidden; white-space : nowrap; text-overflow: ellipsis;">${kbs.violt_cas_cn}</p>
										<label for="title" class="col-sm-1 control-label">게시날짜</label>
										<p>${kbs.date}</p>
										
									</div>
								</c:forEach>
								<c:if test="${index.elastic.stotal.item1 > 4 }">
									<button type="button" onClick="searchCategory('KBS')">KBS 기사 더보기</button>
								</c:if>
							<hr />
							</div>
							<div>
								<strong class="lead">MBC</strong>
								<c:forEach items="${index.elastic.MBC}" var="mbc">
									<div>
										<hr />
										<label for="title" class="col-sm-1 control-label">번호</label>
										<p><c:out value="${mbc.no}" /></p>
										<label for="title" class="col-sm-1 control-label">제목</label>
										<p>
											<a href="read?no=${mbc.no}&page=${pageMaker.cri.page}&perPageNum${pageMaker.cri.perPageNum}">${mbc.violt_cas_nm}</a>
										</p>
										<label for="title" class="col-sm-1 control-label">내용</label>
										<p style="overflow:hidden; white-space : nowrap; text-overflow: ellipsis;">${mbc.violt_cas_cn}</p>
										<label for="title" class="col-sm-1 control-label">게시날짜</label>
										<p>${mbc.date}</p>
										
									</div>
								</c:forEach>
								<c:if test="${index.elastic.stotal.item2 > 4 }">
									<button type="button" onClick="searchCategory('MBC')">MBC 기사 더보기</button>
								</c:if>
							<hr />
							</div>
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
	// 검색어, 이전 검색어, 카테고리
	var search, osearch, Category;
	
	// 초기값
	osearch = document.getElementById('paramVO_osearch').value;
	
	// 입력받은 카테고리별 항목으로 이동하기
	function searchCategory(Category){
		// 고정시킬 url 주소
		var url = "${pageContext.request.contextPath}/elastic/index?search=${index.str}";
		// 파라미터로 입력받은 카테로리 추가
		url = url + "&Category="+ Category;
		// 이동
		location.href = url;
	}
	
	// 상세 검색 열기
	var openDetailFilter = function() {
		$('.totalSearchDetailFilter').show();
	}
	
	// 상세 검색 닫기
	var closeDetailFilter = function(target) {
		var target = $(target);
		$(target).parents('.totalSearchDetailFilter').hide();
	}
	
	// 엔터 검색
	var search_enter = function(){
		if(event.keyCode == 13){
			search_btn();
		}
	}
	
	// 버튼 검색
	var search_btn = function(){
		// 태그 네임이 search의 value를 가져온다.
		search = document.getElementById('search').value;	
		osearch = document.getElementById('paramVO_osearch').value;
		// setReKeep();  결과내 재검색 flag인데 오류 오짐...
		
		searchAll();
	}
	
	//검색
	var searchAll = function(){
		search_default();
	}
	
	// 일반 검색
	var search_default = function(){
		var form = document.getElementById('form_search');
		form.querySelector('input[name=search]').value = search;
		form.querySelector('input[name=osearch]').value = osearch;
		form.querySelector('input[name=Category]').value = "통합검색";
		form.submit();
	}
	
	// rekeep 셋팅
	var setReKeep = function(){
   		try {
   			re = document.getElementById('re').checked;
   		} catch (e) {
   			re = false;
   		}   		
   	}
	
</script>

<!-- footer include -->                
<%@include file="../include/footer.jsp" %>