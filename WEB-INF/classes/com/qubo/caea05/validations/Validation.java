package com.qubo.caea05.validations;

import javax.servlet.http.HttpServletRequest;

/**
 * ユーザーからの値の入力チェックを表現したインターフェース
 * @author Qubo
 */
public interface Validation {
	/**
	 * 与えられた{@link HttpServletRequest}オブジェクトからユーザー入力のチェックを行う
	 * @param request {@link HttpServletRequest}オブジェクト
	 * @return チェックにパスしたかどうか
	 */
	boolean test(HttpServletRequest request);
	/**
	 * チェックにパスしなかった場合に表示すべきメッセージを取得する。
	 * @return チェックにパスしなかった場合に表示すべきメッセージ
	 */
	String getMessage();
	/**
	 * チェックのレベルを取得する
	 * @return チェックのレベル
	 */
	int getLevel();
	/**
	 * 他の入力チェックがすでに失敗していた場合、このテストを行うかどうかを取得
	 * @return 他の入力チェックがすでに失敗していた場合、このテストを行うかどうかを取得
	 */
	boolean skipWhenAlreadyFailed();

	/** チェックレベル：エラー */
	int LEVEL_ERROR = 3;
	/** チェックレベル：失敗 */
	int LEVEL_FAIL = 2;
	/** チェックレベル：警告 */
	int LEVEL_WARN = 1;
	/** チェックレベル：情報の */
	int LEVEL_INFO = 0;

}