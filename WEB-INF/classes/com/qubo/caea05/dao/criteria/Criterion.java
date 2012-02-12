package com.qubo.caea05.dao.criteria;

/**
 * データソースからのエンティティの抽出条件を表現した抽象クラス。
 * @author Qubo
 */
public abstract class Criterion {
	/**
	 * 判定対象のエンティティのプロパティ名を取得する
	 * @return 判定対象のエンティティのプロパティ名
	 */
	public abstract String getName();

	public static Criterion equals(String name, int value) {
		return new IntegerCriterion(name, value, IntegerMathingType.Equals);
	}
	public static Criterion lessThan(String name, int value) {
		return new IntegerCriterion(name, value, IntegerMathingType.LessThan);
	}
	public static Criterion lessThanEqual(String name, int value) {
		return new IntegerCriterion(name, value, IntegerMathingType.LessThanEqual);
	}
	public static Criterion greaterThan(String name, int value) {
		return new IntegerCriterion(name, value, IntegerMathingType.GreaterThan);
	}
	public static Criterion greaterThanEqual(String name, int value) {
		return new IntegerCriterion(name, value, IntegerMathingType.GreaterThanEqual);
	}
	public static Criterion exact(String name, String value) {
		return new StringCriterion(name, value, StringMatchingType.Exact);
	}
	public static Criterion startsWith(String name, String value) {
		return new StringCriterion(name, value, StringMatchingType.StartsWith);
	}
	public static Criterion endsWithxact(String name, String value) {
		return new StringCriterion(name, value, StringMatchingType.EndsWith);
	}
	public static Criterion contains(String name, String value) {
		return new StringCriterion(name, value, StringMatchingType.Contains);
	}
}
