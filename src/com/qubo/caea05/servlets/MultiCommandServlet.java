package com.qubo.caea05.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qubo.Utils;
import com.qubo.caea05.beans.User;
import com.qubo.caea05.validations.ValidationMessage;
import com.qubo.caea05.validations.ValidationSuccessListener;
import com.qubo.caea05.validations.ValidationWithResult;
import com.qubo.caea05.validations.Validations;
import com.qubo.caea05.validations.Validator;

/**
 * 一覧表示画面から、ユーザーデータの追加・更新・削除を行うための遷移用サーブレット
 * @author Qubo
 */
public class MultiCommandServlet extends AuthenticationServlet implements ValidationSuccessListener {
	/** シリアルバージョンID */
	private static final long serialVersionUID = -9156175517893498381L;
	private static final String INPUT_PAGE = "WEB-INF/selectResult.jsp";
	private static final String INPUT_PAGE_UPDATE = "WEB-INF/update.jsp";
	private static final String INPUT_PAGE_DELETE = "WEB-INF/delete.jsp";
	private static final String OUTPUT_PAGE_UPDATE = "WEB-INF/updateInput.jsp";
	private static final String OUTPUT_PAGE_DELETE = "WEB-INF/deleteConfirm.jsp";

	private final ValidationWithResult<User> userExistent = Validations.createUserExistentValidation(getUserDao());

	/** コンストラクタ */
	public MultiCommandServlet() {
		super(INPUT_PAGE);
	}

	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.servlets.AuthenticationServlet#doPostAuthenticated(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPostAuthenticated(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (Utils.isNotBlank(request.getParameter("insert"))) { // Insert mode
			response.sendRedirect("insert");
		} else if (Utils.isNotBlank(request.getParameter("update"))) { // Update
																		// mode
			validateAndForward(request, response, INPUT_PAGE_UPDATE, OUTPUT_PAGE_UPDATE);
		} else if (Utils.isNotBlank(request.getParameter("delete"))) { // Delete
																		// mode
			validateAndForward(request, response, INPUT_PAGE_DELETE, OUTPUT_PAGE_DELETE);
		} else {
			ValidationMessage validationMessage = ValidationMessage.error("対応していないコマンドです！");
			request.setAttribute("validationMessage", validationMessage);
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
	}

	private void validateAndForward(HttpServletRequest request, HttpServletResponse response, String inputPage, String outputPage) throws ServletException, IOException {
		Validator validator = new Validator(inputPage, outputPage);
		validator.setValidationSuccessListener(this);
		validator.add(Validations.userIdRequired);
		validator.add(Validations.userIdInteger);
		validator.add(userExistent);
		validator.validateAndForward(request, response);
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.ValidationSuccessListener#onValidationSuccessed(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void onValidationSuccessed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("user", userExistent.getResult());
	}
}
