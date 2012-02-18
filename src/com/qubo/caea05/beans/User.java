package com.qubo.caea05.beans;

import java.text.MessageFormat;

/**
 * データベースの{@code users}テーブルのレコードを、オブジェクトとして表現したクラス
 * @author Qubo
 */
public class User {
	private static final String FORMAT = "[{0}]{1}({2})";

	private int userId;
	private String userName;
	private String tel;
	private String pass;

	/**
	 * ユーザーIDを取得する
	 * @return ユーザーID
	 */
	public int getUserId() { return userId; }
	/**
	 * ユーザー名を取得する
	 * @return ユーザー名
	 */
	public String getUserName() { return userName; }
	/**
	 * 電話番号を設定する
	 * @param tel 電話番号
	 */
	public String getTel() { return tel; }
	/**
	 * パスワードを取得する
	 * @return パスワード
	 */
	public String getPass() { return pass; }

	/**
	 * ユーザーIDを設定する
	 * @param userId ユーザーID
	 */
	public void setUserId(int userId) { this.userId = userId; }
	/**
	 * ユーザー名を設定する
	 * @param userName ユーザー名
	 */
	public void setUserName(String userName) { this.userName = userName; }
	/**
	 * 電話番号を取得する
	 * @return 電話番号
	 */
	public void setTel(String tel) { this.tel = tel; }
	/**
	 * パスワードを設定する
	 * @param pass パスワード
	 */
	public void setPass(String pass) { this.pass = pass; }

	/*
	 * (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return MessageFormat.format(FORMAT, userId, userName, tel);
	}
}
