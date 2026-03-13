package servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.Player;
import model.Pergunta;

import java.io.IOException;

@WebServlet("/answer")
public class AnswerServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		HttpSession session = req.getSession();

		Pergunta pergunta = (Pergunta) session.getAttribute("pergunta");

// evita erro se pergunta não existir
		if (pergunta == null) {
			resp.sendRedirect("game.jsp");
			return;
		}

		String respUserStr = req.getParameter("resp");

		if (respUserStr == null) {

			session.setAttribute("msg", "Escolha uma opção");

			resp.sendRedirect("resultado.jsp");
			return;
		}

		int respUser = Integer.parseInt(respUserStr);

		int turn = (int) session.getAttribute("turn");

		Player player = (turn == 1) ? (Player) session.getAttribute("p1") : (Player) session.getAttribute("p2");

		String msg;

		if (respUser == pergunta.getResposta()) {

			player.addPontos(10);
			msg = "Acertou! +10 pontos 🎉";

		} else {

			player.voltar(2);
			msg = "Errou! Voltou 2 casas 😬";

		}

// remove pergunta da sessão
		session.removeAttribute("pergunta");

// troca turno
		session.setAttribute("turn", turn == 1 ? 2 : 1);

// ✔ salva mensagem na sessão
		session.setAttribute("msg", msg);

// ✔ redirect sem parâmetros
		resp.sendRedirect("resultado.jsp");

	}
}