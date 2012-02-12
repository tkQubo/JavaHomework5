package com.qubo.caea05.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * {@link PreparedStatement}を{@code T}型に変換する操作を定義したインターフェース
 * @author Qubo
 */
interface PreparedStatementConverter<T> {
	/**
	 * 与えられた{@link PreparedStatement}オブジェクトを{@code T}型のオブジェクトに変換する。
	 * @param preparedStatement {@link PreparedStatement}オブジェクト
	 * @return {@code T}型のオブジェクト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	T map(PreparedStatement preparedStatement) throws ClassNotFoundException, SQLException;
}