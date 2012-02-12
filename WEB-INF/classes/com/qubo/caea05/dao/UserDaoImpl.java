package com.qubo.caea05.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.qubo.caea05.beans.User;

/**
 * データベースから{@link User}型を扱う{@link Dao}実装クラス。
 * @author Qubo
 */
public class UserDaoImpl extends AbstractDbDao<User> {
	public static final String TABLE_NAME = "users";
	public static final String COLUMN_NAME_ID = "userid";
	public static final String COLUMN_NAME_USERNAME = "username";
	public static final String COLUMN_NAME_TEL = "tel";
	public static final String COLUMN_NAME_PASS = "pass";
	public static final String SQL_INSERT = "insert into users (username, tel, pass) values(?, ?, ?);";
	public static final String SQL_UPDATE = "update users set username = ?, tel = ?, pass = ? where userid = ?;";

	/**
	 * コンストラクタ
	 * @param connector {@link DbConnector}インスタンス
	 */
	public UserDaoImpl(DbConnector connector) {
		super(connector, TABLE_NAME, COLUMN_NAME_ID, SQL_INSERT, SQL_UPDATE);
	}
	/** 標準のコンストラクタ */
	public UserDaoImpl() {
		super(TABLE_NAME, COLUMN_NAME_ID, SQL_INSERT, SQL_UPDATE);
	}

	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.dao.AbstractDbDao#fromResultSetToEntity(java.sql.ResultSet)
	 */
	@Override
	protected User fromResultSetToEntity(ResultSet rs) throws SQLException {
		User user = new User();
		user.setUserId(rs.getInt(COLUMN_NAME_ID));
		user.setUserName(rs.getString(COLUMN_NAME_USERNAME));
		user.setTel(rs.getString(COLUMN_NAME_TEL));
		user.setPass(rs.getString(COLUMN_NAME_PASS));
		return user;
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.dao.AbstractDbDao#fromEntityToInsertParameters(java.lang.Object)
	 */
	@Override
	protected Object[] fromEntityToInsertParameters(User entity) {
		return new Object[] { entity.getUserName(), entity.getTel(), entity.getPass() };
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.dao.AbstractDbDao#fromEntityToUpdateParameters(java.lang.Object)
	 */
	@Override
	protected Object[] fromEntityToUpdateParameters(User entity) throws SQLException {
		return new Object[] { entity.getUserName(), entity.getTel(), entity.getPass(), entity.getUserId() };
	}

}
