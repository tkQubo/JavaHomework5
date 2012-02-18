package com.qubo.caea05.validations;

import javax.servlet.http.HttpServletRequest;

import com.qubo.Utils;
import com.qubo.caea05.beans.User;
import com.qubo.caea05.dao.Dao;

/**
 * ユーザーが正しくログインできているかどうかをチェックするための{@link Validation}実装クラス。
 * @author Qubo
 */
public class AuthenticationValidation implements ValidationWithResult<User> {
	private static final String MESSAGE = "ユーザーIDまたはパスワードが違います！";
	private final String idParameterName;
	private final String passParameterName;
	private final Dao<User> userDao;
	private User authenticatedUser;

	/**
	 * コンストラクタ
	 * @param userDao {@link User}用の{@link Dao}インスタンス
	 * @param idParameterName ユーザーIDの、HTML上のパラメータ名
	 * @param passParameterName パスワードの、HTML上のパラメータ名
	 */
	public AuthenticationValidation(Dao<User> userDao, String idParameterName, String passParameterName) {
		this.userDao = userDao;
		this.idParameterName = idParameterName;
		this.passParameterName = passParameterName;
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Validation#test(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public boolean test(HttpServletRequest request) {
		boolean success = false;
		authenticatedUser = null;

		String userid = request.getParameter(idParameterName);
		String pass = request.getParameter(passParameterName);

		if (Utils.isNotBlank(userid) && Utils.isNotBlank(pass)) {
			ValidationWithResult<User> validation = new EntityExistsValidation<User>(userDao, idParameterName, null);
			if (validation.test(request)) {
				User user = validation.getResult();
				success = user != null && Utils.equals(user.getPass(), pass);
				if (success) {
					authenticatedUser = user;
				}
			}
		} else {
			success = true;
		}

		return success;
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Validation#getMessage()
	 */
	@Override
	public String getMessage() { return MESSAGE; }
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Validation#getLevel()
	 */
	@Override
	public int getLevel() { return LEVEL_FAIL; }
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Validation#getResult()
	 */
	@Override
	public User getResult() { return authenticatedUser; }
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Testable#skipWhenAlreadyFailed()
	 */
	@Override
	public boolean skipWhenAlreadyFailed() { return true; }
}
