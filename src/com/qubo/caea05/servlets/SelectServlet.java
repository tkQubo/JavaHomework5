package com.qubo.caea05.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qubo.caea05.beans.User;
import com.qubo.caea05.validations.Validation;
import com.qubo.caea05.validations.ValidationSuccessListener;
import com.qubo.caea05.validations.ValidationWithResult;
import com.qubo.caea05.validations.Validations;

/**
 * 一覧画面用のサーブレット
 * @author Qubo
 */
public class SelectServlet extends ValidationServlet implements ValidationSuccessListener {
	/** シリアルバージョンID */
	private static final long serialVersionUID = 215001640487022051L;
	/** 入力ページ */
	public static final String PAGE_INPUT = "WEB-INF/select.jsp";
	/** 出力ページ */
	public static final String PAGE_OUTPUT = "WEB-INF/selectResult.jsp";
	private final ValidationWithResult<Iterable<User>> hasQueryResult = Validations.createSelectQueryValidation(getUserDao());

	/** コンストラクタ */
	public SelectServlet() {
		super(PAGE_INPUT, PAGE_OUTPUT, true);
	}

	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.servlets.ValidationServlet#getValidations()
	 */
	@Override
	protected Validation[] getValidations() {
		return new Validation[] { Validations.userIdInteger, hasQueryResult };
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.ValidationSuccessListener#onValidationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void onValidationSuccessed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Iterable<User> users = hasQueryResult.getResult();
		request.setAttribute("users", users);
	}
}
