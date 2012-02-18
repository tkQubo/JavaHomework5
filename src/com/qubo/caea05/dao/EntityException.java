package com.qubo.caea05.dao;

/**
 * エンティティの操作時に発生する例外
 * @author Qubo
 */
public class EntityException extends Exception {
	private static final long serialVersionUID = 7063676481914813213L;

	/**
	 * コンストラクタ
	 * @param message エラーメッセージ
	 * @param throwable 内部エラー
	 */
	public EntityException(String message, Throwable throwable) { super(message, throwable); }
	/**
	 * コンストラクタ
	 * @param message エラーメッセージ
	 */
	public EntityException(String message) { super(message); }
	/**
	 * コンストラクタ
	 * @param throwable 内部エラー
	 */
	public EntityException(Throwable throwable) { super(throwable); }
}
