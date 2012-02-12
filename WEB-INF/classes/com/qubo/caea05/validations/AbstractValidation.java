package com.qubo.caea05.validations;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

/**
 * パラメータ名および入力チェックを失敗時に表示する表示名の組を持つ、
 * 単純な{@link Validation}実装のための抽象クラス
 * @author Qubo
 */
public abstract class AbstractValidation implements Validation {
	protected final String parameterName;
	protected final String displayName;
	protected final String messageFormat;
	private boolean result;

	/**
	 * コンストラクタ
	 * @param parameterName パラメータ名
	 * @param displayName パラメータの表示名
	 * @param messageFormat 入力チェック失敗時のメッセージのフォーマット
	 */
	public AbstractValidation(String parameterName, String displayName, String messageFormat) {
		this.parameterName = parameterName;
		this.displayName = displayName;
		this.messageFormat = messageFormat;
	}

	/**
	 * パラメータ名を取得する
	 * @return パラメータ名
	 */
	public final String getParameterName() { return parameterName; }
	/**
	 * パラメータの表示名を取得する
	 * @return パラメータの表示名
	 */
	public final String getDisplayName() { return displayName; }

	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Validation#getMessage()
	 */
	@Override
	public String getMessage() { return MessageFormat.format(messageFormat, displayName); }
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Validation#getLevel()
	 */
	@Override
	public final int getLevel() { return LEVEL_FAIL; }
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Validation#test(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public final boolean test(HttpServletRequest request) {
		String value = request.getParameter(parameterName);
		result = doTest(value);
		return result;
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Testable#skipWhenAlreadyFailed()
	 */
	@Override
	public boolean skipWhenAlreadyFailed() { return false; }
	/**
	 * 実際の入力チェックを行う抽象メソッド
	 * @param value 入力値
	 * @return
	 */
	protected abstract boolean doTest(String value);
}