package com.qubo.caea05.validations;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import com.qubo.Utils;

/**
 * ユーザーが、必要な値と同じ値を入力したかどうかをチェックするための{@link Validation}実装クラス。
 * @author Qubo
 */
public class SameValueValidation implements Validation {
	private static final String MESSAGE_FORMAT = "{0}が一致しません！";
	private final String parameterName1;
	private final String parameterName2;
	private final String displayName;
	private boolean result;

	/**
	 * コンストラクタ
	 * @param parameterName1 比較する値１のパラメータ名
	 * @param parameterName2 比較する値２のパラメータ名
	 * @param displayName パラメータの表示名
	 */
	public SameValueValidation(String parameterName1, String parameterName2, String displayName) {
		this.parameterName1 = parameterName1;
		this.parameterName2 = parameterName2;
		this.displayName = displayName;
	}

	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Validation#test(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public boolean test(HttpServletRequest request) {
		String value1 = request.getParameter(parameterName1);
		String value2 = request.getParameter(parameterName2);
		result = Utils.equals(value1, value2);
		return result;
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Validation#getMessage()
	 */
	@Override
	public String getMessage() {
		return MessageFormat.format(MESSAGE_FORMAT, displayName);
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Validation#getLevel()
	 */
	@Override
	public int getLevel() { return LEVEL_FAIL; }
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Testable#skipWhenAlreadyFailed()
	 */
	@Override
	public boolean skipWhenAlreadyFailed() { return true; }
}
