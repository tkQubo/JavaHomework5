package com.qubo.caea05.dao;

import com.qubo.caea05.beans.User;

/**
 * {@link DaoFactory}の実装版（データベースをデータソースとして持つ）
 * @author Qubo
 */
class DaoFactoryImpl implements DaoFactory {
	private final Dao<User> userDao = new UserDaoImpl();
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.dao.DaoFactory#getUserDao()
	 */
	@Override
	public Dao<User> getUserDao() { return userDao; }
}
