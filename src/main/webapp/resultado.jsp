<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Resultado</title>
<link rel="stylesheet" href="style.css">
</head>

<body>

<%
String msg = (String) session.getAttribute("msg");

if(msg == null){
    msg = "";
}

session.removeAttribute("msg");
%>

<h2><%=msg%></h2>

<a href="game.jsp">
<button>Continuar</button>
</a>

</body>
</html>