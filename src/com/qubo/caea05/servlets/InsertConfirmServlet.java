package com.qubo.caea05.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qubo.caea05.beans.User;
import com.qubo.caea05.dao.EntityException;
import com.qubo.caea05.validations.Validation;
import com.qubo.caea05.validations.ValidationSuccessListener;
import com.qubo.caea05.validations.Validations;

/**
 * 新規登録確認画面用のサーブレット
 * @author Qubo
 */
public class InsertConfirmServlet extends ValidationServlet implements ValidationSuccessListener {
	/** シリアルバージョンID */
	private static final long serialVersionUID = -2880508922753944141L;
	/** 入力ページ */
	public static final String INPUT_PAGE = "WEB-INF/insertConfirm.jsp";
	/** 出力ページ */
	public static final String OUTPUT_PAGE = "WEB-INF/insertResult.jsp";

	/** コンストラクタ */
	public InsertConfirmServlet() {
		super(INPUT_PAGE, OUTPUT_PAGE, false);
	}

	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.servlets.ValidationServlet#getValidations()
	 */
	@Override
	protected Validation[] getValidations() {
		return new Validation[] { Validations.passwordConfirmRequired, Validations.passwordMatch };
	}

	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.ValidationSuccessListener#onValidationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void onValidationSuccessed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = new User();
			user.setUserName(request.getParameter("username"));
			user.setTel(request.getParameter("tel"));
			user.setPass(request.getParameter("pass"));
			getUserDao().insert(user);
		} catch (EntityException e) {
			throw new ServletException(e);
		}
	}
}
