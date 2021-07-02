<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <a class="navbar-brand" href="/">INDEX</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
    <sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal.email" var="ses_email"/>
    <sec:authentication property="principal.nickname" var="ses_nick"/>
    <sec:authentication property="principal.auth" var="auth"/>
          <c:choose>
             <c:when test="${auth eq 'ADM' }">
                <li class="nav-item">
                    <a class="nav-link" href="/member/list">
                    ${ses_nick }(${ses_email })
                    </a>
                  </li>
             </c:when>
             <c:otherwise>
                <li class="nav-item">
                    <a class="nav-link" href="/member/modify?email=${ses_email }">
                    ${ses_nick }(${ses_email })
                    </a>
                  </li>
             </c:otherwise>
          </c:choose>
          <li class="nav-item">
              <a class="nav-link" href="" id="lg">로그아웃</a>
            </li>
            <form action="/member/logout" method="post" id="lgForm">
            	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
            </form>
            <script>
            	$("#lg").on("click", function(e) {
            		e.preventDefault();
            		$("#lgForm").submit();
            	});
            </script>
       </sec:authorize>
       <sec:authorize access="isAnonymous()">
       <li class="nav-item">
           <a class="nav-link" href="/member/register">회원가입</a>
         </li>
         <li class="nav-item">
           <a class="nav-link" href="/member/login">로그인</a>
         </li>
       </sec:authorize>
    <li class="nav-item">
           <a class="nav-link" href="/product/list">상품목록</a>
         </li>
    </ul>
  </div>  
</nav>

<div class="container" style="margin-top:30px">