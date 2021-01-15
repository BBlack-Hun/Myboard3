<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>            
</main>

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