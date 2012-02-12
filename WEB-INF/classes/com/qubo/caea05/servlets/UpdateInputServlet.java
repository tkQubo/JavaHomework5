package com.qubo.caea05.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qubo.caea05.beans.User;
import com.qubo.caea05.validations.Validation;
import com.qubo.caea05.validations.ValidationFailListener;
import com.qubo.caea05.validations.ValidationSuccessListener;
import com.qubo.caea05.validations.ValidationWithResult;
import com.qubo.caea05.validations.Validations;

/**
 * 更新データ入力画面用のサーブレット
 * @author Qubo
 */
public class UpdateInputServlet extends ValidationServlet implements ValidationSuccessListener, ValidationFailListener {
	/** シリアルバージョンID */
	private static final long serialVersionUID = 8971638803264304985L;
	/** 入力ページ */
	public static final String INPUT_PAGE = "WEB-INF/updateInput.jsp";
	/** 出力ページ */
	public static final String OUTPUT_PAGE = "WEB-INF/updateConfirm.jsp";
	
	private final ValidationWithResult<User> userExistent = Validations.createUserExistentValidation(getUserDao());

	/** コンストラクタ */
	public UpdateInputServlet() {
		super(INPUT_PAGE, OUTPUT_PAGE, false);
	}

	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.servlets.ValidationServlet#getValidations()
	 */
	@Override
	protected Validation[] getValidations() {
		return new Validation[] {
			userExistent, // redundant?
			Validations.userNameRequired,
			Validations.passwordRequired,
			Validations.noChange,
		};
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.ValidationSuccessListener#onValidationSuccessed(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void onValidationSuccessed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("user", userExistent.getResult());
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.ValidationFailListener#onValidationFailed(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void onValidationFailed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("user", userExistent.getResult());
	}
}
