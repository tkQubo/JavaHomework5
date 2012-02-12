package com.qubo.caea05.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * エラー発生時に表示する画面用のサーブレット
 * @author Qubo
 */
public class ErrorServlet extends HttpServlet {
	/** シリアルバージョンID */
	private static final long serialVersionUID = 7379622618076627555L;
	/*
	 * (非 Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int code = Integer.parseInt(request.getServletPath().substring(6));
			System.out.println(code);
			String message = null;
			switch (code) {
			case 404:
				message = "指定したページは存在しません！";
				break;
			case 405:
				message = "指定したページに対して不正なアクセスが行われました！";
			default:
				break;
			}
			request.setAttribute("code", code);
			request.setAttribute("message", message);
			request.getRequestDispatcher("WEB-INF/errorWithCode.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			request.setAttribute("code", 404);
			request.setAttribute("message", "指定したページは存在しません！");
			request.getRequestDispatcher("WEB-INF/errorWithCode.jsp").forward(request, response);
		}
	}
//	/*
//	 * (非 Javadoc)
//	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
//	 */
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	}
}
