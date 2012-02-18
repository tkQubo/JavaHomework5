package com.qubo.caea05.servlets;


/**
 * メニュー画面用のサーブレット
 * @author Qubo
 */
public class MenuServlet extends AuthenticationServlet {
	/** シリアルバージョンID */
	private static final long serialVersionUID = 7658586701641072762L;
	/** 遷移先のページ */
	public static final String PAGE = "WEB-INF/menu.jsp";
	public MenuServlet() { super(PAGE); }
}
