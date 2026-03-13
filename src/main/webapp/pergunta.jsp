<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.Pergunta" %>

<%
Pergunta p = (Pergunta) session.getAttribute("pergunta");

if(p == null){
    response.sendRedirect("game.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pergunta</title>
<link rel="stylesheet" href="style.css">
</head>

<body>

<div class="question-box">

<h2><%=p.getPergunta()%></h2>

<form action="answer" method="post">

<%
String[] op = p.getOpcoes();

for(int i=0;i<op.length;i++){
%>

<div class="option">
<input type="radio" name="resp" value="<%=i%>" required>
<%=op[i]%>
</div>

<%
}
%>

<br>

<button type="submit">Responder</button>

</form>

</div>

</body>
</html>