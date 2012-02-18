package com.qubo.caea05.validations;

/**
 * 入力チェックの結果を格納できる{@link Validation}インターフェース
 * @author Qubo
 */
public interface ValidationWithResult<T> extends Validation {
	/**
	 * 入力チェックの結果を取得する
	 * @return 入力チェックの結果
	 */
	T getResult();
}
