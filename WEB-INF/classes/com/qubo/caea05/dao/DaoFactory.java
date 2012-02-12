package com.qubo.caea05.dao;

import com.qubo.caea05.beans.User;

/**
 * システムのに必要なDAOオブジェクトを返すファクトリ
 * @author Qubo
 */
public interface DaoFactory {
	/**
	 * {@link User}型を扱う{@link Dao}を返す
	 * @return {@link User}型を扱う{@link Dao}
	 */
	Dao<User> getUserDao();
	/** 標準で使用する{@link DaoFactory}を返す */
	DaoFactory defaultFactory = new DaoFactoryImpl();
}
