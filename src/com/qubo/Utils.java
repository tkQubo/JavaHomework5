package com.qubo;

/**
 * 文字列用のユーティリティクラス
 * @author Qubo
 */
public abstract class Utils {
	/**
	 * 文字列が空かどうかチェックする。半角スペースだけの文字列や{@code null}も空文字列として扱う。
	 * @param text 文字列
	 * @return 文字列が空かどうか
	 */
	public static boolean isBlank(String text) {
		return text == null || text.trim().length() == 0;
	}
	/**
	 * 文字列が空でないかどうかチェックする。半角スペースだけの文字列や{@code null}も空文字列として扱う。
	 * @param text 文字列
	 * @return 文字列が空でないかどうか
	 */
	public static boolean isNotBlank(String text) { return !isBlank(text); }
	/**
	 * ２つの文字列が同一かどうかチェックする。半角スペースだけの文字列や{@code null}を空文字列として同一に扱う。
	 * @param str1 文字列１
	 * @param str2 文字列２
	 * @return ２つの文字列が同一かどうか
	 */
	public static boolean equals(String str1, String str2) {
		if (isBlank(str1) && isBlank(str2)) {
			return true;
		} else if (isBlank(str1) || isBlank(str2)) {
			return false;
		} else {
			return str1.trim().equals(str2.trim());
		}
	}
}
