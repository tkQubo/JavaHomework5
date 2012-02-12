package com.qubo.caea05.validations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 入力チェックを行った結果、ユーザーに表示すべきメッセージの一覧を表現したクラス。
 * @author Qubo
 */
public class ValidationMessage {
	private final String[] messages;
	private final String type;
	private final int level;
	public static final String TYPE_INFO = "info";
	public static final String TYPE_WARN = "warn";
	public static final String TYPE_FAIL = "error";
	public static final String TYPE_ERROR = "error";

	@SuppressWarnings("serial")
	public static final Map<Integer, String> varNames = new HashMap<Integer, String>() {{
		put(Validation.LEVEL_ERROR, ValidationMessage.TYPE_FAIL);
		put(Validation.LEVEL_FAIL, ValidationMessage.TYPE_FAIL);
		put(Validation.LEVEL_WARN, ValidationMessage.TYPE_WARN);
		put(Validation.LEVEL_INFO, ValidationMessage.TYPE_INFO);
	}};

	/**
	 * メッセージの一覧を取得する
	 * @return メッセージの一覧
	 */
	public String[] getMessages() { return messages; }
	/**
	 * メッセージの種類を文字列として取得する
	 * @return メッセージの種類
	 */
	public String getType() { return type; }
	/**
	 * チェックのレベルを取得する
	 * @return チェックのレベル
	 */
	public int getLevel() { return level; }

	/**
	 * コンストラクタ
	 * @param level チェックレベル
	 * @param messages メッセージの配列
	 */
	public ValidationMessage(int level, String... messages) {
		this.messages = messages.clone();
		this.type = varNames.get(level);
		this.level = level;
	}
	/**
	 * コンストラクタ
	 * @param level チェックレベル
	 * @param messageList メッセージリスト
	 */
	public ValidationMessage(int level, List<String> messageList) {
		this(level, messageList.toArray(new String[0]));
	}

	public static ValidationMessage error(String... messages) {
		return new ValidationMessage(Validation.LEVEL_ERROR, messages);
	}
	public static ValidationMessage fail(String... messages) {
		return new ValidationMessage(Validation.LEVEL_FAIL, messages);
	}
	public static ValidationMessage warn(String... messages) {
		return new ValidationMessage(Validation.LEVEL_WARN, messages);
	}
}
