package com.qubo.caea05.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qubo.caea05.Configuration;
import com.qubo.caea05.beans.User;
import com.qubo.caea05.dao.Dao;
import com.qubo.caea05.dao.DaoFactory;

/**
 * ユーザー認証の機能を実装したサーブレット抽象クラス。<br />
 * このクラスを継承したサーブレットクラスは、以下の機能を有する：
 * <ul>
 * <li>ユーザー認証が行われていない場合、{@link #LOGIN}ページに遷移する。</li>
 * <li>ユーザー認証済みの場合、コンストラクタで渡した{@code page}に遷移する。<br />
 * この挙動は、{@link #doGetAuthenticated(HttpServletRequest, HttpServletResponse)}や
 * {@link #doPostAuthenticated(HttpServletRequest, HttpServletResponse)}をオーバーライドすることにより
 * カスタマイズすることができる。</li>
 * </ul>
 * @author Qubo
 */
public abstract class AuthenticationServlet extends HttpServlet {
	/** 認証ページ */
	public static final String LOGIN = "login";
	/** シリアルバージョンID */
	private static final long serialVersionUID = 4928085407479968400L;
	private final Dao<User> userDao = DaoFactory.defaultFactory.getUserDao();
	private final String page;

	/**
	 * コンストラクタ
	 * @param page GETリクエスト時に表示するJSPページ
	 */
	public AuthenticationServlet(String page) {
		this.page = page;
	}

	/**
	 * {@link User}用の{@link Dao}インスタンスを取得する
	 * @return {@link User}用の{@link Dao}インスタンス
	 */
	protected final Dao<User> getUserDao() { return userDao; }
	/**
	 * GETリクエスト時に表示するJSPページを取得する
	 * @return GETリクエスト時に表示するJSPページ
	 */
	protected final String getPage() { return page; }

	/*
	 * (非 Javadoc)
	 *
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected final void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if (page != null) {
			checkDebug(request);
			Object user = request.getSession().getAttribute("user");
			if (user == null) {
				String redierct = request.getServletPath().substring(1);
				request.setAttribute("redirect", redierct);
				response.sendRedirect(LOGIN);
			} else {
				doGetAuthenticated(request, response);
			}
		} else {
			super.doGet(request, response);
		}
	}

	/**
	 * {@link Configuration#skipLogin}が{@code true}の時に、デバッグユーザーで自動ログインする。
	 * @param request {@link HttpServletRequest}インスタンス
	 */
	protected final void checkDebug(HttpServletRequest request) {
		if (Configuration.skipLogin) {
			User user = new User();
			user.setUserId(-1);
			user.setUserName("デバッグユーザー");
			user.setTel("000-0000-0000");
			user.setPass("pass");
			request.getSession().setAttribute("user", user);
		}
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		checkDebug(request);
		Object user = request.getSession().getAttribute("user");
		if (user == null) {
			String redierct = request.getServletPath().substring(1);
			request.setAttribute("redirect", redierct);
			response.sendRedirect(LOGIN);
		} else {
			doPostAuthenticated(request, response);
		}
	}
	/**
	 * GETリクエスト時にユーザー認証が成功した場合に呼び出されるメソッド
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGetAuthenticated(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(page).forward(request, response);
	}
	/**
	 * POSTリクエスト時にユーザー認証が成功した場合に呼び出されるメソッド
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPostAuthenticated(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(page).forward(request, response);
	}
}
