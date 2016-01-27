<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1" />
	<title>New Task</title>
</head>
<body>
	<form action="task" method="get">
	<div> **** </div>
	<div> Inserisci l'url della pagina da estrarre </div>
	<div> **** </div>
	<div>URL: <input type="text" name="url"></div>
	<div> **** </div>
	<div>Inserisci le parole separate da spazio, ad esempio: </div>
	<div>totti roma rete </div>
	<div> **** </div>
	<div>Keywords: <input type="text" name="keyword"></div>
	<div> **** </div>
	<div><input type="submit" name="sumbit" value="invia" /></div>
	</form>
</body>
</html>
