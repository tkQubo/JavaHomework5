package com.qubo.caea05.dao.criteria;

/**
 * 数値比較の演算子を表現した列挙型
 * @author Qubo
 */
public enum IntegerMathingType {
	/** {@code '='} */
	Equals,
	/** {@code '<'} */
	LessThan,
	/** {@code '<='} */
	LessThanEqual,
	/** {@code '>'} */
	GreaterThan,
	/** {@code '>='} */
	GreaterThanEqual,
}
