package com.qubo.caea05.validations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.qubo.Utils;
import com.qubo.caea05.beans.User;
import com.qubo.caea05.dao.Dao;
import com.qubo.caea05.dao.EntityException;
import com.qubo.caea05.dao.criteria.Criterion;
import com.qubo.caea05.dao.criteria.StringCriterion;
import com.qubo.caea05.dao.criteria.StringMatchingType;

/**
 * ユーザーの入力条件を元にデータソースから取得した{@link User}エンティティの件数が、0件以上かどうかをチェックするための{@link Validation}実装クラス。
 * @author Qubo
 */
public class SelectQueryValidation implements ValidationWithResult<Iterable<User>> {
	private static final String MESSAGE = "検索結果は0件でした！";
	private final Dao<User> userDao;
	private String daoErrorMessage;
	private List<User> users;

	/**
	 * コンストラクタ
	 * @param userDao {@link User}用の{@link Dao}インスタンス
	 */
	public SelectQueryValidation(Dao<User> userDao) {
		this.userDao = userDao;
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Validation#test(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public boolean test(HttpServletRequest request) {
		daoErrorMessage = null;
		users = null;

		try {
			List<Criterion> criteria = new ArrayList<Criterion>();

			String useridStr = request.getParameter("userid");
			if (Utils.isNotBlank(useridStr)) {
				int userid = Integer.parseInt(useridStr);
				Criterion criterion = Criterion.equals("userid", userid);
				criteria.add(criterion);
			}

			String username = request.getParameter("username");
			if (Utils.isNotBlank(username)) {
				String typeStr = request.getParameter("usernameMatchType");
				StringMatchingType type = StringMatchingType.valueOf(typeStr);
				Criterion criterion = new StringCriterion("username", username, type);
				criteria.add(criterion);
			}

			String tel = request.getParameter("tel");
			if (Utils.isNotBlank(tel)) {
				String typeStr = request.getParameter("telMatchType");
				StringMatchingType type = StringMatchingType.valueOf(typeStr);
				Criterion criterion = new StringCriterion("tel", tel, type);
				criteria.add(criterion);
			}

			users = userDao.select(criteria.toArray(new Criterion[0]));
		} catch (NumberFormatException e) {
			daoErrorMessage = e.getMessage();
		} catch (EntityException e) {
			daoErrorMessage = e.getMessage();
		}

		return users != null && users.size() > 0;
	}

	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Validation#getMessage()
	 */
	@Override
	public String getMessage() { return Utils.isBlank(daoErrorMessage) ? MESSAGE : daoErrorMessage; }
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Validation#getLevel()
	 */
	@Override
	public int getLevel() { return Utils.isBlank(daoErrorMessage) ? LEVEL_WARN : LEVEL_ERROR; }
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Validation#getResult()
	 */
	@Override
	public Iterable<User> getResult() { return Collections.unmodifiableList(users); }
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Testable#skipWhenAlreadyFailed()
	 */
	@Override
	public boolean skipWhenAlreadyFailed() { return true; }

}
