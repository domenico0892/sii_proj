<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" />
<title>New Task</title>
</head>
<body>
	<div>
		<form action="task" method="get">
			<table>
				<tr>
					<td>URL</td>
					<td><input type="text" name="url"></td>
				<tr>
					<td>Keywords separate da spazi (es. "roma totti rete")</td>
					<td><input type="text" name="keyword">
					<td>
				<tr>
					<td align="center"><input type="submit" name="sumbit"
						value="invia" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div>
		Stato: ${stato}
	</div>
</body>
</html>
