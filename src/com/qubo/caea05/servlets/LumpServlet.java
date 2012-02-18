package com.qubo.caea05.servlets;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qubo.Utils;
import com.qubo.caea05.beans.User;
import com.qubo.caea05.dao.EntityException;
import com.qubo.caea05.validations.RequiredValidation;
import com.qubo.caea05.validations.Validation;
import com.qubo.caea05.validations.ValidationMessage;
import com.qubo.caea05.validations.ValidationSuccessListener;
import com.qubo.caea05.validations.Validator;

/**
 * 一括変更画面用のサーブレット
 * @author Qubo
 */
public class LumpServlet extends ValidationServlet implements ValidationSuccessListener {
	/** シリアルバージョンID */
	private static final long serialVersionUID = 5483572263527574773L;
	/** 遷移先のページ */
	public static final String PAGE = "WEB-INF/lump.jsp";
	private List<User> users = null;
	private List<Integer> deleteIds = null;
	private List<Integer> updateIds = null;
	private boolean doInsert = false;

	/** コンストラクタ */
	public LumpServlet() {
		super(PAGE, PAGE, true);
	}

	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.servlets.AuthenticationServlet#doGetAuthenticated(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGetAuthenticated(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		users = null;
		try {
			users = getUserDao().selectAll();
			request.setAttribute("users", users);
			super.doGetAuthenticated(request, response);
		} catch (EntityException e) {
			ValidationMessage validationMessage = ValidationMessage.fail(e.getMessage());
			request.setAttribute("validationMessage", validationMessage);
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.servlets.ValidationServlet#doPostAuthenticated(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPostAuthenticated(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		users = null;
		try {
			users = getUserDao().selectAll();
			request.setAttribute("users", users);
			validateAndForward(request, response);
		} catch (EntityException e) {
			ValidationMessage validationMessage = ValidationMessage.fail(e.getMessage());
			request.setAttribute("validationMessage", validationMessage);
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
	}

	private void validateAndForward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		deleteIds = new ArrayList<Integer>();
		updateIds = new ArrayList<Integer>();
		doInsert = false;

		Validator validator = new Validator(PAGE, PAGE);
		validator.setValidationSuccessListener(this);

		for (User user : users) {
			String userNameParameterName = MessageFormat.format("username{0}", user.getUserId());
			String passParameterName = MessageFormat.format("pass{0}", user.getUserId());

			String userName = request.getParameter(userNameParameterName);
			String tel = request.getParameter(MessageFormat.format("tel{0}", user.getUserId()));
			String pass = request.getParameter(passParameterName);
			boolean shouldDelete = request.getParameter(MessageFormat.format("delete{0}", user.getUserId())) != null;
			if (shouldDelete) {
				deleteIds.add(user.getUserId());
			} else {
				if (!Utils.equals(userName, user.getUserName()) || !Utils.equals(tel, user.getTel()) || !Utils.equals(pass, user.getPass())) {
					Validation userNameRequired = new RequiredValidation(userNameParameterName, MessageFormat.format("ユーザー''{0}''の名前", user.getUserName()));
					Validation passRequired = new RequiredValidation(passParameterName, MessageFormat.format("ユーザー''{0}''のPASS", user.getUserName()));
					validator.add(userNameRequired);
					validator.add(passRequired);
					updateIds.add(user.getUserId());
				}
			}
		}

		if (Utils.isNotBlank(request.getParameter("username")) || Utils.isNotBlank(request.getParameter("tel")) || Utils.isNotBlank(request.getParameter("pass"))) {
			Validation userNameRequired = new RequiredValidation("username", "新規登録の名前");
			Validation passRequired = new RequiredValidation("pass", "新規登録のパスワード");
			validator.add(userNameRequired);
			validator.add(passRequired);
			doInsert = true;
		}

		final boolean hasChanges = updateIds.size() > 0 || deleteIds.size() > 0 || doInsert;
		Validation hasChangesTest = new Validation() {
			@Override
			public boolean test(HttpServletRequest request) { return hasChanges; }
			@Override
			public boolean skipWhenAlreadyFailed() { return false; }
			@Override
			public String getMessage() {return "データの変更がありません！"; }
			@Override
			public int getLevel() { return Validation.LEVEL_FAIL; }
		};
		validator.add(hasChangesTest);

		validator.validateAndForward(request, response);
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.ValidationSuccessListener#onValidationSuccessed(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void onValidationSuccessed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			for (int id : deleteIds) {
				getUserDao().delete(id);
			}
			for (int id : updateIds) {
				User user = getUserDao().select(id);
				user.setUserName(request.getParameter(MessageFormat.format("username{0}", user.getUserId())));
				user.setTel(request.getParameter(MessageFormat.format("tel{0}", user.getUserId())));
				user.setPass(request.getParameter(MessageFormat.format("pass{0}", user.getUserId())));
				getUserDao().update(user);
			}
			if (doInsert) {
				User user = new User();
				user.setUserName(request.getParameter("username"));
				user.setTel(request.getParameter("tel"));
				user.setPass(request.getParameter("pass"));
				getUserDao().insert(user);
			}

			users = getUserDao().selectAll();
			request.setAttribute("users", users);

			request.setAttribute("deletes", deleteIds.size());
			request.setAttribute("updates", updateIds.size());
			request.setAttribute("inserts", doInsert ? 1 : 0);
		} catch (EntityException e) {
			ValidationMessage validationMessage = ValidationMessage.error(e.getMessage());
			request.setAttribute("validationMessage", validationMessage);
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
	}
}
