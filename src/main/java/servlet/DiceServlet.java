package servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.Player;
import model.Pergunta;
import data.BancoPerguntas;

import java.io.IOException;
import java.util.Random;

@WebServlet("/roll")
public class DiceServlet extends HttpServlet {

	private static final int FINAL = 99;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		HttpSession session = req.getSession();

		int turn = (int) session.getAttribute("turn");

		Player player = (turn == 1) ? (Player) session.getAttribute("p1") : (Player) session.getAttribute("p2");

		int dice = new Random().nextInt(6) + 1;

		player.mover(dice);

		session.setAttribute("dice", dice);

// 🏆 vitória
		if (player.getPosicao() >= FINAL) {

			session.setAttribute("msg", player.getNome() + " venceu o jogo! 🏆");

			resp.sendRedirect("resultado.jsp");
			return;
		}

		int pos = player.getPosicao();

		// ❓ PERGUNTAS
		if (pos == 5 || pos == 15 || pos == 25 || pos == 35 || pos == 45 || pos == 60 || pos == 75) {

			Pergunta pergunta = BancoPerguntas.getAleatoria();

			session.setAttribute("pergunta", pergunta);

			resp.sendRedirect("pergunta.jsp");
			return;
		}

		// ⚠️ PENALIDADE
		else if (pos == 14 || pos == 33 || pos == 52 || pos == 70) {

			player.voltar(3);

			session.setAttribute("msg", "Falha de rede! Volte 3 casas");

			changeTurn(session, turn);

			resp.sendRedirect("resultado.jsp");
			return;
		}

		// 🚀 BONUS
		else if (pos == 9 || pos == 28 || pos == 47 || pos == 66) {

			player.mover(2);

			session.setAttribute("msg", "Cloud boost! Avance 2 casas");

			changeTurn(session, turn);

			resp.sendRedirect("resultado.jsp");
			return;
		}

		// ⏸ PERDE TURNO
		else if (pos == 20 || pos == 40 || pos == 80) {

			session.setAttribute("msg", "Perdeu a vez!");

			changeTurn(session, turn);

			resp.sendRedirect("resultado.jsp");
			return;
		}

// turno normal
		changeTurn(session, turn);

		resp.sendRedirect("game.jsp");

	}

	private void changeTurn(HttpSession session, int turn) {

		session.setAttribute("turn", turn == 1 ? 2 : 1);

	}

}