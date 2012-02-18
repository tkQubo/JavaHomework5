package com.qubo.caea05.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qubo.caea05.dao.EntityException;
import com.qubo.caea05.validations.ValidationSuccessListener;

/**
 * 削除確認画面用のサーブレット
 * @author Qubo
 */
public class DeleteConfirmServlet extends ValidationServlet implements ValidationSuccessListener {
	/** シリアルバージョンID */
	private static final long serialVersionUID = -7892990705515536272L;
	/** 出力ページ */
	public static final String OUTPUT_PAGE = "WEB-INF/deleteResult.jsp";

	/** コンストラクタ */
	public DeleteConfirmServlet() {
		super(null, OUTPUT_PAGE, false);
	}

	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.ValidationSuccessListener#onValidationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void onValidationSuccessed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int userid = Integer.parseInt(request.getParameter("userid"));
			getUserDao().delete(userid);
		} catch (EntityException e) {
			throw new ServletException(e);
		}
	}
}
