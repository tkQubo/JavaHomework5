package com.qubo.caea05.dao.criteria;

/**
 * 数値比較用の{@link Criterion}実装クラス
 * @author Qubo
 */
public class IntegerCriterion extends Criterion {
	private final String name;
	private final int value;
	private final IntegerMathingType type;

	/**
	 * コンストラクタ
	 * @param name プロパティ名
	 * @param value 比較する値
	 * @param type 比較演算子
	 */
	public IntegerCriterion(String name, int value, IntegerMathingType type) {
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
	public int getValue() { return value; }
	public IntegerMathingType getType() { return type; }

}
