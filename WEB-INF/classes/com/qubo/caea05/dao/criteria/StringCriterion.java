package com.qubo.caea05.dao.criteria;


/**
 * 文字列比較用の{@link Criterion}実装クラス
 * @author Qubo
 */
public class StringCriterion extends Criterion {
	private final String name;
	private final String value;
	private final StringMatchingType type;

	/**
	 * コンストラクタ
	 * @param name プロパティ名
	 * @param value 比較する値
	 * @param type 比較演算子
	 */
	public StringCriterion(String name, String value, StringMatchingType type) {
		this.name = name;
		this.value = value;
		this.type = type;
	}

	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.dao.criteria.Criterion#getName()
	 */
	@Override
	public String getName() { return name; }
	public String getValue() { return value; }
	public StringMatchingType getType() { return type; }
}
