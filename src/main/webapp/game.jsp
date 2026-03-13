<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.Player" %>

<%
Player p1 = (Player) session.getAttribute("p1");
Player p2 = (Player) session.getAttribute("p2");

if(p1 == null || p2 == null){
    response.sendRedirect("index.jsp");
    return;
}

Integer turn = (Integer) session.getAttribute("turn");
Integer dice = (Integer) session.getAttribute("dice");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tabuleiro</title>
<link rel="stylesheet" href="style.css">
</head>

<body>

<h2>Turno do Jogador <%=turn%></h2>

<form action="roll" method="post">
<button>Lançar Dado 🎲</button>
</form>

<% if(dice != null){ %>
<h3>Dado: <%=dice%></h3>
<% } %>

<div class="board">

<%
for(int i=0;i<100;i++){
%>

<div class="cell">

<%=i%>

<% if(p1.getPosicao()==i){ %>
<div class="player1">🔴</div>
<% } %>

<% if(p2.getPosicao()==i){ %>
<div class="player2">🔵</div>
<% } %>

</div>

<%
}
%>

</div>

<div class="score">

<h3>Pontuação</h3>

Jogador 1: <%=p1.getPontos()%> pts<br>
Jogador 2: <%=p2.getPontos()%> pts

</div>

</body>
</html>