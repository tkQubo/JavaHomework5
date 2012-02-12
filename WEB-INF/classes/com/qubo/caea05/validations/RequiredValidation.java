package com.qubo.caea05.validations;


import com.qubo.Utils;

/**
 * ユーザーが必須項目を入力したかどうかをチェックするための{@link Validation}実装クラス。
 * @author Qubo
 */
public class RequiredValidation extends AbstractValidation {
	public static final String MESSAGE_FORMAT = "{0}は必須です！";

	/**
	 * コンストラクタ
	 * @param parameterName パラメータ名
	 * @param displayName パラメータの表示名
	 */
	public RequiredValidation(String parameterName, String displayName) {
		super(parameterName, displayName, MESSAGE_FORMAT);
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.AbstractValidation#doTest(java.lang.String)
	 */
	@Override
	protected boolean doTest(String value) {
		return Utils.isNotBlank(value);
	}
}
