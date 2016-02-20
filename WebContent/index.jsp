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
					<td>Sito (es. www.romatoday.it)</td>
					<td><input type="text" name="url"></td>
				<tr>
					<td>Keywords</td>
					<td><input type="text" name="keyword">
					<td>
				<tr>
					<td align="center"><input type="submit" name="sumbit"
						value="invia" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div>Stato: ${stato}</div>
	<br>
	<div>
		<h3>Istruzioni:</h3>
		<ul>
			<li>Le keywords devono separate da spazi (es. "roma totti rete")</li>
			<li>Nella pagina di anteprima la terminazione avviene premendo
				INVIO o cliccando HO FINITO</li>
			<li>La selezione dei Content Block avviene con una dot notation
				"tipo.campo", oppure solo con il tipo</li>
		</ul>
	</div>
</body>
</html>
