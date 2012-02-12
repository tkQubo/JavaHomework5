package com.qubo.caea05.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * データベースへの接続を行うクラス
 * @author Qubo
 */
class DbConnector {
	private static final String DEFAULT_JDBC_NAME = "com.mysql.jdbc.Driver";
	private static final String DEFAULT_JDBC_CONNECTION_STRING = "jdbc:mysql://localhost:3306/cadb";
	private static final String DEFAULT_USERNAME = "causer";
	private static final String DEFAULT_PASSWORD = "cajava";

	/** デフォルトの{@link DbConnector}インスタンスを返す */
	public static final DbConnector defaultConnector = new DbConnector(DEFAULT_JDBC_NAME, DEFAULT_JDBC_CONNECTION_STRING, DEFAULT_USERNAME, DEFAULT_PASSWORD);

	private final String jdbcName;
	private final String connectionString;
	private final String userName;
	private final String password;

	/**
	 * 標準のコンストラクタ
	 * @param jdbcName JDBCドライバ名
	 * @param connectionString 接続文字列
	 * @param userName データベースのユーザー名
	 * @param password データベースのパスワード
	 */
	public DbConnector(String jdbcName, String connectionString, String userName, String password) {
		this.jdbcName = jdbcName;
		this.connectionString = connectionString;
		this.userName = userName;
		this.password = password;
	}

	/**
	 * JDBCドライバ名を取得する
	 * @return JDBCドライバ名
	 */
	public String getJdbcName() { return jdbcName; }
	/**
	 * 接続文字列を取得する
	 * @return 接続文字列
	 */
	public String getConnectionString() { return connectionString; }
	/**
	 * データベースのユーザー名を取得する
	 * @return データベースのユーザー名
	 */
	public String getUserName() { return userName; }
	/**
	 * データベースのパスワードを取得する
	 * @return データベースのパスワード
	 */
	public String getPassword() { return password; }

	/**
	 * {@link Connection}インスタンスを取得する
	 * @return {@link Connection}インスタンス
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(jdbcName);
		return DriverManager.getConnection(connectionString, userName, password);
	}
	/**
	 * {@link Statement}および{@link Connection}の接続を閉じる
	 * @param statement 対象の{@link Statement}
	 * @throws SQLException
	 */
	public void close(Statement statement) throws SQLException {
		if (statement != null) {
			statement.close();
			Connection connection = statement.getConnection();
			if (connection != null) {
				connection.close();
			}
		}
	}
	/**
	 * 与えられたSQL文とパラメータから、{@link PreparedStatement}インスタンスを生成、取得する
	 * @param sql SQL文
	 * @param parameters SQLパラメータ
	 * @return {@link PreparedStatement}インスタンス
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public PreparedStatement createPreparedStatement(String sql, Object... parameters) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		for (int i = 0; i < parameters.length; i++) {
			prepareStatement.setObject(i + 1, parameters[i]);
		}
		return prepareStatement;
	}
	/**
	 * {@link Statement}インスタンスを取得する
	 * @return {@link Statement}インスタンス
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Statement createStatement() throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		return connection.createStatement();
	}
}
