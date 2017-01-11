<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 자바스크립트와 css 파일 위치 -->
<!-- 기본 패키지 spring.mvc.board_mybatis의 3번째 이름이 context name이 된다. -->
<!-- value="/컨텍스트명/resources" -->
<!-- <c:set var="project" value="/board_mybatis/resources/board/" /> -->

<c:set var="project" value="/board_mybatis/resources/" />

<script type="text/javascript" src="${project}script.js"></script>

<link type="text/css" rel="stylesheet" href="${project}style.css">
