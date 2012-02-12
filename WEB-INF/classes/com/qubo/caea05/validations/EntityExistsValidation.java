package com.qubo.caea05.validations;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import com.qubo.Utils;
import com.qubo.caea05.beans.User;
import com.qubo.caea05.dao.Dao;
import com.qubo.caea05.dao.EntityException;

/**
 * ユーザーの指定したIDから、正しくエンティティを取得できるかどうかをチェックするための{@link Validation}実装クラス。
 * @author Qubo
 */
public class EntityExistsValidation<T> implements ValidationWithResult<T> {
	private static final String MESSAGE_FORMAT = "指定したIDの{0}は存在しませんでした！";
	private final Dao<T> dao;
	private final String idParameterName;
	private final String entityName;
	private String entityErrorMessage;
	private T entity;

	/**
	 * コンストラクタ
	 * @param dao {@link User}用の{@link Dao}インスタンス
	 * @param idParameterName ユーザーIDの、HTML上のパラメータ名
	 * @param entityName エンティティ名
	 */
	public EntityExistsValidation(Dao<T> dao, String idParameterName, String entityName) {
		this.dao = dao;
		this.idParameterName = idParameterName;
		this.entityName = entityName;
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Validation#getResult()
	 */
	@Override
	public T getResult() { return entity; }
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Validation#test(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public boolean test(HttpServletRequest request) {
		entityErrorMessage = null;
		entity = null;
		try {
			String idStr = request.getParameter(idParameterName);
			int id = Integer.parseInt(idStr);
			entity = dao.select(id);
			return entity != null;
		} catch (NumberFormatException e) {
			return true;
		} catch (EntityException e) {
			entityErrorMessage = e.getMessage();
			return false;
		}
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Validation#getMessage()
	 */
	@Override
	public String getMessage() {
		return Utils.isBlank(entityErrorMessage) ? MessageFormat.format(MESSAGE_FORMAT, entityName) : entityErrorMessage;
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Validation#getLevel()
	 */
	@Override
	public int getLevel() { return Utils.isBlank(entityErrorMessage) ? LEVEL_FAIL : LEVEL_ERROR; }
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Testable#skipWhenAlreadyFailed()
	 */
	@Override
	public boolean skipWhenAlreadyFailed() { return true; }
}
