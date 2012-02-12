package com.qubo.caea05.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qubo.caea05.beans.User;
import com.qubo.caea05.dao.Dao;
import com.qubo.caea05.dao.DaoFactory;
import com.qubo.caea05.validations.Validation;
import com.qubo.caea05.validations.ValidationSuccessListener;
import com.qubo.caea05.validations.ValidationWithResult;
import com.qubo.caea05.validations.Validations;
import com.qubo.caea05.validations.Validator;

/**
 * ログイン画面用のサーブレット
 * @author Qubo
 */
public final class LoginServlet extends HttpServlet implements ValidationSuccessListener {
	/** シリアルバージョンID */
	private static final long serialVersionUID = -3840742599004487884L;
	/** 入力ページ */
	public static final String PAGE_INPUT = "WEB-INF/login.jsp";
	/** 出力ページ */
	public static final String PAGE_OUTPUT = "WEB-INF/menu.jsp";
	private static final Dao<User> userDao = DaoFactory.defaultFactory.getUserDao();

	private final ValidationWithResult<User> authenticationValidation = Validations.createAuthenticationValidation(userDao);
	private final Validation[] validations = new Validation[] {
			Validations.userIdRequired, Validations.userIdInteger, Validations.passwordRequired, authenticationValidation,
	};

	/*
	 * (非 Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(PAGE_INPUT).forward(request, response);
	}

	/*
	 * (非 Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Validator validator = new Validator(PAGE_INPUT, PAGE_OUTPUT, validations);
		validator.setValidationSuccessListener(this);
		validator.validateAndForward(request, response);
	}

	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.ValidationSuccessListener#onValidationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void onValidationSuccessed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = authenticationValidation.getResult();
		request.getSession().setAttribute("user", user);
	}
}
