package servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Player;

import java.io.IOException;

@WebServlet("/start")
public class StartGameServlet extends HttpServlet {

protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

HttpSession session = req.getSession();

session.setAttribute("p1", new Player("Jogador 1"));
session.setAttribute("p2", new Player("Jogador 2"));

session.setAttribute("turn", 1);
session.setAttribute("dice", null);

resp.sendRedirect("game.jsp");

}
}