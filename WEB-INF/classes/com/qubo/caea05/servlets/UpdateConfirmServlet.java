package com.qubo.caea05.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qubo.caea05.beans.User;
import com.qubo.caea05.dao.EntityException;
import com.qubo.caea05.validations.Validation;
import com.qubo.caea05.validations.ValidationSuccessListener;
import com.qubo.caea05.validations.ValidationWithResult;
import com.qubo.caea05.validations.Validations;

/**
 * 更新確認画面用のサーブレット
 * @author Qubo
 */
public class UpdateConfirmServlet extends ValidationServlet implements ValidationSuccessListener {
	/** シリアルバージョンID */
	private static final long serialVersionUID = -7505751969798992377L;
	/** 入力ページ */
	public static final String INPUT_PAGE = "WEB-INF/updateConfirm.jsp";
	/** 出力ページ */
	public static final String OUTPUT_PAGE = "WEB-INF/updateResult.jsp";

	private final ValidationWithResult<User> userExistent = Validations.createUserExistentValidation(getUserDao());

	/** コンストラクタ */
	public UpdateConfirmServlet() {
		super(INPUT_PAGE, OUTPUT_PAGE, false);
	}

	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.servlets.ValidationServlet#getValidations()
	 */
	@Override
	protected Validation[] getValidations() {
		return new Validation[] {
			userExistent,
			Validations.passwordConfirmRequired,
			Validations.passwordMatch,
		};
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.ValidationSuccessListener#onValidationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void onValidationSuccessed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = userExistent.getResult();
			user.setUserName(request.getParameter("username"));
			user.setTel(request.getParameter("tel"));
			user.setPass(request.getParameter("pass"));
			getUserDao().update(user);
		} catch (EntityException e) {
			throw new ServletException(e);
		}
	}
}
