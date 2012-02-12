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
 * 更新画面用のサーブレット
 * @author Qubo
 */
public class UpdateServlet extends ValidationServlet implements ValidationSuccessListener {
	/** シリアルバージョンID */
	private static final long serialVersionUID = -4907996682710173961L;
	/** 入力ページ */
	public static final String INPUT_PAGE = "WEB-INF/update.jsp";
	/** 出力ページ */
	public static final String OUTPUT_PAGE = "WEB-INF/updateInput.jsp";

	private ValidationWithResult<User> userExistent = Validations.createUserExistentValidation(getUserDao());

	/** コンストラクタ */
	public UpdateServlet() {
		super(INPUT_PAGE, OUTPUT_PAGE, true);
	}

	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.servlets.ValidationServlet#getValidations()
	 */
	@Override
	protected Validation[] getValidations() {
		return new Validation[] {
				Validations.userIdRequired,
				Validations.userIdInteger,
				userExistent
		};
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.ValidationSuccessListener#onValidationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void onValidationSuccessed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = userExistent.getResult();
		request.setAttribute("user", user);
	}
}
