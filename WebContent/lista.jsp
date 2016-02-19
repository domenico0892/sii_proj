<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Scelta pagina</title>
</head>
<body>
	<h3>Scelta della pagina</h3>
	<form action="anteprimactrl" method="get">
		<c:forEach items="${pagine}" var="url">
			<input type="radio" name="url" value="${url}" checked>${url}<br>
		</c:forEach>
		<input type="submit" name="submit" value="Vai!">
	</form>
</body>
</html>