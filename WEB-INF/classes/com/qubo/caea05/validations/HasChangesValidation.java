package com.qubo.caea05.validations;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * ユーザーがデータを変更したかどうかをチェックするための{@link Validation}実装クラス。
 * @author Qubo
 */
public class HasChangesValidation implements Validation {
	private static final String MESSAGE = "変更がありません！";
	public static final String OLD_PARAMETER_SUFFIX = "_OLD";
	private final Map<String, String> parameterNamePairs;

	/**
	 * コンストラクタ
	 * @param parameterNamePairs 古い値を保持したパラメータの名前と、新しい値を保持したパラメータの名前のペア
	 */
	public HasChangesValidation(Map<String, String> parameterNamePairs) {
		this.parameterNamePairs = parameterNamePairs;
	}
	/**
	 * コンストラクタ
	 * @param newParameterNames 新しい値を保持したパラメータの配列。古い値を保持したパラメータの名前は、サフィックスとして{@code "_OLD"}を付ける。
	 */
	public HasChangesValidation(String... newParameterNames) {
		this.parameterNamePairs = new HashMap<String, String>();
		for (String newParameterName : newParameterNames) {
			this.parameterNamePairs.put(newParameterName, newParameterName + OLD_PARAMETER_SUFFIX);
		}
	}

	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Validation#test(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public boolean test(HttpServletRequest request) {
		for (Map.Entry<String, String> pair : parameterNamePairs.entrySet()) {
			Validation validation = new SameValueValidation(pair.getKey(), pair.getValue(), null);
			if (!validation.test(request)) {
				return true;
			}
		}
		return false;
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Validation#getMessage()
	 */
	@Override
	public String getMessage() { return MESSAGE; }
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Validation#getLevel()
	 */
	@Override
	public int getLevel() { return LEVEL_WARN; }
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.validations.Testable#skipWhenAlreadyFailed()
	 */
	@Override
	public boolean skipWhenAlreadyFailed() { return true; }
}
