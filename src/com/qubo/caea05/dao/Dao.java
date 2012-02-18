package com.qubo.caea05.dao;

import java.util.List;

import com.qubo.caea05.dao.criteria.Criterion;

/**
 * エンティティの操作を行うためのインターフェース
 * @author Qubo
 */
public interface Dao<T> {
	/**
	 * データソースから全てのエンティティを取得する
	 * @return {@code T}型の全てのエンティティ
	 * @throws EntityException
	 */
	List<T> selectAll() throws EntityException;
	/**
	 * データソースから{@link Criterion}の条件を満たした{@code T}型の全てのエンティティを取得する
	 * @param criteria エンティティの抽出条件
	 * @return {@link Criterion}の条件を満たした{@code T}型の全てのエンティティ
	 * @throws EntityException
	 */
	List<T> select(Criterion... criteria) throws EntityException;
	/**
	 * データソースから、指定したIDのエンティティを取得する
	 * @param id エンティティのID
	 * @return 見つかったエンティティ
	 * @throws EntityException
	 */
	T select(int id) throws EntityException;
	/**
	 * 指定したエンティティを、データソースに追加する
	 * @param entity エンティティ
	 * @return 追加件数（成功した場合に1を返す）
	 * @throws EntityException
	 */
	int insert(T entity) throws EntityException;
	/**
	 * 指定したエンティティのデータを更新する
	 * @param entity エンティティ
	 * @return 更新件数（成功した場合に1を返す）
	 * @throws EntityException
	 */
	int update(T entity) throws EntityException;
	/**
	 * 指定したIDを持つエンティティを、データソースから削除する
	 * @param id エンティティのID
	 * @return 削除件数（成功した場合に1を返す）
	 * @throws EntityException
	 */
	int delete(int id) throws EntityException;
}
