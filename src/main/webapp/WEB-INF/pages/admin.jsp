<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员页面</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
</head>
<body>
   admin <a href="${pageContext.request.contextPath}/logout">注销</a>
   <a href="${pageContext.request.contextPath}/admin/adminFrist">admin</a>
   <a href="${pageContext.request.contextPath}/dept/deptFrist">dept</a>
   <a href="${pageContext.request.contextPath}/user/userFrist">user</a>
   
   <c:forEach var="dept" items="${departments }">
      ${dept.id }
      ${dept.name}
   </c:forEach>
</body>
</html>