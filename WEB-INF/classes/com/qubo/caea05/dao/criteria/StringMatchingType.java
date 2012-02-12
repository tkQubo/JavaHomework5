package com.qubo.caea05.dao.criteria;

/**
 * 文字列比較の演算子を表現した列挙型
 * @author Qubo
 */
public enum StringMatchingType {
	/** 完全一致検索 */
	Exact,
	/** 部分一致検索 */
	Contains,
	/** 前方一致検索 */
	StartsWith,
	/** 後方一致検索 */
	EndsWith,
}
