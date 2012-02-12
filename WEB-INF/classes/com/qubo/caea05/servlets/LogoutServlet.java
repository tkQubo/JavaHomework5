package com.qubo.caea05.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ログアウト用のサーブレット
 * @author Qubo
 */
public class LogoutServlet extends AuthenticationServlet {
	/** シリアルバージョンID */
	private static final long serialVersionUID = -414981709111380292L;
	/** 遷移先のページ */
	public static final String PAGE = "WEB-INF/login.jsp";
//	private static final String PAGE = "WEB-INF/logout.jsp";
	public LogoutServlet() { super(PAGE); }
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.servlets.AuthenticationServlet#doGetAuthenticated(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGetAuthenticated(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		super.doGetAuthenticated(request, response);
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.servlets.AuthenticationServlet#doPostAuthenticated(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPostAuthenticated(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGetAuthenticated(request, response);
	}
}
