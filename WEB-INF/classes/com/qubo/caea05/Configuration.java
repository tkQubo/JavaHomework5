package com.qubo.caea05;


/**
 * デバッグ用の諸設定を定義したクラス
 * @author Qubo
 */
public interface Configuration {
	/** {@code true}にすると、ユーザー認証を省略できる（DBには存在しない「デバッグユーザー」としてログインする） */
	boolean skipLogin = false;
	/** {@code true}にすると、DBに対してSQLが発行されるたびに、SQL文がパラメータと共にコンソールに表示される */
	boolean showSql = true;
	/** {@code true}にすると、一覧画面でユーザーのパスワードも表示する */
	boolean showPassword = false;
}
