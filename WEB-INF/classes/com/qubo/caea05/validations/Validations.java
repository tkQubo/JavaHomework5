package com.qubo.caea05.validations;

import com.qubo.caea05.beans.User;
import com.qubo.caea05.dao.Dao;

/**
 * ユーザー管理システムのサーブレット間で共通して使われる{@link Validation}実装クラスを集めたヘルパークラス
 * @author Qubo
 */
public abstract class Validations {
	/** {@code "userid"}で指定されたパラメータが入力されているかどうかをチェックする{@link Validation}インスタンス */
	public final static Validation userIdRequired = new RequiredValidation("userid", "ユーザーID");
	/** {@code "userid"}で指定されたパラメータが整数かどうかをチェックする{@link Validation}インスタンス */
	public final static Validation userIdInteger = new IntegerValidation("userid", "ユーザーID");
	/** {@code "username"}で指定されたパラメータが入力されているかどうかをチェックする{@link Validation}インスタンス */
	public final static Validation userNameRequired = new RequiredValidation("username", "名前");
	/** {@code "pass"}で指定されたパラメータが入力されているかどうかをチェックする{@link Validation}インスタンス */
	public final static Validation passwordRequired = new RequiredValidation("pass", "パスワード");
	/** 更新画面で、ユーザー入力に変更があるかどうかをチェックする{@link Validation}インスタンス */
	public final static Validation noChange = new HasChangesValidation("username", "tel", "pass");
	/** 更新確認画面および新規登録確認画面で、ユーザーに再入力を求めたパスワードが入力されているかどうかをチェックする{@link Validation}インスタンス */
	public static final Validation passwordConfirmRequired = new RequiredValidation("passConfirm", "パスワード");
	/** 更新確認画面および新規登録確認画面で、ユーザーに再入力を求めたパスワードが一致しているかどうかをチェックする{@link Validation}インスタンス */
	public static final Validation passwordMatch = new SameValueValidation("pass", "passConfirm", "パスワード");
	/**
	 * ユーザーの指定したIDから、正しくエンティティを取得できるかどうかをチェックするための{@link Validation}インスタンスを生成する。
	 * @param userDao {@link User}用の{@link Dao}インスタンス
	 * @return {@link ValidationWithResult}インスタンス
	 */
	public static ValidationWithResult<User> createUserExistentValidation(Dao<User> userDao) {
		return new EntityExistsValidation<User>(userDao, "userid", "ユーザー");
	}
	/**
	 * ユーザーの入力条件を元にデータソースから取得した{@link User}エンティティの件数が、0件以上かどうかをチェックするための{@link Validation}インスタンスを生成する。
	 * @param userDao {@link User}用の{@link Dao}インスタンス
	 * @return {@link ValidationWithResult}インスタンス
	 */
	public static ValidationWithResult<Iterable<User>> createSelectQueryValidation(Dao<User> userDao) {
		return new SelectQueryValidation(userDao);
	}
	/**
	 * ユーザーが正しくログインできているかどうかをチェックするための{@link Validation}インスタンスを生成する。
	 * @param userDao {@link User}用の{@link Dao}インスタンス
	 * @return {@link ValidationWithResult}インスタンス
	 */
	public static ValidationWithResult<User> createAuthenticationValidation(Dao<User> userDao) {
		return new AuthenticationValidation(userDao, "userid", "pass");
	}

	private Validations() { }
}
