package com.qubo.caea05.validations;

import com.qubo.Utils;

/**
 * ユーザーが整数値を入力したかどうかをチェックするための{@link Validation}実装クラス。
 * @author Qubo
 */
public class IntegerValidation extends AbstractValidation {
	static final String MESSAGE_FORMAT = "{0}には数値を入力して下さい！";

	/**
	 * コンストラクタ
	 * @param parameterName パラメータ名
	 * @param displayName パラメータの表示名
	 */
	public IntegerValidation(String parameterName, String displayName) {
		super(parameterName, displayName, MESSAGE_FORMAT);
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.AbstractValidation#doTest(java.lang.String)
	 */
	@Override
	protected boolean doTest(String value) {
		if (Utils.isBlank(value)) {
			return true;
		}
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
