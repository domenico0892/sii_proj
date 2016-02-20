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
		<div>
			<input type="submit" name="submit" value="Mi sento fortunato!">
		</div>
		<div>
			<c:forEach items="${pagine}" var="url">
				<p style="line-height: 25px;">
					<input type="radio" name="url" value="${url}" checked><a
						href="${url}" target="_blank">${url}</a>
				</p>
			</c:forEach>
		</div>
		<div>
			<input type="submit" name="submit" value="Vai!">
		</div>
	</form>
</body>
</html>