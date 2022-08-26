package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		int account_type;
		//データベースからアカウント情報判定する
		if (name == null) {
			User user = new User(user_id, password);
			LoginLogic loginLogic = new LoginLogic();
			User returnUser = loginLogic.excute(user);
			//trueならセッションスコープに保存
			if(returnUser != null) {
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", user);
			}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
		dispatcher.forward(request, response);
		}else {
			//新規登録
			account_type = Integer.parseInt(request.getParameter("account_type"));
			User user = new User(user_id, password, name, account_type);
			LoginLogic loginLogic = new LoginLogic();
			boolean result = loginLogic.registerCheck(user);
			request.setAttribute("result", result);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerResult.jsp");
			dispatcher.forward(request, response);
		}
	}

}
