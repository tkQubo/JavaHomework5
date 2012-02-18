package com.qubo.caea05.dao.criteria;

/**
 * 文字列比較の演算子を表現した列挙型
 * @author Qubo
 */
public enum StringMatchingType {
	/** 完全一致検索 */
	Exact { @Override public String getLabel() { return "完全一致"; } },
	/** 部分一致検索 */
	Contains { @Override public String getLabel() { return "部分一致"; } },
	/** 前方一致検索 */
	StartsWith { @Override public String getLabel() { return "前方一致"; } },
	/** 後方一致検索 */
	EndsWith { @Override public String getLabel() { return "後方一致"; } },
	;
	public abstract String getLabel();
}
