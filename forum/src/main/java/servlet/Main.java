package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetMutterListLogic;
import model.Mutter;
import model.PostMutterLogic;
import model.User;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter> mutterList = getMutterListLogic.execute();
		request.setAttribute("mutterList", mutterList);
			//セッションスコープからユーザー情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User)session.getAttribute("loginUser");
			
			//ユーザー情報が取得できる(ログインしている)ならメイン画面を表示
			if(loginUser != null) {
				RequestDispatcher  dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
				dispatcher.forward(request, response);
			}else {
				response.sendRedirect("/forum/");
			}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String text = request.getParameter("text");
		String words =request.getParameter("words");
		String flag = request.getParameter("flag");
		GetMutterListLogic gmll = new GetMutterListLogic();
		List<Mutter> mutterList = null;
		PostMutterLogic pml= null;
		Mutter mutter = null;
		//フォームから送られてきたフラグ番号で分岐させる
		switch(flag) {
		case "1": //入力
			if(text.length() == 0) {
				request.setAttribute("errorMsg", "文字が入力されていません");
				mutterList = gmll.execute();
				request.setAttribute("mutterList", mutterList);
			}else {
				HttpSession session = request.getSession();
				User loginUser = (User)session.getAttribute("loginUser");
				mutter = new Mutter(loginUser.getName(), text);
				pml = new PostMutterLogic();
				pml.incertExecute(mutter);
				mutterList = gmll.execute();
				}
			break;
		
		case "2"://更新
			mutterList = gmll.execute();
			break;
		
		case "3"://削除
			mutter = new Mutter(id);
			pml = new PostMutterLogic();
			pml.deleteExcecute(mutter);
			mutterList = gmll.execute();
			break;
			
		case "4"://検索
			if (words.length() != 0) {
				mutter = new Mutter(words, words);
				pml = new PostMutterLogic();
				mutterList = pml.serchExcecute(mutter);
			}else {
				mutterList = gmll.execute();
			}
			break;
		
		default://それ以外
			mutterList = gmll.execute();
			
		}

		request.setAttribute("mutterList", mutterList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
			
	}

}
