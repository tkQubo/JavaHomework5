package com.qubo.caea05.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.qubo.caea05.Configuration;
import com.qubo.caea05.dao.criteria.Criterion;
import com.qubo.caea05.dao.criteria.IntegerCriterion;
import com.qubo.caea05.dao.criteria.StringCriterion;

/**
 * データベースをデータソースとする{@link Dao}の基礎となる抽象クラス
 * @author Qubo
 */
abstract class AbstractDbDao<TEntity> implements Dao<TEntity> {
	private static final String SQL_DELETE_WITH_TABLENAME = "delete from {0} where {1} = ?;";
	private static final String SQL_SELECT_WITH_TABLENAME = "select * from {0} where {1} = ?;";
	private static final String SQL_SELECT_ALL_WITH_TABLENAME = "select * from {0}";

	protected final DbConnector connector;
	protected final String sqlSelectAll;
	protected final String sqlSelect;
	protected final String sqlInsert;
	protected final String sqlUpdate;
	protected final String sqlDelete;

	/**
	 * コンストラクタ
	 * @param connector {@link DbConnector}インスタンス
	 * @param sqlSelectAll {@link Dao#selectAll()}用のSQL文
	 * @param sqlSelect {@link Dao#select(int)}用のSQL文
	 * @param sqlInsert {@link Dao#insert(Object)}用のSQL文
	 * @param sqlUpdate {@link Dao#update(Object)}用のSQL文
	 * @param sqlDelete {@link Dao#delete(int)}用のSQL文
	 */
	public AbstractDbDao(DbConnector connector, String sqlSelectAll, String sqlSelect, String sqlInsert, String sqlUpdate, String sqlDelete) {
		this.connector = connector;
		this.sqlSelectAll = sqlSelectAll;
		this.sqlSelect = sqlSelect;
		this.sqlInsert = sqlInsert;
		this.sqlUpdate = sqlUpdate;
		this.sqlDelete = sqlDelete;
	}
	/**
	 * コンストラクタ
	 * @param sqlSelectAll {@link Dao#selectAll()}用のSQL文
	 * @param sqlSelect {@link Dao#select(int)}用のSQL文
	 * @param sqlInsert {@link Dao#insert(Object)}用のSQL文
	 * @param sqlUpdate {@link Dao#update(Object)}用のSQL文
	 * @param sqlDelete {@link Dao#delete(int)}用のSQL文	 */
	public AbstractDbDao(String sqlSelectAll, String sqlSelect, String sqlInsert, String sqlUpdate, String sqlDelete) {
		this(DbConnector.defaultConnector, sqlSelectAll, sqlSelect, sqlInsert, sqlUpdate, sqlDelete);
	}
	/**
	 * コンストラクタ
	 * @param connector {@link DbConnector}インスタンス
	 * @param tableName データベース内でのテーブル名
	 * @param idColumnName エンティティのIDが保存されているカラム名
	 * @param sqlInsert {@link Dao#insert(Object)}用のSQL文
	 * @param sqlUpdate {@link Dao#update(Object)}用のSQL文
	 */
	public AbstractDbDao(DbConnector connector, String tableName, String idColumnName, String sqlInsert, String sqlUpdate) {
		this(
			connector,
			MessageFormat.format(SQL_SELECT_ALL_WITH_TABLENAME, sqlEscape(tableName)),
			MessageFormat.format(SQL_SELECT_WITH_TABLENAME, sqlEscape(tableName), sqlEscape(idColumnName)),
			sqlInsert,
			sqlUpdate,
			MessageFormat.format(SQL_DELETE_WITH_TABLENAME, sqlEscape(tableName), sqlEscape(idColumnName))
		);
	}
	/**
	 * コンストラクタ
	 * @param tableName データベース内でのテーブル名
	 * @param idColumnName エンティティのIDが保存されているカラム名
	 * @param sqlInsert {@link Dao#insert(Object)}用のSQL文
	 * @param sqlUpdate {@link Dao#update(Object)}用のSQL文
	 */
	public AbstractDbDao(String tableName, String idColumnName, String sqlInsert, String sqlUpdate) {
		this(DbConnector.defaultConnector, tableName, idColumnName, sqlInsert, sqlUpdate);
	}

	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.dao.Dao#selectAll()
	 */
	@Override
	public final List<TEntity> selectAll() throws EntityException {
		return execute(selectAllConverter, sqlSelectAll + ";");
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.dao.Dao#select(int)
	 */
	@Override
	public List<TEntity> select(Criterion... criteria) throws EntityException {
		String sql = buildSql(criteria);
		Object[] parameters = buildParameterArray(criteria);
		return execute(selectAllConverter, sql, parameters);
	}
	/**
	 * {@link Criterion}オブジェクトからパラメータの配列を生成する。
	 * @param criteria {@link Criterion}オブジェクト
	 * @return パラメータの配列
	 * @throws EntityException
	 */
	public Object[] buildParameterArray(Criterion... criteria) throws EntityException {
		List<Object> parameters = new ArrayList<Object>();
		for (Criterion criterion : criteria) {
			if (criterion instanceof StringCriterion) {
				StringCriterion stringCriterion = (StringCriterion) criterion;
				String value = likeEscape(stringCriterion.getValue());
				switch (stringCriterion.getType()) {
				case Contains: value = '%' + value + '%'; break;
				case StartsWith: value = value + '%'; break;
				case EndsWith: value = '%' + value; break;
				}
				parameters.add(value);
			}else if (criterion instanceof IntegerCriterion) {
				IntegerCriterion integerCriterion = (IntegerCriterion) criterion;
				parameters.add(integerCriterion.getValue());
			} else if (criterion != null) {
				throw new EntityException(MessageFormat.format("[{0}]は実装されていません！", criteria.getClass().getName()));
			}
		}
		return parameters.toArray();
	}
	/**
	 * {@link Criterion}オブジェクトからSQL文を生成する。
	 * @param criteria {@link Criterion}オブジェクト
	 * @return SQL文
	 * @throws EntityException
	 */
	public String buildSql(Criterion... criteria) throws EntityException {
		StringBuilder builder = new StringBuilder();
		if (criteria.length > 0) {
			for (Criterion criterion : criteria) {
				if (builder.length() > 0) {
					builder.append(" and");
				}
				String fieldName = sqlEscape(criterion.getName());
				if (criterion instanceof StringCriterion) {
					builder.append(MessageFormat.format(" {0} LIKE ?", fieldName));
				} else if (criterion instanceof IntegerCriterion) {
					IntegerCriterion integerCriterion = (IntegerCriterion) criterion;
					String operator = null;
					switch (integerCriterion.getType()) {
					case Equals: operator = "="; break;
					case LessThan: operator = "<"; break;
					case LessThanEqual: operator = "<="; break;
					case GreaterThan: operator = ">"; break;
					case GreaterThanEqual: operator = ">="; break;
					}
					builder.append(MessageFormat.format(" {0} {1} ?", fieldName, operator));
				} else if (criterion != null) {
					throw new EntityException(MessageFormat.format("[{0}]は実装されていません！", criteria.getClass().getName()));
				}
			}
			builder.insert(0, " where");
		}
		builder.insert(0, sqlSelectAll);
		builder.append(";");
		return builder.toString();
	}
	/**
	 * SQL文をエスケープする
	 * @param value SQL文
	 * @return エスケープされたSQL文
	 */
	private static String sqlEscape(String value) {
		return (value != null) ? value.replace("'", "\\\\'").replace("\\\\", "\\\\\\\\") : "";
	}
	/**
	 * SQL内でLIKE構文に用いられる文字列をエスケープする
	 * @param value 文字列
	 * @return エスケープされた文字列
	 */
	private static String likeEscape(String value) {
		return (value != null) ? value.replace("%", "\\\\%").replace("_", "\\\\_") : "";
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.dao.Dao#select(int)
	 */
	@Override
	public final TEntity select(int id) throws EntityException {
		return execute(selectConverter, sqlSelect, id);
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.dao.Dao#insert(java.lang.Object)
	 */
	@Override
	public final int insert(TEntity entity) throws EntityException {
		try {
			Object[] parameters = fromEntityToInsertParameters(entity);
			return execute(updateConverter, sqlInsert, parameters);
		} catch (SQLException e) {
			throw new EntityException(e);
		}

	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.dao.Dao#update(java.lang.Object)
	 */
	@Override
	public final int update(TEntity entity) throws EntityException {
		try {
			Object[] parameters = fromEntityToUpdateParameters(entity);
			return execute(updateConverter, sqlUpdate, parameters);
		} catch (SQLException e) {
			throw new EntityException(e);
		}
	}
	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.dao.Dao#delete(int)
	 */
	@Override
	public final int delete(int id) throws EntityException {
		return execute(updateConverter, sqlDelete, id);
	}

	/**
	 * {@link ResultSet}オブジェクトからエンティティのインスタンスを生成する。
	 * @param rs {@link ResultSet}オブジェクト
	 * @return 生成されたエンティティ
	 * @throws SQLException
	 */
	protected abstract TEntity fromResultSetToEntity(ResultSet rs) throws SQLException;
	/**
	 * エンティティから、SQLのINSERT文で使用するパラメータ配列を生成する。
	 * @param entity エンティティ
	 * @return パラメータ配列
	 * @throws SQLException
	 */
	protected abstract Object[] fromEntityToInsertParameters(TEntity entity) throws SQLException;
	/**
	 * エンティティから、SQLのUPDATE文で使用するパラメータ配列を生成する。
	 * 基本的にUPDATE文は
	 * <code>
	 * "update hoge set huga = ?, hoga = ? where id = ?"
	 * </code>
	 * の形になるため、配列の最後にはエンティティのIDが必要。
	 * @param entity エンティティ
	 * @return パラメータ配列
	 * @throws SQLException
	 */
	protected abstract Object[] fromEntityToUpdateParameters(TEntity entity) throws SQLException;

	/**
	 * 与えられたSQL文とパラメータ配列からSQLを実行し、結果を{@code T}型に変換して返す内部メソッド。<br />
	 * 内部的にはSQL文とパラメータ配列を{@link PreparedStatementConverter}オブジェクトに渡して、
	 * その{@link PreparedStatementConverter}オブジェクトが内部で生成した{@link PreparedStatement}を
	 * {@code T}型に変換する。
	 * @param executor 変換関数オブジェクト
	 * @param sql SQL文
	 * @param parameters パラメータ配列
	 * @return {@code T}型のオブジェクト
	 * @throws EntityException
	 */
	protected final <T> T execute(PreparedStatementConverter<T> executor, String sql, Object... parameters) throws EntityException {
		PreparedStatement statement = null;

		try {
			showSql(sql, parameters);
			statement = connector.createPreparedStatement(sql, parameters);
			return executor.map(statement);
		} catch (ClassNotFoundException e) {
			throw new EntityException(e);
		} catch (SQLException e) {
			throw new EntityException(e);
		} finally {
			try {
				connector.close(statement);
			} catch (SQLException e) {
				throw new EntityException(e);
			}
		}
	}
	/** {@link Dao#selectAll()}用。{@link PreparedStatement}オブジェクトをエンティティのリストに変換するための{@link PreparedStatementConverter}オブジェクト */
	private final PreparedStatementConverter<List<TEntity>> selectAllConverter = new PreparedStatementConverter<List<TEntity>>() {
		@Override
		public List<TEntity> map(PreparedStatement preparedStatement) throws ClassNotFoundException, SQLException {
			ResultSet rs = preparedStatement.executeQuery();

			List<TEntity> list = new ArrayList<TEntity>();
			while (rs.next()) {
				TEntity entity = fromResultSetToEntity(rs);
				list.add(entity);
			}

			return Collections.unmodifiableList(list);
		}
	};
	/** {@link Dao#select(int)}用。{@link PreparedStatement}オブジェクトをエンティティに変換するための{@link PreparedStatementConverter}オブジェクト */
	private final PreparedStatementConverter<TEntity> selectConverter = new PreparedStatementConverter<TEntity>() {
		@Override
		public TEntity map(PreparedStatement preparedStatement) throws ClassNotFoundException, SQLException {
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				return fromResultSetToEntity(rs);
			} else {
				return null;
			}
		}
	};
	/** {@link Dao#update(Object)}用。{@link PreparedStatement}オブジェクトからエンティティを更新するための{@link PreparedStatementConverter}オブジェクト */
	private final PreparedStatementConverter<Integer> updateConverter = new PreparedStatementConverter<Integer>() {
		@Override
		public Integer map(PreparedStatement preparedStatement) throws ClassNotFoundException, SQLException {
			return preparedStatement.executeUpdate();
		}
	};
	/** {@link Dao#delete(int)}用。{@link PreparedStatement}オブジェクトからエンティティを削除するための{@link PreparedStatementConverter}オブジェクト */
	private void showSql(String sql, Object... parameters) {
		if (Configuration.showSql) {
			System.out.println("SQL: " + sql);
			if (parameters.length > 0) {
				StringBuilder builder = new StringBuilder();
				for (Object object : parameters) {
					if (builder.length() > 0)
						builder.append(", ");
					builder.append(object);
				}
				System.out.println("PRM: " + builder);
			}
		}
	}
}
